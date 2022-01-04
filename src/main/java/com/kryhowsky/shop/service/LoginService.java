package com.kryhowsky.shop.service;

import com.kryhowsky.shop.model.dto.LoginDto;
import com.kryhowsky.shop.model.dto.TokenDto;

public interface LoginService {

    TokenDto authenticateUser(LoginDto loginDto);

}
