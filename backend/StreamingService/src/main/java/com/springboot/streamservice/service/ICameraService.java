package com.springboot.streamservice.service;

import com.springboot.streamservice.payload.CameraDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICameraService {
    List<CameraDto> getAllCameras();
    CameraDto createNewCamera(CameraDto cameraDto);
    CameraDto updateCamera(CameraDto cameraDto);
    void deleteCamera(Long cameraId);
    CameraDto getCamera(Long cameraId);
}
