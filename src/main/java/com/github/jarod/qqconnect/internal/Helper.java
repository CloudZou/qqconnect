package com.github.jarod.qqconnect.internal;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {
    private final static ObjectMapper json = new ObjectMapper();

    public static Map<String, String> parseURLResponse(String url) {
        Map<String,String> r = new HashMap<>();
        for (String pair : url.split("&", -1)) {
            String[] kv = pair.split("=", 2);
            r.put(kv[0], kv[1]);
        }
        return r;
    }


    public static ObjectMapper json() {
        return json;
    }
}