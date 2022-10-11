package com.techelevator.service;

import com.techelevator.controller.exceptions.BreweryNotFoundException;
import com.techelevator.controller.exceptions.ReviewNotFoundException;
import com.techelevator.dao.ReviewsDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Reviews;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewsService {

    @Autowired
    ReviewsDao reviewsDao;

    @Autowired
    UserDao userDao;

    public boolean createReview(Reviews newReview) {
        boolean result = false;
        newReview.setCreateDate(LocalDate.now());

        try {
            reviewsDao.createReview(newReview);
            result = true;
        } catch (ReviewNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Reviews> returnAllReviews() {
        return reviewsDao.getAllReviews();
    }

    public Reviews returnReviewsById(Long reviewId) {
        return reviewsDao.getReviewById(reviewId);
    }

    public boolean updateReviewsById(long reviewId, Reviews reviewToChange, Principal principal) {
        String username = principal.getName();
        long currentId = userDao.findIdByUsername(username);
        reviewToChange.setCreateDate(LocalDate.now());

        if (reviewToChange.getUserId() == currentId) {
            return reviewsDao.updateReviewsById(reviewId, reviewToChange);
        }
        return false;
    }

    public boolean checkReviewDelete(Long reviewId, Principal principal) {
        String username = principal.getName();
        long currentId = userDao.findIdByUsername(username);
        User user = userDao.getUserById(currentId);
        Reviews reviewToDelete = returnReviewsById(reviewId);

        if (reviewToDelete.getUserId() == currentId || user.getRole().equals("ROLE_ADMIN")) {
            return reviewsDao.checkReviewDelete(reviewId);
        }
        return false;
    }

    public List<Reviews> returnReviewByBeer(long beerId) {
        return reviewsDao.getReviewsByBeer(beerId);
    }

    public List<Reviews> returnReviewByBrewery(long breweryId) {
        return reviewsDao.getReviewsByBrewer(breweryId);
    }
}
