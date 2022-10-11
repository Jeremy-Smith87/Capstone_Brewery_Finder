import React from 'react'
import { Link } from "react-router-dom";

export default function BeerList(props){
    return (
      <div className="brew-selection--beer">
        <Link to={`/beer/${props.beerId}`}>
          <img
            src={props.imageUrl}
            className="brew-selection--image"
            alt={props.beerName}
            key={props.key}
          />
          <div className="brew-selection--name">{props.beerName}</div>
        </Link>
      </div>
    );
}