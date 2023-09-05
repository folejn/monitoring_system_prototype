package com.springboot.streamservice.controller;

import com.springboot.streamservice.payload.CameraDto;
import com.springboot.streamservice.service.ICameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class CameraUserController {
    private final ICameraService cameraService;

    public CameraUserController(@Autowired ICameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping({"/cameras/", "/cameras"})
    public ResponseEntity<List<CameraDto>> getAllCameras() {
        return ResponseEntity.ok(cameraService.getAllCameras());
    }

}
