package com.fast.miniproject.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
public class TokenDTO {

    private final String accessToken;
    private final String refreshToken;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @ApiModel(value = "리프레시 토큰 입력")
    public static class RefreshTokenReqDTO {

        @ApiModelProperty(value = "리프레시 토큰")
        private String refreshToken;


    }


}
