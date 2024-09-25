package com.auth.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrorDTO {

    private String message;
//    public ErrorDTO(String unauthorized_path) {
//
//    }
}
