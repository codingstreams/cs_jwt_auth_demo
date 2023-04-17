package com.codingstreams.jwtauth.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String availableToAll() {
        return "I'm publicly accessed by everyone.";
    }

    @GetMapping("/user")
    public String availableToAuthenticated() {
        return "I'm only accessible by authenticated users.";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public String availableToAdmin() {
        return "I'm only accessible by admin.";
    }

    @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    @GetMapping("/editor")
    public String editor(){
        return "I'm only accessible by editor and admin.";
    }
}
