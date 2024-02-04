package com.example.userservice.controller;

import com.example.userservice.jwt.JwtUserDetails;
import com.example.userservice.service.AuthenticationService;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestParam String email) {
        userService.signUp(email);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestParam String email, @RequestParam String verificationCode) {

        return ResponseEntity.ok(authenticationService.signIn(email, verificationCode));
    }

    @PostMapping("/some")
    public ResponseEntity<String> signIn(@AuthenticationPrincipal JwtUserDetails jwtUserDetails) {

        return ResponseEntity.ok().build();
    }
}
