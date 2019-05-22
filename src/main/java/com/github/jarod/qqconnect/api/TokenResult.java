package com.github.jarod.qqconnect.api;

import java.util.Map;

import com.github.jarod.qqconnect.internal.Helper;

public class TokenResult {
    private Map<String, Object> value;

    public static TokenResult parseFromResponse(String response) {
        TokenResult r = new TokenResult();
        r.value = Helper.parseResponse(response);
        return r;
    }

    public int error() {
        return (Integer) value.getOrDefault("error", 0);
    }

    public String errorDescription() {
        return (String) value.getOrDefault("error_description", "");
    }

    public String msg() {
        return (String) value.getOrDefault("msg", "");
    }

    public String accessToken() {
        return (String) value.get("access_token");
    }

    public int expiresIn() {
        return (Integer) value.getOrDefault("expires_in", 0);
    }

    public String refreshToken() {
        return (String) value.get("refresh_token");
    }

    @Override
    public String toString() {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}