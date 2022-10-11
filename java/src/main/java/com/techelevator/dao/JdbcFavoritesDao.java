package com.techelevator.dao;

import com.techelevator.controller.exceptions.FavoritesNotFoundException;
import com.techelevator.model.Favorites;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFavoritesDao implements FavoritesDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcFavoritesDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createFavorite(Favorites newFavorite) {
        String sql = "INSERT INTO favorites (favorite_id, user_id, brewery_id, beer_id) VALUES (DEFAULT,?,?,?)";
        return jdbcTemplate.update(sql, newFavorite.getFavoriteId(), newFavorite.getUserId(), newFavorite.getBreweryId(), newFavorite.getBeerId()) == 1;
    }

    @Override
    public List<Favorites> getAllFavorites() {
        List<Favorites> favorites = new ArrayList<>();
        String sql = "SELECT * FROM beer";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Favorites favorite = mapRowToFavorites(results);
            favorites.add(favorite);
        }
        return favorites;
    }

    @Override
    public Favorites getFavoritesById(long id) {
        Favorites favorite = null;
        String sql = "SELECT * FROM beer where favorite_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            favorite = mapRowToFavorites(results);
        } else {
            throw new FavoritesNotFoundException();
        }
        return favorite;
    }

    @Override
    public boolean checkDeleteFavorite(long id) {
        String sql = "DELETE FROM favorites WHERE favorite_id = ?";
        return jdbcTemplate.update(sql, id) == 1;
    }

    private Favorites mapRowToFavorites(SqlRowSet rs) {
        Favorites favorite = new Favorites();
        favorite.setFavoriteId(rs.getLong("favorite_id"));
        favorite.setUserId(rs.getLong("user_id"));
        favorite.setBreweryId(rs.getLong("brewery_id"));
        favorite.setBeerId(rs.getLong("beer_id"));

        return favorite;
    }
}
