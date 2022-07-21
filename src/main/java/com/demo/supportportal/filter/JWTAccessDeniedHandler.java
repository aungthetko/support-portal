package com.demo.supportportal.filter;

import com.demo.supportportal.domain.HttpStatusResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static com.demo.supportportal.constants.SecurityConstants.FORBIDDEN_MESSAGE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component 
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpStatusResponse httpStatusResponse = new HttpStatusResponse(UNAUTHORIZED.value(),
                UNAUTHORIZED, UNAUTHORIZED.getReasonPhrase().toUpperCase(), FORBIDDEN_MESSAGE);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(UNAUTHORIZED.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(outputStream, httpStatusResponse);
        outputStream.flush();
    }
}
