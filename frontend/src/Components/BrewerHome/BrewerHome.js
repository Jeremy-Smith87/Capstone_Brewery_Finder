import React from "react";
import { useParams } from "react-router";
import "./BrewerHome.css";


export default function BrewerHome() {
  const userId = useParams();
  console.log("user id= " +{userId});

  return (
    <div className="brewer-home--wrapper">

      <div className="content-wrapper">
        {/*if logged out...*/}
        <div>log in</div>
        <div>request a brewer account</div>
        {/*if logged in...*/}
        {/*show the brewery associated with the userId*/}
      </div>
    </div>
  );
}
