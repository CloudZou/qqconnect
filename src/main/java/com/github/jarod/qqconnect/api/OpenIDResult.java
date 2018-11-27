package com.github.jarod.qqconnect.api;

import java.util.HashMap;
import java.util.Map;

import com.github.jarod.qqconnect.internal.Helper;

public class OpenIDResult {
    private Map<String, String> value;

    public static OpenIDResult parseFromResponse(String response) {
        OpenIDResult r = new OpenIDResult();
        if (response.indexOf("callback") != -1) {
            String jsonStr = response.substring(response.indexOf("{"), response.indexOf("}") + 1);
            try {
                r.value = Helper.json().readValue(jsonStr, HashMap.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            r.value = Helper.parseURLResponse(response);
        }
        return r;
    }

    public int code() {
        return Integer.parseInt(value.getOrDefault("code", "0"));
    }

    public String msg() {
        return value.getOrDefault("msg", "");
    }

    public String getOpenID() {
        return value.get("openid");
    }

    public String toString() {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

}