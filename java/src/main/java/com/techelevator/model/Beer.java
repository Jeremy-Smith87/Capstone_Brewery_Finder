package com.techelevator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Beer {

    private long beerId;
    @NotBlank(message = "The field name is required.")
    private long breweryId;
    @NotBlank(message = "The field name is required.")
    private String beerName;
    private BigDecimal avgRating;
    private String description;
    private String imageUrl;
    private float abv;
    private String beerType;
    private String hops;
    private int ibu;


}
