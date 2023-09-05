package com.springboot.streamservice.payload;

public class JWTAuthResponse extends UserDto{
    private String accessToken;
    private String tokenType = "Bearer";

    public JWTAuthResponse(String accessToken, UserDto userDto) {
        super(userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getRoles());
        this.accessToken = accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
