package com.github.jarod.qqconnect.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResult {
    private int ret;
    private String msg;
    private String nickname;
    private String figureurl;
    private String figureurl_1;
    private String figureurl_2;
    private String figureurl_qq_1;
    private String figureurl_qq_2;
    private String gender;

    public int getRet() {
        return ret;
    }

    public String getMsg() {
        return msg;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFigureurl() {
        return figureurl;
    }

    public String getFigureurl_1() {
        return figureurl_1;
    }

    public String getFigureurl_2() {
        return figureurl_2;
    }

    public String getFigureurl_qq_1() {
        return figureurl_qq_1;
    }

    public String getFigureurl_qq_2() {
        return figureurl_qq_2;
    }

    public String getGender() {
        return gender;
    }
}