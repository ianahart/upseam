package com.backend.fitters.cloth;

import java.util.Map;

import com.backend.fitters.advice.BadRequestException;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.amazon.AmazonService;
import com.backend.fitters.cloth.request.CreateClothRequest;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void createCloth(CreateClothRequest request) {
        User user = this.userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        Cloth cloth = new Cloth();

        System.out.println(user.getLastName());
        cloth.setUser(user);

        if (request.getSize().isEmpty()) {
            throw new BadRequestException("Please select a size");
        }
        cloth.setSize(request.getSize());
        cloth.setDueDate(request.getDueDate());
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
