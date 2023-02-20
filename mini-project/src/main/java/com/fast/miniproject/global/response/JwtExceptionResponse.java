package com.fast.miniproject.global.response;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JwtExceptionResponse {

    private String message;
    private HttpStatus unauthorized;


    public String convertToJson() throws JsonProcessingException {

        JwtExceptionResponse jwtExceptionResponse = new JwtExceptionResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jwtExceptionResponse);
    }

}
