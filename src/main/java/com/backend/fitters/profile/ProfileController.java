package com.backend.fitters.profile;

import java.util.Map;

import com.backend.fitters.profile.request.UpdateProfileRequest;
import com.backend.fitters.profile.request.ProfilePhotoRequest;
import com.backend.fitters.profile.response.GetProfileShippingResponse;
import com.backend.fitters.profile.response.ProfilePhotoResponse;
import com.backend.fitters.profile.response.ProfileResponse;
import com.backend.fitters.profile.response.UpdateProfileResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<UpdateProfileResponse> updateProfile(@PathVariable("profileId") Long profileId,
            @RequestBody UpdateProfileRequest request) {

        this.profileService.updateProfile(request, profileId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new UpdateProfileResponse("success"));
    }

    @GetMapping("/{profileId}/shipping")
    public ResponseEntity<GetProfileShippingResponse> getProfileShipping(@PathVariable("profileId") Long profileId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GetProfileShippingResponse("success", this.profileService.getProfileShipping(profileId)));
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable("profileId") Long profileId) {
        this.profileService.getProfile(profileId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ProfileResponse(this.profileService.getProfile(profileId), "Success"));
    }

    @PostMapping("/upload")
    public ResponseEntity<ProfilePhotoResponse> uploadPhoto(ProfilePhotoRequest request) {

        Map<String, String> uploadedPhoto = this.profileService.uploadPhoto(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ProfilePhotoResponse(uploadedPhoto.get("url"), uploadedPhoto.get("fileName")));

    }
}
