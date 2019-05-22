package com.github.jarod.qqconnect.internal;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {
    private final static ObjectMapper json = new ObjectMapper();

    public static Map<String, Object> parseURLResponse(String url) {
        try {
            Map<String, Object> r = new HashMap<>();
            for (String pair : url.split("&", -1)) {
                String[] kv = pair.split("=", 2);
                r.put(kv[0], kv[1]);
            }
            return r;
        } catch (Exception ex) {
            throw new IllegalArgumentException("url=" + url);
        }
    }

    // callback( {"error":100019,"error_description":"code to access token error"}
    // );
    public static Map<String, Object> parseCallbackResponse(String body) {
        String jsonStr = body.substring(body.indexOf("{"), body.indexOf("}") + 1);
        try {
            return Helper.json().readValue(jsonStr, HashMap.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(body);
        }
    }

    public static Map<String, Object> parseResponse(String body) {
        if (body.startsWith("callback(")) {
            return parseCallbackResponse(body);
        } else {
            return parseURLResponse(body);
        }
    }

    public static ObjectMapper json() {
        return json;
    }
}