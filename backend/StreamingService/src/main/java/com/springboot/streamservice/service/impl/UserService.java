package com.springboot.streamservice.service.impl;

import com.springboot.streamservice.entity.User;
import com.springboot.streamservice.payload.UserDto;
import com.springboot.streamservice.repository.UserRepository;
import com.springboot.streamservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getUserByEmailOrUsername(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));
        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
        return mapUserToDto(user, roles);
    }

    UserDto mapUserToDto(User user, List<String> roles) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), roles);
    }

}