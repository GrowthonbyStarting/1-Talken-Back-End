package com.example.talken.oauth2.service;

import com.example.talken.oauth2.dto.KakaoUserInfo;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class Oauth2Service {

    public KakaoUserInfo getUserInfo(String authorizedCode) {
        String accessToken = getAccessToken(authorizedCode);
        KakaoUserInfo userInfo = getUserInfoByToken(accessToken);

        return userInfo;
    }

    private String getAccessToken(String authorizedCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "596a5a8e48ed5c14a7e953802ef7adb2");
        params.add("code", authorizedCode);

        params.add("redirect_uri", "http://3.35.135.64:8080/users/oauth");

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ResponseEntity<JSONObject> apiResponse = rt.postForEntity("https://kauth.kakao.com/oauth/token",
                kakaoTokenRequest, JSONObject.class);
        JSONObject responseBody = apiResponse.getBody();

        String accessToken = (String) responseBody.get("access_token");

        return accessToken;
    }

    private KakaoUserInfo getUserInfoByToken(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        JSONObject body = new JSONObject(response.getBody());
        Long id = body.getLong("id");
        String nickname = body.getJSONObject("properties").getString("nickname");
        String image = body.getJSONObject("kakao_account").getJSONObject("profile").getString("profile_image_url");
        String email = body.getJSONObject("kakao_account").getString("email");

        return new KakaoUserInfo(id, nickname, image, email);
    }
}
