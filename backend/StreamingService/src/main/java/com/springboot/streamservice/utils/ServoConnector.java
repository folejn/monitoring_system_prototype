package com.springboot.streamservice.utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class ServoConnector {
    public static Integer connect(String httpUrl) throws Exception{
        URL url = new URL(httpUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(5000);
        return con.getResponseCode();
    }
}
