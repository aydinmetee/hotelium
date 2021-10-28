package com.hotelium.mainservice.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @date 28.10.2021
 */
@Setter
@Getter
public class LoginExecutionException extends RuntimeException {

    private String message;

    public LoginExecutionException(String message) {
        this.message = message;
    }

}