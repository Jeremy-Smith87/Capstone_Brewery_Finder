package com.techelevator.controller.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeerNotFoundException extends RuntimeException {

    public BeerNotFoundException(){
        super("beer not found, please try again");
    }
}
