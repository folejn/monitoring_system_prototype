package com.springboot.streamservice.repository;

import com.springboot.streamservice.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CameraRepository extends JpaRepository<Camera, Long> {
    Optional<Camera> findByName(String name);
}
