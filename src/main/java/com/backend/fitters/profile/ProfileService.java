package com.backend.fitters.profile;

import java.util.Map;

import com.backend.fitters.amazon.AmazonService;
import com.backend.fitters.profile.request.ProfilePhotoRequest;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.profile.dto.ProfileDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AmazonService amazonService;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, AmazonService amazonService) {
        this.profileRepository = profileRepository;
        this.amazonService = amazonService;
    }

    public ProfileDto getProfile(Long profileId) {
        Profile profile = this.profileRepository.findById(profileId).orElseThrow(
                () -> new NotFoundException("Profile not found."));
        return new ProfileDto(profile,
                profile.getUser().getFirstName(),
                profile.getUser().getLastName());
    }

    public Profile createProfile() {
        Profile profile = new Profile();

        return this.profileRepository.save(profile);
    }

    private void addPhotoToProfile(String avatarUrl, String avatarFileName, Long profileId) {
        Profile profile = this.profileRepository.getById(profileId);
        if (profile.getAvatarUrl() != null) {
            this.amazonService.delete("arrow-date/upseam/avatars", profile.getAvatarFileName());
        }

        profile.setAvatarUrl(avatarUrl);
        profile.setAvatarFileName(avatarFileName);

        this.profileRepository.save(profile);

    }

    public Map<String, String> uploadPhoto(ProfilePhotoRequest request) {
        String fileName = this.amazonService.upload(
                "arrow-date/upseam/avatars",
                request.getFile().getOriginalFilename(),
                request.getFile());
        Map<String, String> mapper = this.amazonService.getPublicUrl("arrow-date/upseam/avatars",
                fileName);
        addPhotoToProfile(mapper.get("url"), mapper.get("fileName"), request.getProfileId());
        return mapper;
    }
}
