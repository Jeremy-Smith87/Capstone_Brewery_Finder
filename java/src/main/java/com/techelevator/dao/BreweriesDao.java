package com.techelevator.dao;

import com.techelevator.model.Breweries;

import java.util.List;

public interface BreweriesDao {

    boolean createBrewery(Breweries newBrewery);

    boolean deleteBrewery(long id);

    boolean updateBrewery(long id, Breweries breweryToChange);

    List<Breweries> getAllBreweries();

    Breweries getBreweryById(long id);

    Breweries getBreweryByUserId(long userId);
}
