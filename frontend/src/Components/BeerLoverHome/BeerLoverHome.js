import React, { useEffect, useState } from "react";

import "./BeerLoverHome.css";
import BreweryList from "./BreweryList";


export default function BeerLoverHome() {
  const [breweriesData, setBreweriesData] = useState([]);

  useEffect(function () {
    fetch("http://localhost:8080/breweries")
      .then((res) => res.json())
      .then((data) => setBreweriesData(data));
  }, []);

  return (
    <div className="beer-lover-home--wrapper">
    
      <div className="component-wrapper">
        <div className="discover-breweries-wrapper">
          <h1 className="discover-breweries--heading">Discover Breweries</h1>
          {console.log(breweriesData)}

          {breweriesData.map((data) => (
            <BreweryList
              logoUrl={data.logoUrl}
              name={data.name}
              address={data.address}
              city={data.city}
              stateAbbreviation={data.stateAbbreviation}
              zipcode={data.zipcode}
              description={data.description}
              breweryId={data.breweryId}
            
            />
          ))}
        </div>
      </div>
    </div>
  );
}
