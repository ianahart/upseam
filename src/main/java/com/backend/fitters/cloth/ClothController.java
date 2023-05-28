package com.backend.fitters.cloth;

import com.backend.fitters.cloth.request.CreateClothRequest;
import com.backend.fitters.cloth.response.CreateClothResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/clothes")
public class ClothController {

    private final ClothService clothService;

    @Autowired
    public ClothController(ClothService clothService) {
        this.clothService = clothService;
    }

    @PostMapping
    public ResponseEntity<CreateClothResponse> createCloth(CreateClothRequest request) {
        this.clothService.createCloth(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateClothResponse("success"));
    }

}
