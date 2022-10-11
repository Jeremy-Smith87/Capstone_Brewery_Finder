import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
import BeerList from "../BrewerHome/BeerList";
import "./BreweryHome.css";

export default function BreweryHome() {
  const { breweryId } = useParams();

  const [singleBreweryData, setSingleBreweryData] = useState([]);
  const [beerData, setBeerData] = useState([]);

  useEffect(
    function () {
      fetch(`http://localhost:8080/breweries/${breweryId}`)
        .then((res) => res.json())
        .then((data) => setSingleBreweryData(data));
    },
    [breweryId]
  );

  useEffect(
    function () {
      fetch(`http://localhost:8080/breweries/${breweryId}/beers`)
        .then((res) => res.json())
        .then((data) => setBeerData(data));
    },
    [breweryId]
  );

  console.log(singleBreweryData);
  console.log(beerData);

  return (
    <>
      <div className="brewery-home--wrapper">
        <div className="brew-selection">
          <h2 className="brew-selection--header">Our Brew Selection</h2>

          {beerData.map((data) => (
            <BeerList imageUrl={data.imageUrl} beerName={data.beerName} beerId={data.beerId} key={data.beerId} />
          ))}
        </div>

        <div className="brewery-description">
          <div className="brewery-photos">
            <img
              className="brewery-photos--image"
              src={singleBreweryData.logoUrl}
              alt={singleBreweryData.beerName}
            />
          </div>

          <h1 className="brewery-description--brewery-name">
            {singleBreweryData.name}
          </h1>
          <h2 className="brewery-description--brewery-address">
            <div className="descriptors">Address: </div>
            {singleBreweryData.address}, {singleBreweryData.city},{" "}
            {singleBreweryData.stateAbbreviation}, {singleBreweryData.zipcode}
          </h2>

          <h2 className="brewery-description--brewery-hours">
            <div className="descriptors">Hours: </div>
            {singleBreweryData.hours}
          </h2>
          <div className="brewery-description--brewery-history-text">
            <div className="descriptors">Description: </div>

            {singleBreweryData.description}
          </div>
        </div>
      </div>
    </>
  );
}
