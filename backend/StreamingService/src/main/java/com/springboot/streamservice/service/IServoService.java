package com.springboot.streamservice.service;

import org.springframework.stereotype.Service;

@Service
public interface IServoService {
    Integer move(Long id, String direction);
}
