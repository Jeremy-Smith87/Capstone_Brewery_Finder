package com.techelevator.dao;

import com.techelevator.controller.exceptions.BeerNotFoundException;
import com.techelevator.model.Beer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBeerDao implements BeerDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBeerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public boolean createBeer(long id, Beer newBeer) {
        String sql = "INSERT INTO beer (beer_id, brewery_id, beer_name, description, image_url, abv, beer_type, hops, ibu) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, id, newBeer.getBeerName(), newBeer.getDescription(), newBeer.getImageUrl(), newBeer.getAbv(), newBeer.getBeerType(), newBeer.getHops(), newBeer.getIbu()) == 1;

    }

    @Override
    public List<Beer> getAllBeers() {
        List<Beer> beers = new ArrayList<>();
        String sql = "SELECT * FROM beer LEFT JOIN (SELECT beer_id, AVG(rating) AS avg_rating FROM reviews GROUP BY beer_id) AS rating ON rating.beer_id = beer.beer_id GROUP BY beer.beer_id, rating.beer_id, avg_rating ORDER BY beer_name";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Beer beer = mapRowToBeer(results);
            beers.add(beer);
        }
        return beers;
    }

    @Override
    public Beer getBeerById(long id) {
        Beer beer = null;
        String sql = "SELECT * FROM beer LEFT JOIN (SELECT beer_id, AVG(rating) AS avg_rating FROM reviews GROUP BY beer_id) AS rating ON rating.beer_id = beer.beer_id WHERE beer.beer_id= ? GROUP BY beer.beer_id, rating.beer_id, avg_rating ORDER BY beer_name;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            beer = mapRowToBeer(results);
        } else {
            throw new BeerNotFoundException();
        }

        return beer;
    }

    @Override
    public List<Beer> getBeerByBrewer(long id) {
        List<Beer> beers = new ArrayList<>();
        String sql = "SELECT * FROM beer LEFT JOIN (SELECT beer_id, AVG(rating)AS avg_rating FROM reviews GROUP BY beer_id)AS rating ON rating.beer_id = beer.beer_id WHERE brewery_id = ? GROUP BY beer.beer_id, rating.beer_id, avg_rating ORDER BY beer_name";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            Beer beer = mapRowToBeer(results);
            beers.add(beer);
        }
        return beers;
    }

    @Override
    public boolean updateBeer(long beerId, Beer beerToChange) {
        String sql = "UPDATE beer SET beer_name = ?, description = ?, image_url = ?, abv = ?, beer_type = ?, hops = ?, ibu = ? WHERE beer_id = ? ";
        return jdbcTemplate.update(sql, beerToChange.getBeerName(), beerToChange.getDescription(), beerToChange.getImageUrl(), beerToChange.getAbv(), beerToChange.getBeerType(), beerToChange.getHops(), beerToChange.getIbu(), beerId) == 1;
    }

    @Override
    public boolean deleteBeer(long id) {
        String sql = "DELETE FROM beer WHERE beer_id = ?";
        return jdbcTemplate.update(sql, id) == 1;
    }


    private Beer mapRowToBeer(SqlRowSet rs) {
        Beer beer = new Beer();
        beer.setBeerId(rs.getLong("beer_id"));
        beer.setBeerName(rs.getString("beer_name"));
        beer.setBreweryId(rs.getLong("brewery_id"));
        if (rs.getBigDecimal("avg_rating") == null) {
            beer.setAvgRating(rs.getBigDecimal("avg_rating"));
        } else {
            beer.setAvgRating(rs.getBigDecimal("avg_rating").setScale(2, RoundingMode.HALF_UP));
        }
        beer.setDescription(rs.getString("description"));
        beer.setImageUrl(rs.getString("image_url"));
        beer.setAbv(rs.getFloat("abv"));
        beer.setBeerType(rs.getString("beer_type"));
        beer.setHops(rs.getString("hops"));
        beer.setIbu(rs.getInt("ibu"));
        return beer;
    }

    ;

}
