package com.backend.fitters.profile;

import java.util.Map;

import com.backend.fitters.amazon.AmazonService;
import com.backend.fitters.profile.request.ProfilePhotoRequest;
import com.backend.fitters.profile.request.UpdateProfileRequest;
import com.backend.fitters.profile.dto.ProfileFieldsDto;
import com.backend.fitters.profile.dto.ProfileShippingDto;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.advice.ForbiddenException;
import com.backend.fitters.profile.dto.ProfileDto;
import com.backend.fitters.user.dto.MinimalUserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public ProfileShippingDto getProfileShipping(Long profileId) {
        return this.profileRepository.getProfileShipping(profileId);
    }

    private String getCurrentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return username;
    }

    public void updateProfile(UpdateProfileRequest request, Long profileId) {
        String username = getCurrentUserName();
        Profile profile = this.profileRepository
                .findById(profileId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!profile.getUser().getUsername().equals(username)) {
            throw new ForbiddenException("Cannot update another person's profile");
        }

        profile.setBio(request.getBio());
        profile.setSite(request.getSite());
        profile.setState(request.getState());
        profile.setAddress(request.getAddress());
        profile.setCountry(request.getCountry());
        profile.setZipCode(request.getZipCode());
        profile.setSpecialities(request.getSpecialities());
        profile.setPricing(request.getPricing());

        this.profileRepository.save(profile);

    }

    public ProfileDto getProfile(Long profileId) {

        Profile profile = this.profileRepository.findById(profileId).orElseThrow(
                () -> new NotFoundException("Profile not found."));

        ProfileFieldsDto profileFields = this.profileRepository.getProfileFields(profileId);
        System.out.println(profileFields.getZipCode());
        return new ProfileDto(profileFields, new MinimalUserDto(
                profile.getUser().getFirstName(),
                profile.getUser().getLastName(),
                profile.getUser().getRole(),
                profile.getUser().getId()));

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
