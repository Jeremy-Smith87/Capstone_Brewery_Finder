package com.techelevator.dao;

import com.techelevator.model.Reviews;

import java.util.List;

public interface ReviewsDao {


    Reviews getReviewById(long id);

    boolean checkReviewDelete(long id);

    boolean createReview(Reviews newReview);

    List<Reviews> getAllReviews();

    boolean updateReviewsById(long reviewId, Reviews reviewToChange);

    List<Reviews> getReviewsByBeer(long beerId);

    List<Reviews> getReviewsByBrewer(long breweryId);
}
