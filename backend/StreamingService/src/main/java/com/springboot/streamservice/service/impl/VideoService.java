package com.springboot.streamservice.service.impl;

import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.springboot.streamservice.entity.Camera;
import com.springboot.streamservice.exception.ResourceNotFoundException;
import com.springboot.streamservice.repository.CameraRepository;
import com.springboot.streamservice.service.IVideoService;
import com.springboot.streamservice.utils.ProtocolUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class VideoService implements IVideoService {
    @Autowired
    private CameraRepository cameraRepository;

    public FFmpeg getVideoFromCamera(Long cameraId) {
        Camera camera = cameraRepository.findById(cameraId).orElseThrow(
                () -> new ResourceNotFoundException("Camera", "id", cameraId)
        );

        String rtspUrl = ProtocolUtils.getRTSP_PREFIX() + camera.getAddress() + ProtocolUtils.getRTSP_PORT();

        return FFmpeg.atPath()
                .addArgument("-re")
                .addArguments("-acodec", "pcm_s16le")
                .addArguments("-rtsp_transport", "udp")
                .addArguments("-i", rtspUrl)
                .addArguments("-vcodec", "copy")
                .addArguments("-af", "asetrate=22050")
                .addArguments("-acodec", "aac")
                .addArguments("-b:a", "96k" )
                .addArguments("-movflags", "frag_keyframe+empty_moov");
    }
}
