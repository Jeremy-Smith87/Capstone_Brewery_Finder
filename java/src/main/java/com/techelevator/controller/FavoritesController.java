package com.techelevator.controller;

import com.techelevator.controller.exceptions.BreweryNotFoundException;
import com.techelevator.controller.exceptions.FavoritesNotFoundException;
import com.techelevator.controller.exceptions.NotAuthorizedException;
import com.techelevator.model.Breweries;
import com.techelevator.model.Favorites;
import com.techelevator.service.FavoritesService;
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
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addFavorite")
    public boolean addFavorite(@Valid @RequestBody Favorites favorite) {
        return favoritesService.createFavorite(favorite);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public List<Favorites> getAllFavorites() {
        return favoritesService.returnAllFavorites();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/favorites/{id}", method = RequestMethod.GET)
    public Favorites getFavoritesById(@PathVariable long id) throws FavoritesNotFoundException {
        return favoritesService.returnFavoritesById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/favorites/{id}", method = RequestMethod.DELETE)
    public boolean deleteFavorite(@PathVariable long id, Principal principal) throws NotAuthorizedException {
        if (favoritesService.returnFavoritesById(id) != null) {
            return favoritesService.checkDeleteFavorite(id, principal);
        }
        return false;
    }

}
