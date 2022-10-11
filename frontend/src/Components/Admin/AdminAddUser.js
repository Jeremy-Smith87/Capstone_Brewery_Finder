import AdminAddUserImg from "../../Resources/adminAddUserImg.jpg";
import "./AdminAddUser.css";
import { useState } from "react";
import React from "react";

export default function AdminAddUser() {
  const [addUserData, setAddUserData] = useState({
    username: "",
    password: "",
    role: "ROLE_BREWERY",
    email: "",
  });

  console.log(addUserData);

  function handleChange(event) {
    setAddUserData((prevFormData) => {
      return {
        ...prevFormData,
        [event.target.name]: event.target.value,
      };
    });
  }

  return (
    <div className="AdminAddUser--container">
      <div className="Header--color-bar"></div>
      <div className="AdminAddUser--grid-container">
        <h3 className="AdminAddUser--heading">Add A User</h3>
        <img
          className="AdminAddUser--img"
          alt="beer thumbs up"
          src={AdminAddUserImg}
        />
        <div className="AdminAddUser--form">
          <label className="form-right" htmlFor="brewerName">
            Brewer's Name<span className="star">*</span>
          </label>
          <br />

          <input
            className="form-right"
            type="text"
            id="brewerName"
            name="brewerName"
          ></input>
          <br />

          <label className="form-right" htmlFor="userName">
            User Name<span className="star">*</span>
          </label>
          <br />
          <input
            className="form-right"
            type="text"
            id="userName"
            name="username"
            onChange={handleChange}
          ></input>
          <br />

          <label className="form-right" htmlFor="password">
            Password<span className="star">*</span>
          </label>
          <br />
          <input
            className="form-right"
            type="text"
            id="password"
            name="password"
            onChange={handleChange}
          ></input>
          <br />

          <label className="form-right" htmlFor="password">
            Email
          </label>
          <br />
          <input
            className="form-right"
            type="text"
            id="email"
            name="email"
            onChange={handleChange}
          ></input>
          <br />
        </div>

        <p className="AdminAddUser--form-submitBtn">SUBMIT</p>
      </div>
    </div>
  );
}
