package com.techelevator.dao;

import com.techelevator.model.Favorites;

import java.util.List;

public interface FavoritesDao {
    boolean createFavorite(Favorites favorite);

    List<Favorites> getAllFavorites();

    Favorites getFavoritesById(long id);

    boolean checkDeleteFavorite(long id);
}
