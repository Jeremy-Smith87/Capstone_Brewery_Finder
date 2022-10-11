package com.techelevator.service;

import com.techelevator.controller.exceptions.BeerNotFoundException;
import com.techelevator.dao.BeerDao;
import com.techelevator.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    @Autowired
    BeerDao beerDao;


    public boolean createBeer(long id, Beer newBeer) {
        boolean result = false;
        try {
            beerDao.createBeer(id, newBeer);
            result = true;
        } catch (BeerNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Beer> returnAllBeers() {
        return beerDao.getAllBeers();
    }

    public Beer returnBeerById(long id) {

        return beerDao.getBeerById(id);

    }

    public List<Beer> returnBeerByBrewer(long id) {
        return beerDao.getBeerByBrewer(id);

    }

    public boolean checkBeerUpdate(long id, Beer beerToChange) {
        return beerDao.updateBeer(id, beerToChange);
    }

    public boolean checkBeerDelete(long id) {
        return beerDao.deleteBeer(id);
    }


}
