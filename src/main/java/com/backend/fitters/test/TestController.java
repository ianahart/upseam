package com.backend.fitters.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/heartbeat")

public class TestController {

    @GetMapping
    public String testConnection() {
        return "Secured endpoint";
    }
}
