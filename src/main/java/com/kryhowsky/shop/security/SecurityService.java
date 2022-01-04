package com.kryhowsky.shop.security;

import com.kryhowsky.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;

    public boolean hasAccessToUser(Long id) {
        try {
            return userService.getCurrentUser().getId().equals(id);
        } catch (EntityNotFoundException e) {
            return false;
        }

    }

}
