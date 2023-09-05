package com.springboot.streamservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CameraDto {

    private long id;

    private String address;

    private String name;

}
