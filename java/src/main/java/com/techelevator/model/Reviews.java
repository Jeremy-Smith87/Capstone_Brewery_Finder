package com.techelevator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reviews {

    private long reviewId;
    private long userId;
    private String username;
    private int rating;
    private String description;
    private LocalDate createDate;
    private long beerId;
    private long breweryId;

}
