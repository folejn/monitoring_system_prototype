package com.springboot.streamservice.service;

import com.springboot.streamservice.payload.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    UserDto getUserByEmailOrUsername(String emailOrUsername);
}
