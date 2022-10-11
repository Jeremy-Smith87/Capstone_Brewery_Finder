package com.techelevator.dao;

import com.techelevator.model.Beer;

import java.util.List;

public interface BeerDao {

    boolean createBeer(long id, Beer newBeer);

    List<Beer> getAllBeers();

    Beer getBeerById(long id);

    List<Beer> getBeerByBrewer(long id);

    boolean updateBeer(long id, Beer beerToChange);

    boolean deleteBeer(long id);
}
