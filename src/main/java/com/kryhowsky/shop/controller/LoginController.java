package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.model.dto.LoginDto;
import com.kryhowsky.shop.model.dto.TokenDto;
import com.kryhowsky.shop.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    @Operation(description = "Allows to login.")
    public TokenDto authenticateUser(@RequestBody LoginDto loginDto) {

        return loginService.authenticateUser(loginDto);

    }

}
