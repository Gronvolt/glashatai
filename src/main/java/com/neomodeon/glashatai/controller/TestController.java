package com.neomodeon.glashatai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> test(Authentication authentication) {
        return ResponseEntity.ok(authentication.getName());
    }
}
