package com.techelevator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Favorites {
    private long favoriteId;
    private long userId;
    private long breweryId;
    private long beerId;

}
