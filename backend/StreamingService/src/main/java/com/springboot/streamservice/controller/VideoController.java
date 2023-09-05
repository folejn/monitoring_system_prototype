package com.springboot.streamservice.controller;

import com.github.kokorin.jaffree.StreamType;
import com.github.kokorin.jaffree.ffmpeg.PipeOutput;
import com.springboot.streamservice.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    private final IVideoService videoService;

    public VideoController(@Autowired IVideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping(value = "/{id}/live.mp4")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> livestream(@PathVariable("id") Long cameraId) {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(os -> {
                    videoService.getVideoFromCamera(cameraId)
                            .addOutput(PipeOutput.pumpTo(os)
                                    .disableStream(StreamType.AUDIO)
                                    .disableStream(StreamType.SUBTITLE)
                                    .disableStream(StreamType.DATA)
                                    .setFrameCount(StreamType.VIDEO, 100L)
                                    //1 frame every 10 seconds
                                    .setFrameRate(0.3)
                                    .setDuration(1, TimeUnit.HOURS)
                                    .setFormat("ismv"))
                            .addArgument("-nostdin")
                            .execute();
                });

    }
}


