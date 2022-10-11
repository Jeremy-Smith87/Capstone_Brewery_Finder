package com.techelevator.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Breweries {

    private long breweryId;
    @NotBlank(message = "The field name is required.")
    private String name;
    @NotBlank(message = "The field name is required.")
    private String address;
    private long userId;
    @NotBlank(message = "The field name is required.")
    private String city;
    @NotBlank(message = "The field name is required.")
    private String stateAbbreviation;
    @NotBlank(message = "The field name is required.")
    private String zipcode;
    private String phoneNumber;
    private String description;
    private String logoUrl;
    private String hours;
    private String website;


}
