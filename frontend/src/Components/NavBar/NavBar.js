// import {Component, useState, useContext } from 'react'
// import {Link, useNavigate} from 'react-router-dom'
// import Login from '../Login/Login'
// import Register from '../Register/Register'
// import Home from '../Home/Home'
// import {addToken, deleteUser} from '../../Redux/actionCreators'
// import {connect} from 'react-redux'
// import {withRouter} from 'react-router-dom'
import { useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from '../Login/AuthProvider'
// import axios from 'axios'
import './NavBar.css'



export default function NavBar() {

  let isLoggedIn = true;

  const {setAuth} = useContext(AuthContext)
  // const navigate = useNavigate();

  const logout = async () => {
    setAuth({});
    // navigate('/main');
  }


    return (
      <div className="NavBar--nav">
        <div className="NavBar--blue-box">
          <div className="NavBar--nav-container">
          <Link to="/">
            <div className="NavBar--home-button">
              <p className="NavBar--nav-h1">BREWERY</p>
              <p className="NavBar--nav-h4">FINDER</p>
            </div>
            </Link>
            <div className="NavBar--empty"></div>
            <ul className="NavBar--nav-list">
              <li>
                <Link to="main">Home</Link>
              </li>

              <li>
                <Link to='main' onClick={logout}>Logout</Link>
              </li>

              <li>
                <Link to='/login'>Sign In</Link>
              </li>

              <li>
                {isLoggedIn ? <div>Make this the log out link </div> : <Link to="/login">Login</Link>}
              </li>

              <li>
                {isLoggedIn ? <div>Welcome, NAME</div> : <Link to="/register">Register</Link>}
              </li>
            </ul>
          </div>
        </div>
      </div>

    );
};