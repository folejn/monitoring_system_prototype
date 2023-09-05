package com.springboot.streamservice.controller;

import com.springboot.streamservice.payload.CameraDto;
import com.springboot.streamservice.payload.Message;
import com.springboot.streamservice.service.ICameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class CameraAdminController {
    private final ICameraService cameraService;

    public CameraAdminController(@Autowired ICameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping({"/cameras/", "/cameras"})
    public ResponseEntity<List<CameraDto>> getAllCameras() {
        return ResponseEntity.ok(cameraService.getAllCameras());
    }

    @PostMapping("/cameras/new")
    public ResponseEntity<Message> newCamera(@RequestBody CameraDto cameraDto) {
        cameraService.createNewCamera(cameraDto);
        return ResponseEntity.ok(new Message("New camera has been added"));
    }

    @PutMapping("/cameras/update")
    public ResponseEntity<CameraDto> updateCamera(@RequestBody CameraDto cameraDto) {
        return ResponseEntity.ok(cameraService.updateCamera(cameraDto));
    }

    @DeleteMapping("/cameras/{id}/delete")
    public ResponseEntity<Message> deleteCamera(@PathVariable Long id) {
        cameraService.deleteCamera(id);
        Message message = new Message("Camera has been deleted");
        return ResponseEntity.ok(message);
    }

    @GetMapping({"/cameras/{id}"})
    public ResponseEntity<CameraDto> getCamera(Long id) {
        return ResponseEntity.ok(cameraService.getCamera(id));
    }
}
