package com.techelevator.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BlogNotFoundException extends RuntimeException {

    public BlogNotFoundException() {
        super("Blog not found, please try again");
    }
}
