package com.springboot.streamservice.service.impl;

import com.springboot.streamservice.entity.Camera;
import com.springboot.streamservice.exception.AlreadyExistsException;
import com.springboot.streamservice.exception.ResourceNotFoundException;
import com.springboot.streamservice.payload.CameraDto;
import com.springboot.streamservice.repository.CameraRepository;
import com.springboot.streamservice.service.ICameraService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CameraService implements ICameraService {
    @Autowired
    CameraRepository cameraRepository;

    @Override
    public List<CameraDto> getAllCameras() {
        return cameraRepository.findAll().stream()
                .map(camera -> mapCameraToDto(camera))
                .collect(Collectors.toList());
    }

    @Override
    public CameraDto createNewCamera(CameraDto cameraDto) {
        Camera camera = null;
        try {
            camera = cameraRepository.saveAndFlush(mapDtoToCamera(cameraDto));
        } catch (Exception e) {
            throw new AlreadyExistsException("Camera already exists");
        }

        return(mapCameraToDto(camera));
    }

    @Override
    public CameraDto updateCamera(CameraDto cameraDto) {
        Camera camera = cameraRepository.findById(cameraDto.getId()).orElseThrow(
                    () -> new ResourceNotFoundException("Camera", "id", cameraDto.getId()
                ));
        camera.setAddress(cameraDto.getAddress());
        camera.setName(cameraDto.getName());
        camera = cameraRepository.save(camera);
        return mapCameraToDto(camera);
    }

    @Override
    public void deleteCamera(Long cameraId) {
        if(cameraRepository.existsById(cameraId)) {
            cameraRepository.deleteById(cameraId);
        } else {
            throw new ResourceNotFoundException("Camera", "id", cameraId);
        }
    }

    @Override
    public CameraDto getCamera(Long id) {
        Camera camera = cameraRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Camera", "id", id)
        );
        return mapCameraToDto(camera);
    }
    private CameraDto mapCameraToDto(Camera camera) {
        return new CameraDto(
                camera.getId(),
                camera.getAddress(),
                camera.getName()
        );
    }

    private Camera mapDtoToCamera(CameraDto cameraDto) {
        Camera camera = new Camera();
        camera.setAddress(cameraDto.getAddress());
        camera.setName(cameraDto.getName());
        return camera;
    }
}
