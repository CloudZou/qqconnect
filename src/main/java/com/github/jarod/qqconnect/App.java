package com.github.jarod.qqconnect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.github.jarod.qqconnect.api.OpenIDResult;
import com.github.jarod.qqconnect.api.TokenResult;
import com.github.jarod.qqconnect.api.UserInfoResult;
import com.github.jarod.qqconnect.internal.Helper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class App {
    private static final String GRAPH_BASE = "https://graph.qq.com";
    private final String appId;
    private final String appKey;
    private final String redirectUrl;
    private String scope = "all";
    private final OkHttpClient httpClient = new OkHttpClient();

    public App(String appId, String appKey, String redirectUrl) {
        this.appId = appId;
        this.appKey = appKey;
        this.redirectUrl = redirectUrl;

    }

    public String getAuthorizeURL() {
        try {
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            return String.format("%s/oauth2.0/authorize?response_type=code&scope=%s&client_id=%s&redirect_uri=%s",
                    GRAPH_BASE, scope, appId, encodedRedirectUrl);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public TokenResult requestToken(String code) {
        String encodedRedirectUrl;
        try {
            encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            encodedRedirectUrl = redirectUrl;
        }
        Request request = new Request.Builder().get().url(String.format(
                "%s/oauth2.0/token?grant_type=authorization_code&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s",
                GRAPH_BASE, appId, appKey, code, encodedRedirectUrl)).build();
        try (Response response = httpClient.newCall(request).execute()) {
            return TokenResult.parseFromResponse(response.body().string());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public OpenIDResult requestOpenID(String accessToken) {
        Request request = new Request.Builder().get()
                .url(String.format("%s/oauth2.0/me?access_token=%s", GRAPH_BASE, accessToken)).build();
        try (Response response = httpClient.newCall(request).execute()) {
            return OpenIDResult.parseFromResponse(response.body().string());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public App setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public UserInfoResult requestUserInfo(String accessToken, String openID) {
        String url = String.format("%s/user/get_user_info?access_token=%s&oauth_consumer_key=%s&openid=%s", GRAPH_BASE,
                accessToken, appId, openID);
        Request request = new Request.Builder().get().url(url).build();
        try (Response response = httpClient.newCall(request).execute()) {
            return Helper.json().readValue(response.body().byteStream(), UserInfoResult.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}