package com.techelevator.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends Exception {
    public NotAuthorizedException() {
        super("You're not authorized to make this change, please reach out to your admin if you believe you received this message in error");
    }
}
