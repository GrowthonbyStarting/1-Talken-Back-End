package com.example.talken.oauth2.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class KakaoUserInfo {

    private Long id;
    private String profileNickname;
    private String profileImageUrl;
    private String 	accountEmail;

}
