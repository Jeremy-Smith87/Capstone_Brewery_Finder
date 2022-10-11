package com.techelevator.dao;

import com.techelevator.controller.exceptions.ReviewNotFoundException;
import com.techelevator.model.Reviews;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewsDao implements ReviewsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReviewsDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createReview(Reviews newReview) {
        String sql = "INSERT INTO reviews (review_id, user_id, username, rating, description, create_date, beer_id, brewery, id) VALUES (DEFAULT,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, newReview.getReviewId(), newReview.getUserId(), newReview.getUsername(), newReview.getRating(), newReview.getDescription(), newReview.getCreateDate(), newReview.getBeerId(), newReview.getBreweryId()) == 1;
    }

    @Override
    public List<Reviews> getAllReviews() {
        List<Reviews> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Reviews review = mapRowToReviews(results);
            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public List<Reviews> getReviewsByBeer(long beerId) {
        List<Reviews> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE beer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
        while (results.next()) {
            Reviews review = mapRowToReviews(results);
            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public List<Reviews> getReviewsByBrewer(long breweryId) {
        List<Reviews> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE brewery_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);
        while (results.next()) {
            Reviews review = mapRowToReviews(results);
            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public Reviews getReviewById(long id) {
        Reviews review = null;
        String sql = "SELECT * FROM reviews where review_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            review = mapRowToReviews(results);
        } else {
            throw new ReviewNotFoundException();
        }
        return review;
    }

    @Override
    public boolean updateReviewsById(long reviewId, Reviews reviewToChange) {
        String sql = "UPDATE reviews SET rating = ?, description = ?, create_date = ? WHERE review_id = ?";
        return jdbcTemplate.update(sql, reviewToChange.getRating(), reviewToChange.getDescription(), reviewToChange.getCreateDate(), reviewId) == 1;
    }

    @Override
    public boolean checkReviewDelete(long reviewId) {
        String sql = "DELETE FROM reviews WHERE review_id = ?";
        return jdbcTemplate.update(sql, reviewId) == 1;
    }

    private Reviews mapRowToReviews(SqlRowSet rs) {
        Reviews review = new Reviews();
        review.setReviewId(rs.getLong("review_id"));
        review.setUserId(rs.getLong("user_id"));
        review.setUsername(rs.getString("username"));
        review.setRating(rs.getInt("rating"));
        review.setDescription(rs.getString("description"));
        review.setBeerId(rs.getLong("beer_id"));
        review.setBreweryId(rs.getLong("brewery_id"));
        if (rs.getDate("create_date") != null) {
            review.setCreateDate(rs.getDate("create_date").toLocalDate());
        }
        return review;
    }
}
