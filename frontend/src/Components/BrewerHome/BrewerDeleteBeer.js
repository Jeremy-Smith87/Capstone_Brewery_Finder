import React from "react";
import "./Brewer.css"


export default function BrewerDeleteBeer(){
    return (
      <div className="delete-beer-wrapper">
        {/* endpoint: value = "/beer/{id}", method = RequestMethod.DELETE */}
        <button className="brewer-button">Delete Beer</button>
      </div>
    );
}