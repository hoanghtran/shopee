package com.act.vn.shopeeApplication.exception;

import com.act.vn.shopeeApplication.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(InvalidCredentialsException.class)
//    public ResponseEntity<ApiResponse> hanldeInvalidCredentialsException
//            (InvalidCredentialsException exception, WebRequest request) {
//
//        ApiResponse errorResponse = ApiResponse.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .message(exception.getMessage())
//                .body(exception.getCause())
//                .build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(errorResponse);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleAllExceptions
            (WebRequest request, Exception exception,
             MethodArgumentNotValidException validException) {

        String errorMessage = validException.
                getBindingResult()
                .getAllErrors().get(0)
                .getDefaultMessage();

        ApiResponse errorResponse = ApiResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(errorMessage)
                .body(validException.getBody().getDetail())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundException
            (NotFoundException exception, WebRequest request) {
        ApiResponse errorResponse = ApiResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


}
