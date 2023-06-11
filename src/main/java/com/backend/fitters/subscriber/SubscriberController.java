package com.backend.fitters.subscriber;

import com.backend.fitters.subscriber.response.CreateSubscriberResponse;
import com.backend.fitters.subscriber.request.CreateSubscriberRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscribers")
public class SubscriberController {
    private final SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping
    public ResponseEntity<CreateSubscriberResponse> createSubscriber(
            @RequestBody CreateSubscriberRequest request) {
        this.subscriberService.createSubscriber(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateSubscriberResponse("success"));
    }
}
