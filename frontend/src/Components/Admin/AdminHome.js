import addUserImg from "../../Resources/addUser.png";
import addBreweryImg from "../../Resources/addBrewery.png";
import "./AdminHome.css";
import BreweryList from "../BeerLoverHome/BreweryList";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AdminAddBrewery from "./AdminAddBrewery"

export default function AdminHome() {

    const [breweriesData, setBreweriesData] = useState([]);

    useEffect(function () {
      fetch("http://localhost:8080/breweries")
        .then((res) => res.json())
        .then((data) => setBreweriesData(data));
    }, []);


  return (
    <div className="AdminHome--container">
      <div className="Header--color-bar"></div>
      <div className="AdminHome--grid-container">
        
        <img
          className="AdminHome--addBreweryImg"
          src={addBreweryImg}
          alt="brewery"
        />
        
        <img
          className="AdminHome--addUserImg"
          src={addUserImg}
          alt="guy drinking beer"
        />
        <div className="AdminHome--addBreweryTxt">ADD A BREWERY</div>
        <div className="AdminHome--addUserTxt">ADD A USER</div>
      </div>

      <div className="wrapper-wrapper">
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
