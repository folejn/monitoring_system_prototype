package com.springboot.streamservice.service.impl;

import com.springboot.streamservice.entity.Camera;
import com.springboot.streamservice.exception.APIException;
import com.springboot.streamservice.exception.ResourceNotFoundException;
import com.springboot.streamservice.repository.CameraRepository;
import com.springboot.streamservice.service.IServoService;
import com.springboot.streamservice.utils.ProtocolUtils;
import com.springboot.streamservice.utils.ServoConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class ServoService implements IServoService {
    @Autowired
    private CameraRepository cameraRepository;

    public Integer move(Long id, String direction){

        if(!direction.chars().allMatch(Character::isLetter)) {
            throw new APIException(HttpStatus.FORBIDDEN, "Invalid endpoint");
        }

        Camera camera = cameraRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Camera", "id", id)
        );
        String httpUrl = ProtocolUtils.getHTTP_PREFIX() +
                camera.getAddress() + ProtocolUtils.getHTTP_PORT() + direction;

        try {
            return ServoConnector.connect(httpUrl);
        } catch (Exception e) {
            throw new APIException(HttpStatus.NOT_FOUND, "Camera not found");
        }

    }
}
