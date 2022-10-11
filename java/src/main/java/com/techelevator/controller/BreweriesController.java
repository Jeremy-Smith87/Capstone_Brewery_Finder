package com.techelevator.controller;

import com.techelevator.controller.exceptions.BreweryNotFoundException;
import com.techelevator.controller.exceptions.NotAuthorizedException;
import com.techelevator.model.Beer;
import com.techelevator.model.Breweries;
import com.techelevator.service.BreweriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class BreweriesController {

    @Autowired
    private BreweriesService breweryService;


    public BreweriesController(BreweriesService breweryService) {
        this.breweryService = breweryService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "admin/addBrewery")
    public boolean addBrewery(@Valid @RequestBody Breweries newBrewery) throws NotAuthorizedException {
        return breweryService.createBrewery(newBrewery);
    }

    @PermitAll
    @RequestMapping(value = "/breweries", method = RequestMethod.GET)
    public List<Breweries> getAllBreweries() {
        return breweryService.returnAllBreweries();
    }

    @PermitAll
    @RequestMapping(value = "/breweries/{id}", method = RequestMethod.GET)
    public Breweries getBreweryById(@PathVariable long id) throws BreweryNotFoundException {
        return breweryService.returnBreweriesById(id);
    }


    @PreAuthorize("hasRole('ROLE_BREWER')")
    @RequestMapping(value = "brewer/{id}", method = RequestMethod.PUT)
    public boolean updateBrewery(@PathVariable long id, @Valid @RequestBody Breweries breweryToChange) throws NotAuthorizedException {
        return breweryService.updateBrewery(id, breweryToChange);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "admin/breweries/{id}", method = RequestMethod.DELETE)
    public boolean removeBrewery(@PathVariable long id) throws NotAuthorizedException {
        if (breweryService.returnBreweriesById(id) != null) {
            return breweryService.checkDeleteBrewery(id);
        }
        return false;
    }


}
