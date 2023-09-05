package com.springboot.streamservice.service;

import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import org.springframework.stereotype.Service;

@Service
public interface IVideoService {
    FFmpeg getVideoFromCamera(Long cameraId);
}
