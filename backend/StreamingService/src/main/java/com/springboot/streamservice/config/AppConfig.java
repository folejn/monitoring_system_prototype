package com.springboot.streamservice.config;

import com.springboot.streamservice.service.ICameraService;
import com.springboot.streamservice.service.IServoService;
import com.springboot.streamservice.service.IUserService;
import com.springboot.streamservice.service.IVideoService;
import com.springboot.streamservice.service.impl.CameraService;
import com.springboot.streamservice.service.impl.ServoService;
import com.springboot.streamservice.service.impl.UserService;
import com.springboot.streamservice.service.impl.VideoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public IVideoService videoService() {
        return new VideoService();
    }

    @Bean
    public ICameraService cameraService() {
        return new CameraService();
    }

    @Bean
    public IUserService userService() { return new UserService(); }

    @Bean
    public IServoService servoService() { return new ServoService(); }
}
