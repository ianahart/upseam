package com.backend.fitters.cloth;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.backend.fitters.advice.BadRequestException;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.amazon.AmazonService;
import com.backend.fitters.cloth.dto.ClothesWithPaginationDto;
import com.backend.fitters.cloth.dto.FullClothDto;
import com.backend.fitters.cloth.request.CreateClothRequest;
import com.backend.fitters.cloth.request.UpdateClothRequest;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserRepository;
import com.backend.fitters.user.UserService;
import com.backend.fitters.util.MyUtils;
import com.backend.fitters.cloth.dto.ClothDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

@Service
public class ClothService {

    private final ClothRepository clothRepository;
    private final UserRepository userRepository;
    private final AmazonService amazonService;
    private final UserService userService;
    private final String bucketName = "arrow-date/upseam/clothes";

    @Autowired
    public ClothService(
            ClothRepository clothRepository,
            UserService userService,
            AmazonService amazonService,
            UserRepository userRepository) {
        this.clothRepository = clothRepository;
        this.amazonService = amazonService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void updateClothClosed(Long clothId, Long closedId) {
        boolean exists = this.clothRepository.existsById(clothId);
        if (!exists) {
            throw new BadRequestException("Cloth with the id " + clothId + " does not exist");
        }

        Cloth cloth = this.clothRepository
                .findById(clothId)
                .orElseThrow(() -> new NotFoundException("Cloth not found"));

        cloth.setClosedId(closedId);
        cloth.setClosed(true);
        this.clothRepository.save(cloth);
    }

    public FullClothDto getCloth(Long clothId) {
        boolean exists = this.clothRepository.existsById(clothId);
        if (!exists) {
            throw new BadRequestException("Cloth with the id " + clothId + " does not exist");
        }

        return this.clothRepository.findFullClothById(clothId);
    }

    public void updateCloth(UpdateClothRequest request, Long clothId) {
        Cloth cloth = this.clothRepository.findById(clothId)
                .orElseThrow(() -> new BadRequestException("Cloth not found"));
        User user = this.userService.getUserByAuth();
        if (cloth.getUser().getId() != user.getId()) {
            throw new BadRequestException("Cannot update another user's clothes");
        }

        if (request.getFile() != null && cloth.getClothFilename() != null) {
            this.amazonService.delete(bucketName, cloth.getClothFilename());
            String fileName = this.amazonService.upload(bucketName,
                    request.getFile().getOriginalFilename(),
                    request.getFile());
            Map<String, String> fileContents = this.amazonService.getPublicUrl(bucketName, fileName);

            cloth.setClothFilename(fileContents.get("fileName"));
            cloth.setClothUrl((fileContents.get("url")));

        }

        if (request.getDescription().length() > 250) {
            throw new BadRequestException("Clothes description must be under 250 characters");
        }

        cloth.setSize(request.getSize());
        cloth.setDescription(request.getDescription());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = request.getDueDate().replaceAll("^\"|\"$", "");
        cloth.setDueDate(LocalDate.parse(date, df));

        this.clothRepository.save(cloth);
    }

    public ClothDto syncCloth(String clothId) {
        User user = this.userService.getUserByAuth();
        ClothDto cloth = this.clothRepository.findByClothId(Long.parseLong(clothId));
        if (cloth.getUserId() != user.getId()) {
            throw new BadRequestException("Cannot update another user's clothes");
        }

        return cloth;
    }

    public ClothesWithPaginationDto getAllClothes(int page, int pageSize, String direction) {
        int currentPage = MyUtils.paginate(page, direction);
        Pageable paging = PageRequest.of(currentPage, pageSize, Sort.by("id"));

        return new ClothesWithPaginationDto(this.clothRepository.findAllClothes(paging), currentPage,

                direction);
    }

    public ClothesWithPaginationDto getUserClothes(Long userId, int page, int pageSize, String direction) {
        if (userId == null) {
            throw new BadRequestException("No user id found to fetch user's clothes");
        }
        int currentPage = MyUtils.paginate(page, direction);
        Pageable paging = PageRequest.of(currentPage, pageSize, Sort.by("id"));

        return new ClothesWithPaginationDto(this.clothRepository.findAllByUserId(userId, paging), currentPage,

                direction);
    }

    public void createCloth(CreateClothRequest request) {
        User user = this.userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        Cloth cloth = new Cloth();

        cloth.setUser(user);

        if (request.getSize().isEmpty()) {
            throw new BadRequestException("Please select a size");
        }
        cloth.setSize(request.getSize());
        cloth.setClosed(false);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = request.getDueDate().replaceAll("^\"|\"$", "");
        cloth.setDueDate(LocalDate.parse(date, df));

        if (request.getDescription().length() > 250) {
            throw new BadRequestException("Description must be under 250 characters");
        }
        cloth.setDescription(request.getDescription());
        String fileName = this.amazonService.upload(
                bucketName,
                request.getFile().getOriginalFilename(),
                request.getFile());

        Map<String, String> fileContents = this.amazonService.getPublicUrl(bucketName, fileName);

        cloth.setClothFilename(fileContents.get("fileName"));
        cloth.setClothUrl((fileContents.get("url")));

        this.clothRepository.save(cloth);
    }
}
