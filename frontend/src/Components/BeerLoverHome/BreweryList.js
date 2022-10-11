import React from "react";
import { Link } from "react-router-dom";

export default function BreweryList(props) {
  return (
    <div className="discover-breweries--single-brewery--wrapper">
      <div className="single-brewery--left">
        <img
          src={props.logoUrl}
          className="single-brewery-photo"
          alt={props.name}
          key={props.key}
        />
      </div>
      <div className="single-brewery--right">
        <div className="single-brewery--name" key={props.key}>
          {props.name}
        </div>
        <div className="single-brewery--address" key={props.key}>
          {props.address}, {props.city}, {props.stateAbbreviation},{" "}
          {props.zipcode}
        </div>

        <div className="single-brewery--history-title">History: </div>
        <div className="single-brewery--history-text" key={props.key}>
          {props.description}
        </div>
        <Link to={`/breweries/${props.breweryId}`}>
          <button className="single-brewery--explore-button">EXPLORE</button>
        </Link>
      </div>
    </div>
  );
}
