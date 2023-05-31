package com.backend.fitters.cloth;

import java.util.Optional;

import com.backend.fitters.cloth.request.CreateClothRequest;
import com.backend.fitters.cloth.response.CreateClothResponse;
import com.backend.fitters.cloth.response.FullClothResponse;
import com.backend.fitters.cloth.response.GetClothesResponse;
import com.backend.fitters.cloth.response.SyncClothResponse;
import com.backend.fitters.cloth.response.UpdateClothResponse;
import com.backend.fitters.cloth.request.UpdateClothRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{clothId}")
    public ResponseEntity<FullClothResponse> getCloth(@PathVariable("clothId") Long clothId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new FullClothResponse("success", this.clothService.getCloth(clothId)));
    }

    @PatchMapping("/{clothId}")
    public ResponseEntity<UpdateClothResponse> updateCloth(
            @PathVariable("clothId") Long clothId,
            UpdateClothRequest request) {
        this.clothService.updateCloth(request, clothId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new UpdateClothResponse("success"));
    }

    @GetMapping("/sync")
    public ResponseEntity<SyncClothResponse> syncClothes(@RequestParam("clothId") String clothId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SyncClothResponse(this.clothService.syncCloth(clothId), "success"));
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
                    .body(new GetClothesResponse(this.clothService.getAllClothes(page, pageSize, direction)));
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
