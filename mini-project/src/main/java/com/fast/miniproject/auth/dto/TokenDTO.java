package com.fast.miniproject.auth.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TokenDTO {

    private final String accessToken;

    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
