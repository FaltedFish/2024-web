package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

import java.math.BigDecimal;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);

    BigDecimal getAmount(Long id);
}
