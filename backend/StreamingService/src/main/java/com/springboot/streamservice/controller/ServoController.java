package com.springboot.streamservice.controller;

import com.springboot.streamservice.payload.Message;
import com.springboot.streamservice.service.IServoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/camera")
public class ServoController {

    private final IServoService servoService;

    public ServoController(@Autowired IServoService servoService) {
        this.servoService = servoService;
    }

    @GetMapping("/{id}/servo/{direction}")
    public ResponseEntity<Message> rotate(@PathVariable Long id, @PathVariable String direction) {
        if (servoService.move(id, direction) == 200)
            return ResponseEntity.ok(new Message("Camera rotated"));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Camera error"));
    }

}
