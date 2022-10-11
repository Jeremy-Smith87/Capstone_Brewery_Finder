import React from "react";
import "./Brewer.css";
import { useState } from "react";

export default function BrewerUpdateBrewery() {

  const [updateBreweryData, setUpdateBreweryData] = useState({
    name: "",
    address: "",
    city: "",
    state_abbr: "",
    zipcode: "",
    phone_number: "",
    description: "",
    logo_url: "",
    hours: "",
    website: "",
  });

  console.log(updateBreweryData)

  function handleChange(event) {
    setUpdateBreweryData((prevFormData) => {
      return {
        ...prevFormData,
        [event.target.name]: event.target.value,
      };
    });
  }

  return (
    <div className="brewer-update-brewery-wrapper">
      <div className="brewer-update-brewery-form-wrapper">
        {/* pass in the breweryId somehow, based on user? 
          on the backend, beerId and brewerId are required
          endpoint is /addBeer */}
        <h3>Update Your Brewery</h3>
        <form className="AdminAddBrewery--form-container">
          <div className="AdminAddBrewery--form-left">
            <label className="form-left" htmlFor="breweryName">
              Brewery Name<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-left"
              type="text"
              id="breweryName"
              name="name"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

        

            <label className="form-left" htmlFor="address">
              Street Address<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-left"
              type="text"
              id="address"
              name="address"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-left" htmlFor="city">
              City<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-left"
              type="text"
              id="city"
              name="city"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-left" htmlFor="state">
              State<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-left"
              type="text"
              id="state"
              name="state_abbr"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-left" htmlFor="zip">
              Zip<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-left"
              type="text"
              id="zip"
              name="zipcode"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-right" htmlFor="phoneNumber">
              Phone Number<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-right"
              type="text"
              id="phoneNumber"
              name="phone_number"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />
          </div>
          <div className="AdminAddBrewery--form-right">
            <label className="form-right" htmlFor="history">
              Brewery history<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-right"
              type="text"
              id="history"
              name="description"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-right" htmlFor="hours">
              Hours of Operation<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-right"
              type="text"
              id="hours"
              name="hours"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            

            <label className="form-right" htmlFor="logoURL">
              Please Give a Link to Your Logo<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-right"
              type="text"
              id="logoURL"
              name="logo_url"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-right" htmlFor="websiteURL">
              Please Give a Link to Your Website<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-right"
              type="text"
              id="websiteURL"
              name="website"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

          
          </div>

          <button
            className="AdminAddBrewery--form-submitBtn"
        
          >
            SUBMIT
          </button>
        </form>
      </div>
    </div>
  );
}
