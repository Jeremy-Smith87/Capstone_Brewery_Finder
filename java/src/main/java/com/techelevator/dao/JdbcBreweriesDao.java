package com.techelevator.dao;

import com.techelevator.controller.exceptions.BreweryNotFoundException;
import com.techelevator.model.Breweries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBreweriesDao implements BreweriesDao {


    private JdbcTemplate jdbcTemplate;

    public JdbcBreweriesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean createBrewery(Breweries newBrewery) {
        String sql = "INSERT INTO breweries (brewery_id, name, address, user_id, city, state_abbr, zipcode, phone_number, description, logo_url, hours, website) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, newBrewery.getBreweryId(), newBrewery.getName(), newBrewery.getAddress(), newBrewery.getUserId(), newBrewery.getCity(), newBrewery.getStateAbbreviation(), newBrewery.getZipcode(), newBrewery.getPhoneNumber(), newBrewery.getLogoUrl(), newBrewery.getHours(), newBrewery.getWebsite()) == 1;
    }

    @Override
    public List<Breweries> getAllBreweries() {
        List<Breweries> breweries = new ArrayList<>();
        String sql = "SELECT * FROM breweries";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Breweries brewery = mapRowToBrewery(results);
            breweries.add(brewery);
        }
        return breweries;
    }

    @Override
    public Breweries getBreweryById(long id) {
        Breweries brewery = null;
        String sql = "SELECT * FROM breweries WHERE brewery_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            brewery = mapRowToBrewery(results);
        } else {
            throw new BreweryNotFoundException();
        }
        return brewery;
    }

    @Override
    public Breweries getBreweryByUserId(long userId) {
        Breweries brewery;
        String sql = "SELECT * FROM breweries WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            brewery = mapRowToBrewery(results);
        } else {
            throw new BreweryNotFoundException();
        }
        return brewery;
    }

    @Override
    public boolean deleteBrewery(long id) {
        String sql = "DELETE FROM breweries WHERE brewery_id = ?";
        return jdbcTemplate.update(sql, id) == 1;
    }

    @Override
    public boolean updateBrewery(long id, Breweries breweryToChange) {
        String sql = "UPDATE breweries SET name = ?, address = ?, user_id = ?, city = ?, state_abbr ?, zipcode ?, phone_number ?, description ?, logo_url ?, hours ?, website ?, WHERE brewery_id = ?";
        return jdbcTemplate.update(sql, breweryToChange.getName(), breweryToChange.getAddress(), breweryToChange.getUserId(), breweryToChange.getCity(), breweryToChange.getStateAbbreviation(), breweryToChange.getZipcode(), breweryToChange.getPhoneNumber(), breweryToChange.getDescription(), breweryToChange.getLogoUrl(), breweryToChange.getHours(), breweryToChange.getWebsite()) == 1;
    }

    private Breweries mapRowToBrewery(SqlRowSet rs) {
        Breweries brewery = new Breweries();

        brewery.setBreweryId(rs.getLong("brewery_id"));
        brewery.setName(rs.getString("name"));
        brewery.setAddress(rs.getString("address"));
        brewery.setUserId(rs.getLong("user_id"));
        brewery.setCity(rs.getString("city"));
        brewery.setStateAbbreviation(rs.getString("state_abbr"));
        brewery.setZipcode(rs.getString("zipcode"));
        brewery.setPhoneNumber(rs.getString("phone_number"));
        brewery.setDescription(rs.getString("description"));
        brewery.setLogoUrl(rs.getString("logo_url"));
        brewery.setHours(rs.getString("hours"));
        brewery.setWebsite(rs.getString("website"));

        return brewery;
    }

}
