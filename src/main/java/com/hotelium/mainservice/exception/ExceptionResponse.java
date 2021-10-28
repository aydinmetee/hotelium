package com.hotelium.mainservice.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Mete Aydin
 * @date 28.10.2021
 */
@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private LocalDateTime localDateTime;

    public ExceptionResponse(String message) {
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }
}
