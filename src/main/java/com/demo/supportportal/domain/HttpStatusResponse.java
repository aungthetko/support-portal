package com.demo.supportportal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpStatusResponse {

    private int statusCode;
    private HttpStatus status;
    private String reason;
    private String message;

}
