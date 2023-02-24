package com.fast.miniproject.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorResponseDTO {
    private int errorCode;
    private String message;

    public ResponseDTO toResponse() {
        return new ResponseDTO<>(this.errorCode, this.message, null);
    }
}