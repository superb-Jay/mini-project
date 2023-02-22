package com.fast.miniproject.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Builder
@ToString
public class TokenDTO {

    private final String accessToken;
    private final String refreshToken;


}
