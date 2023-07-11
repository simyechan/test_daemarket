package com.example.daeMarket.domain.user.facade;

import com.example.daeMarket.domain.user.domain.User;
import com.example.daeMarket.domain.user.domain.repository.UserRepository;
import com.example.daeMarket.domain.user.exception.AlreadyEmailExistsException;
import com.example.daeMarket.domain.user.exception.AlreadyNameExistException;
import com.example.daeMarket.domain.user.exception.AlreadyUserExistException;
import com.example.daeMarket.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void checkUserExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw AlreadyUserExistException.EXCEPTION;
        }
    }

    public void checkNameExists(String nickname) {
        if (userRepository.findByName(nickname).isPresent()) {
            throw AlreadyNameExistException.EXCEPTION;
        }
    }

    public void checkEmailExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw AlreadyEmailExistsException.EXCEPTION;
        }
    }
}
