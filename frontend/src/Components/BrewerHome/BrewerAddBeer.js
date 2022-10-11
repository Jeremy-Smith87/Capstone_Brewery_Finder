import React from "react";
import {useState} from "react"
import "./Brewer.css";

export default function BrewerAddBeer(){

  const [addBeerData, setAddBeerData] = useState({
    brewery_id: "",
    beer_name: "",
    description: "",
    image_url: "",
    abv: "",
    beer_type: "",
    hops: "",
    ibu: ""
  })
  console.log(addBeerData)

  function handleChange(event) {
    setAddBeerData(prevFormData => {
      return {
        ...prevFormData,
        [event.target.name]: event.target.value
      }
    })
  }
    return (
      <div className="brewer-add-beer-wrapper">
       
        <div className="brewer-add-beer-form-wrapper">
          {/* pass in the breweryId somehow, based on user? 
          on the backend, beerId and brewerId are required
          endpoint is /addBeer */}
          <label className="form-right" htmlFor="beerName">
            Beer Name<span className="star">*</span>
          </label>
          <br />
          <input
            className="form-right"
            type="text"
            name="beer_name"
            onChange={handleChange}
          ></input>
          <br />

          <label className="form-right" htmlFor="description">
            Beer Description<span className="star">*</span>
          </label>
          <br />
          <input
            className="form-right"
            type="text"
            name="description"
            onChange={handleChange}
          ></input>
          <br />

          <label className="form-right" htmlFor="ImageURL">
            Image URL<span className="star">*</span>
          </label>
          <br />
          <input
            className="form-right"
            type="text"
            name="image_url"
            onChange={handleChange}
          ></input>
          <br />

          <label className="form-right" htmlFor="abv">
            ABV
          </label>
          <br />
          <input className="form-right" type="text" id="abv" name="abv"></input>
          <br />

          <label className="form-right" htmlFor="beerType">
            Beer Type
          </label>
          <br />
          <input
            className="form-right"
            type="text"
            name="beer_type"
            onChange={handleChange}
          ></input>
          <br />

          <label className="form-right" htmlFor="hops">
            Hops
          </label>
          <br />
          <input
            className="form-right"
            type="text"
            name="hops"
            onChange={handleChange}
          ></input>
          <br />

          <label className="form-right" htmlFor="ibu">
            IBU
          </label>
          <br />
          <input
            className="form-right"
            type="text"
            name="ibu"
            onChange={handleChange}
          ></input>
          <br />
          <p className="brewer-button">SUBMIT</p>
        </div>
   
      </div>
    );
}