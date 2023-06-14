package com.backend.fitters.shipping;

import com.backend.fitters.shipping.request.CreateShippingRequest;
import com.backend.fitters.shipping.response.CreateShippingResponse;
import com.backend.fitters.shipping.response.GetShippingResponse;
import com.backend.fitters.shipping.response.RemoveShippingResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shippings")
public class ShippingController {

    private final ShippingService shippingService;

    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping
    public ResponseEntity<GetShippingResponse> getShipping(@RequestParam("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(

                new GetShippingResponse("success", this.shippingService.getShipping(userId)));
    }

    @PostMapping
    public ResponseEntity<CreateShippingResponse> createShipping(@RequestBody CreateShippingRequest request) {
        this.shippingService.createShipping(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateShippingResponse("success"));
    }

    @DeleteMapping("/{shippingId}")
    public ResponseEntity<RemoveShippingResponse> removeShipping(@PathVariable("shippingId") Long shippingId) {
        this.shippingService.removeShipping(shippingId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RemoveShippingResponse("success"));
    }
}
