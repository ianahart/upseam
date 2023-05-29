package com.backend.fitters.cloth;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.backend.fitters.advice.BadRequestException;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.amazon.AmazonService;
import com.backend.fitters.cloth.dto.ClothesDto;
import com.backend.fitters.cloth.dto.ClothesWithPaginationDto;
import com.backend.fitters.cloth.request.CreateClothRequest;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserRepository;
import com.backend.fitters.util.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

@Service
public class ClothService {

    private final ClothRepository clothRepository;
    private final UserRepository userRepository;
    private final AmazonService amazonService;
    private final String bucketName = "arrow-date/upseam/clothes";

    @Autowired
    public ClothService(
            ClothRepository clothRepository,
            AmazonService amazonService,
            UserRepository userRepository) {
        this.clothRepository = clothRepository;
        this.amazonService = amazonService;
        this.userRepository = userRepository;
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
