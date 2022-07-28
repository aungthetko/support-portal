package com.demo.supportportal.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.demo.supportportal.domain.HttpStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandling {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact to your administrator";
    private static final String METHOD_IS_NOT_ALLOWED = "This method is not allowed on this endpoint. Please send a '%s' request.";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred when processing the request";
    private static final String INCORRECT_CREDENTIALS = "Username / Password incorrect. Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. Please contact to your administrator";
    private static final String ERROR_PROCESSING_FILE = "Error occurred when processing file";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpStatusResponse> accountDisabledException(){
        return createHttpResponse(HttpStatus.BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpStatusResponse> badCredentialsException(){
        return createHttpResponse(HttpStatus.BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpStatusResponse> accessDeniedException(){
        return createHttpResponse(HttpStatus.BAD_REQUEST, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpStatusResponse> lockedException(){
        return createHttpResponse(HttpStatus.UNAUTHORIZED, ACCOUNT_LOCKED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpStatusResponse> tokenExpiredException(TokenExpiredException exception){
        return createHttpResponse(HttpStatus.UNAUTHORIZED, exception.getMessage().toUpperCase());
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<HttpStatusResponse> emailExistException(EmailExistException exception){
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage().toUpperCase());
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpStatusResponse> notFoundException(NoResultException exception){
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage().toUpperCase());
    }

    @ExceptionHandler(UserExistException.class )
    public ResponseEntity<HttpStatusResponse> usernameExistException(UserExistException userExistException){
        return createHttpResponse(HttpStatus.BAD_REQUEST, userExistException.getMessage().toUpperCase());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpStatusResponse> emailNotFoundException(EmailNotFoundException emailNotFoundException){
        return createHttpResponse(HttpStatus.BAD_REQUEST, emailNotFoundException.getMessage().toUpperCase());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpStatusResponse> userNotFoundException(UserNotFoundException userNotFoundException){
        return createHttpResponse(HttpStatus.BAD_REQUEST, userNotFoundException.getMessage().toUpperCase());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpStatusResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception){
        HttpMethod supportMethod = Objects.requireNonNull(exception.getSupportedHttpMethods().iterator().next());
        return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportMethod));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatusResponse> internalServerErrorException(Exception exception){
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpStatusResponse> ioException(IOException exception){
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    private ResponseEntity<HttpStatusResponse> createHttpResponse(HttpStatus httpStatus, String message){
        HttpStatusResponse httpResponse = new HttpStatusResponse(LocalDateTime.now(), httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message);
        return new ResponseEntity<>(httpResponse, httpStatus);
    }
}
