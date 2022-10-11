package com.techelevator.service;

import com.techelevator.controller.exceptions.FavoritesNotFoundException;
import com.techelevator.dao.FavoritesDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Favorites;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class FavoritesService {

    @Autowired
    FavoritesDao favoritesDao;

    @Autowired
    UserDao userDao;

    public boolean createFavorite(Favorites favorite) {
        boolean result = false;
        try {
            favoritesDao.createFavorite(favorite);
            result = true;
        } catch (FavoritesNotFoundException e) {
            System.out.println(e.getMessage());

        }
        return result;

    }

    public List<Favorites> returnAllFavorites() {
        return favoritesDao.getAllFavorites();
    }

    public Favorites returnFavoritesById(long id) {
        return favoritesDao.getFavoritesById(id);
    }

    public boolean checkDeleteFavorite(long id, Principal principal) {
        String username = principal.getName();
        long currentId = userDao.findIdByUsername(username);
        User user = userDao.getUserById(currentId);
        Favorites favoriteToDelete = favoritesDao.getFavoritesById(id);

        if (favoriteToDelete.getUserId() == currentId || user.getAuthorities().equals("ROLE_ADMIN")) {
            return favoritesDao.checkDeleteFavorite(id);
        } else return false;
    }
}
