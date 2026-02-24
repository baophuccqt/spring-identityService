package org.baophuccqt.springidentityservice.Exception;

import com.nimbusds.jose.JOSEException;
import org.baophuccqt.springidentityservice.DTO.Request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException exception) {
        ApiResponse apiResponse = new ApiResponse();
        ErrorCode errorCode = exception.getErrorCode();

        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse> handleAppException(RuntimeException exception) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage(ErrorCode.INVALID_ERROR_CODE.getMessage());
        apiResponse.setCode(ErrorCode.INVALID_ERROR_CODE.getCode());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_ERROR_CODE;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {

        }

        ApiResponse response = new ApiResponse();

        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = JOSEException.class)
    ResponseEntity<ApiResponse> handleJOSEException(JOSEException exception) {
        ApiResponse response = new ApiResponse();

        response.setMessage(ErrorCode.INVALID_ERROR_CODE.getMessage());
        response.setCode(ErrorCode.INVALID_ERROR_CODE.getCode());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = ParseException.class)
    ResponseEntity<ApiResponse> handleParseException(ParseException exception) {
        ApiResponse response = new ApiResponse();

        response.setMessage(ErrorCode.BAD_TOKEN.getMessage());
        response.setCode(ErrorCode.BAD_PARSE.getCode());

        return ResponseEntity.badRequest().body(response);
    }
}
