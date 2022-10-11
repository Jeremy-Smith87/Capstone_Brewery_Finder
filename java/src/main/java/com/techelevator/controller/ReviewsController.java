package com.techelevator.controller;

import com.techelevator.controller.exceptions.NotAuthorizedException;
import com.techelevator.controller.exceptions.ReviewNotFoundException;
import com.techelevator.model.Reviews;
import com.techelevator.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewService) {
        this.reviewsService = reviewService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addReview")
    public boolean addReview(@Valid @RequestBody Reviews newReview) {
        return reviewsService.createReview(newReview);
    }

    @RequestMapping(path = "/reviews", method = RequestMethod.GET)
    public List<Reviews> getAllReviews() throws ReviewNotFoundException {
        return reviewsService.returnAllReviews();
    }

    @RequestMapping(path = "/reviews/{reviewId}", method = RequestMethod.GET)
    public Reviews getReviewsById(@PathVariable long reviewId) throws ReviewNotFoundException {
        return reviewsService.returnReviewsById(reviewId);
    }

    @RequestMapping(value = "/reviews/beer/{beerId}", method = RequestMethod.GET)
    public List<Reviews> getReviewByBeer(@PathVariable long beerId) throws ReviewNotFoundException {
        return reviewsService.returnReviewByBeer(beerId);
    }

    @RequestMapping(value = "/reviews/breweries/{breweryId}", method = RequestMethod.GET)
    public List<Reviews> getReviewByBrewery(@PathVariable long breweryId) throws ReviewNotFoundException {
        return reviewsService.returnReviewByBrewery(breweryId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/reviews/{reviewId}", method = RequestMethod.PUT)
    public boolean updateReview(@PathVariable long reviewId, @Valid @RequestBody Reviews reviewToChange, Principal principal) throws NotAuthorizedException {
        return reviewsService.updateReviewsById(reviewId, reviewToChange, principal);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(path = "/reviews/{reviewId}", method = RequestMethod.DELETE)
    public boolean deleteAReview(@PathVariable Long reviewId, Principal principal) throws NotAuthorizedException {

        if (reviewsService.returnReviewsById(reviewId) != null) {
            return reviewsService.checkReviewDelete(reviewId, principal);
        } else return false;
    }


}
