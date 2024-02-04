package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.jwt.JwtService;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Transactional
    public String signIn(String email, String verificationCode) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid email"));

        if (!user.getVerificationCode().equals(verificationCode)) {
            throw new IllegalArgumentException("Invalid verification code");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, email)
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        return jwtService.generateToken(userDetails);
    }
}
