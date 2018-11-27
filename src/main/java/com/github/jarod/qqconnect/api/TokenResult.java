package com.github.jarod.qqconnect.api;

import java.util.Map;

import com.github.jarod.qqconnect.internal.Helper;

public class TokenResult {
    private Map<String, String> value;

    public static TokenResult parseFromResponse(String response) {
        TokenResult r = new TokenResult();
        r.value = Helper.parseURLResponse(response);
        return r;
    }

    public int code() {
        return Integer.parseInt(value.getOrDefault("code", "0"));
    }

    public String msg() {
        return value.getOrDefault("msg", "");
    }

    public String accessToken() {
        return value.get("access_token");
    }

    public int expiresIn() {
        return Integer.parseInt(value.getOrDefault("expires_in", "0"));
    }

    public String refreshToken() {
        return value.get("refresh_token");
    }

    public String toString() {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}