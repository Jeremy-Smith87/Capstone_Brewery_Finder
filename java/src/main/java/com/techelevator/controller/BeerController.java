package com.techelevator.controller;

import com.techelevator.controller.exceptions.BeerNotFoundException;
import com.techelevator.controller.exceptions.NotAuthorizedException;
import com.techelevator.model.Beer;
import com.techelevator.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class BeerController {

    @Autowired
    private BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @PreAuthorize("hasRole('ROLE_BREWER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "brewer/{breweryId}/addBeer")
    public boolean addBeer(@PathVariable long id, @Valid @RequestBody Beer beer) {
        return beerService.createBeer(id, beer);
    }


    @PermitAll
    @RequestMapping(path = "/beer", method = RequestMethod.GET)
    public List<Beer> getAllBeer() {
        return beerService.returnAllBeers();

    }

    @PermitAll
    @RequestMapping(path = "/beer/{id}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable long id) throws BeerNotFoundException {
        return beerService.returnBeerById(id);

    }

    @PermitAll
    @RequestMapping(path = "/breweries/{breweryId}/beers", method = RequestMethod.GET)
    public List<Beer> getBeerByBreweryID(@PathVariable Long breweryId) {
        return beerService.returnBeerByBrewer(breweryId);
    }


    @PreAuthorize("hasRole('ROLE_BREWER')")
    @RequestMapping(path = "brewer/beer/{id}", method = RequestMethod.PUT)
    public boolean updateBeer(@PathVariable long id, @Valid @RequestBody Beer beerToChange) throws NotAuthorizedException {
        return beerService.checkBeerUpdate(id, beerToChange);
    }

    @PreAuthorize("hasRole('ROLE_BREWER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "brewer/beer/{id}", method = RequestMethod.DELETE)
    public boolean removeBeer(@PathVariable long id) {
        if (beerService.returnBeerById(id) != null) {
            return beerService.checkBeerDelete(id);
        } else return false;
    }


}
