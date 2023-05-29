package com.backend.fitters.cloth;

import java.util.Optional;

import com.backend.fitters.cloth.request.CreateClothRequest;
import com.backend.fitters.cloth.response.CreateClothResponse;
import com.backend.fitters.cloth.response.GetClothesResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/clothes")
public class ClothController {

    private final ClothService clothService;

    @Autowired
    public ClothController(ClothService clothService) {
        this.clothService = clothService;
    }

    @GetMapping
    public ResponseEntity<GetClothesResponse> getClothes(
            @RequestParam("fetchType") String fetchType,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("direction") String direction,
            @RequestParam(name = "userId", required = false) Long userId) {
        if (fetchType.equals("user")) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new GetClothesResponse(
                            this.clothService.getUserClothes(userId, page, pageSize, direction)));

        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new GetClothesResponse(this.clothService.getUserClothes(userId, page, pageSize, direction)));
            // fetch all users clothes
            // same as above query without the userid = :userId
        }

    }

    @PostMapping
    public ResponseEntity<CreateClothResponse> createCloth(CreateClothRequest request) {
        this.clothService.createCloth(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateClothResponse("success"));
    }

}
