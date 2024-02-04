package com.example.userservice.service.impl;

import com.example.userservice.entity.User;
import com.example.userservice.jwt.JwtUserDetails;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final List<String> verificationsCodes = List.of(
            "DCQCQCQSDQDQWE",
            "HDHDGHDFHDFGDF",
            "26425624624626",
            "SVXCVXCVCXVVAQ",
            "PYOUIRPOKHPRYK",
            "QZTEHETHETTEHT",
            "DBDNM>M!N#!@MA",
            "123AERQW912AET"
    );

    @Override
    @Transactional
    public void signUp(String email) {

        String code = verificationsCodes.get(new Random().nextInt(verificationsCodes.size()));

        User user = new User();
        user.setEmail(email);
        user.setVerificationCode(code);

        userRepository.save(user);

        kafkaTemplate.send("email_details", code);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new JwtUserDetails(userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid email")));
    }
}
