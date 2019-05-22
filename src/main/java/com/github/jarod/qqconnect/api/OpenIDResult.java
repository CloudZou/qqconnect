package com.github.jarod.qqconnect.api;

import java.util.Map;

import com.github.jarod.qqconnect.internal.Helper;

public class OpenIDResult {
    private Map<String, Object> value;

    public static OpenIDResult parseFromResponse(String response) {
        OpenIDResult r = new OpenIDResult();
        r.value = Helper.parseCallbackResponse(response);
        return r;
    }

    public int error() {
        return (Integer) value.getOrDefault("error", 0);
    }

    public String errorDescription() {
        return (String) value.getOrDefault("error_description", "");
    }

    public String getOpenID() {
        return (String) value.get("openid");
    }

    public String getUnionID() {
        return (String) value.get("unionid");
    }

    @Override
    public String toString() {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

}