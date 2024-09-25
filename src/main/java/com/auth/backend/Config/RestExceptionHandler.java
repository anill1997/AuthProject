package com.auth.backend.Config;

import com.auth.backend.Exceptions.AppException;
import com.auth.backend.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class RestExceptionHandler {

    public ResponseEntity<ErrorDTO> handleException(AppException ex) {
        return ResponseEntity.status(ex.getCode()).body(ErrorDTO.builder().message(ex.getMessage()).build());
    }
}
