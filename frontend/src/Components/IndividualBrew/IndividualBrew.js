import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./IndividualBrew.css";
import Hop from "../../Resources/hop.png";

export default function BeerLoverHome() {
  const { beerId } = useParams();

  const [singleBeerData, setSingleBeerData] = useState([]);
  const [beerReviews, setBeerReviews] = useState([]);

  useEffect(
    function () {
      fetch(`http://localhost:8080/beer/${beerId}`)
        .then((res) => res.json())
        .then((data) => setSingleBeerData(data));
    },
    [beerId]
  );

  useEffect(
    function () {
      fetch(`http://localhost:8080/reviews/beer/${beerId}`)
        .then((res) => res.json())
        .then((data) => setBeerReviews(data));
    },
    [beerId]
  );

  const hopsRating = [];
  function figureHopsRating() {
    for (let i = 0; i < singleBeerData.avgRating; i++) {
      hopsRating.push(
        <img
          src={Hop}
          className="individual-beer--rating-images"
          alt={singleBeerData.avgRating}
        />
      );
    }
    return hopsRating;
  }

  console.log(singleBeerData);

  return (
    <>
      <div className="individual-beer--wrapper">
        <div className="individual-beer--image-wrapper">
          <img
            className="individual-beer--image"
            src={singleBeerData.imageUrl}
            alt={singleBeerData.beerName}
          />
        </div>
        <div className="individual-beer--right-wrapper">
          <div className="individual-beer--title">
            {singleBeerData.beerName}
          </div>
          <div className="individual-beer--description-title">
            Description:{" "}
          </div>
          <div className="individual-beer--description-text">
            {singleBeerData.description}
          </div>
          <div className="individual-beer--abv">ABV: {singleBeerData.abv}%</div>
          <div className="individual-beer--type">
            Type: {singleBeerData.beerType}
          </div>
          <div className="individual-beer--rating-wrapper">
            <div className="individual-beer--rating-title">Rating:</div>
            {figureHopsRating()}

            {/* {singleBeerData.map((rating) => (
                <img src={Hop} className="individual-beer--rating-images" />
              ))} */}

            {/* <img src={Hop} className="individual-beer--rating-images" />
            <img src={Hop} className="individual-beer--rating-images" /> */}
          </div>
          <div className="individual-beer--choice-links">
            Rate This Brew | Review This Brew
          </div>
        </div>
      </div>

      <div className="reviews-wrapper">
        <h2 className="individual-beer--reviews-header">Reviews:</h2>
        {beerReviews.map((reviews) => (
          <div className="individual-beer--single-review-wrapper">
            <div className="individual-beer--review-title-wrapper">
              <div className="individual-beer--review-username">
                Review from: {reviews.username}
              </div>
              <div className="individual-beer--review-timestamp">
                on Date: {reviews.createDate}
              </div>
            </div>
            <div className="individual-beer--review-body">
              {reviews.description}
            </div>
          </div>
        ))}
        {/* add link to add a review */}
      </div>
    </>
  );
}
