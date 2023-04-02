package com.codingstreams.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String availableToAll() {
        return "I'm publicly accessed by everyone.";
    }

    @GetMapping("/auth")
    public String availableToAuthenticated() {
        return "I'm only accessible by authenticated users.";
    }

    @GetMapping("/admin")
    public String availableToAdmin() {
        return "I'm only accessible by admin.";
    }
}
