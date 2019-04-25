package com.github.jarod.qqconnect.api;

import java.util.HashMap;
import java.util.Map;

import com.github.jarod.qqconnect.internal.Helper;

public class OpenIDResult {
    private Map<String, String> value;

    public static OpenIDResult parseFromResponse(String response) {
        OpenIDResult r = new OpenIDResult();
        String jsonStr = response.substring(response.indexOf("{"), response.indexOf("}") + 1);
        try {
            r.value = Helper.json().readValue(jsonStr, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    public int error() {
        return Integer.parseInt(value.getOrDefault("error", "0"));
    }

    public String errorDescription() {
        return value.getOrDefault("error_description", "");
    }

    public String getOpenID() {
        return value.get("openid");
    }

    public String getUnionID() {
        return value.get("unionid");
    }

    @Override
    public String toString() {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

}