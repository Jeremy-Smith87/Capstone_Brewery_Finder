package com.techelevator.service;

import com.techelevator.controller.exceptions.BreweryNotFoundException;
import com.techelevator.dao.BeerDao;
import com.techelevator.dao.BreweriesDao;
import com.techelevator.model.Breweries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweriesService {

    @Autowired
    BreweriesDao breweriesDao;

    public boolean createBrewery(Breweries newBrewery) {
        boolean result = false;
        try {
            breweriesDao.createBrewery(newBrewery);
            result = true;
        } catch (BreweryNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Breweries> returnAllBreweries() {
        return breweriesDao.getAllBreweries();
    }

    public Breweries returnBreweriesById(long id) {
        return breweriesDao.getBreweryById(id);
    }


    public boolean updateBrewery(long id, Breweries breweryToChange) {
        return breweriesDao.updateBrewery(id, breweryToChange);
    }

    public boolean checkDeleteBrewery(long id) {
        return breweriesDao.deleteBrewery(id);
    }


}
