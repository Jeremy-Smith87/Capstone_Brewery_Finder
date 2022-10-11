import React from "react";
import {useState} from "react"



export default function BeerLoverAddReview() {

    const [addBeerData, setAddBeerData] = useState({
      review_id: "",
      user_id: "",
      username: "",
      rating: "",
      description: "",
      create_date: Date().toLocaleString(),
      beer_id: "",
      brewery_id: "",
    });

    console.log(addBeerData)

    function handleChange(event){
      setAddBeerData(prevFormData => {
        return {
          ...prevFormData,
          [event.target.name]: event.target.value
        }
      })
    }
  return (
    <div className="brewer-add-beer-wrapper">
      
      <div className="beer-lover-add-review-form-wrapper">

        {/* might not need this one */}
        <label className="form-right" htmlFor="review">
          Your Username
        </label>
        <br />
        <input
          className="form-right"
          type="text"
          name="username"
          onChange={handleChange}
        ></input>
        <br />

        <label className="form-right" htmlFor="review">
          Rating
        </label>
        <br />
        <select className="form-right" name="rating" onChange={handleChange}>
          <option value="">--please choose--</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
        <br />

        <label className="form-right" htmlFor="review">
          Your Review
        </label>
        <br />
        <input
          className="form-right"
          type="text"
          name="description"
          onChange={handleChange}
        ></input>
        <br />

        <p className="brewer-button">SUBMIT</p>
      </div>
     
    </div>
  );
}
