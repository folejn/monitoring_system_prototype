package com.springboot.streamservice.utils;

import lombok.Getter;

public class ProtocolUtils {
    @Getter private static final String HTTP_PREFIX = "http://";
    @Getter private static final String HTTP_PORT = ":80/";

    @Getter private static final String RTSP_PREFIX = "rtsp://";
    @Getter private static final String RTSP_PORT = ":8080/";
}
