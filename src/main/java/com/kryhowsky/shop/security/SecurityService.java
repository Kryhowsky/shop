package com.kryhowsky.shop.security;

import com.kryhowsky.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;

    public boolean hasAccessToUser(Long id) {
        return userService.getCurrentUser().getId().equals(id);
    }

}
