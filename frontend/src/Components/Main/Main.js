
import { Link } from "react-router-dom";
import NavBar from "../NavBar/NavBar";
import Footer from "../Footer/Footer";
import beerLoversDisplay from "../../Resources/beerLoversDisplay.jpg";
import "./Main.css";

export default function Main() {
  return (
    <div className="Main--container">
      <div>
        <NavBar />
      </div>
      <div className="Main--welcome-container">
        <div className="Main--welcome-banner">
          <p className="Main--welcome-text">WELCOME TO</p>
          <p className="Main--title-text">BREWERY</p>
          <p className="Main--finder-text">FINDER</p>
          <p className="Main--task-text">
            Find new breweries and rate your favorites
          </p>
        </div>
      </div>
      <ul className="Main--user-selection">
        <li className="Main--beer-lover">
          <Link to="/beer-lover-home">I'M A BEER LOVER</Link>
        </li>
        <li className="Main--brewer">
          <Link to="/brewer-home">I'M A BREWER</Link>
        </li>
      </ul>


      <div className="Main--content-area-container">

      <div className="Main--info"><p>Founded in 2022 by four Fullstack Devlopers</p>
      <p>Brewery finder was made to be your one hop stop to find and rate the best brews in your city</p>
      <p>Register below for a smile from beer to beer</p></div>
        {/*<img
          className="Main--content-brewerDisplay Main--content-displays"
          src={brewerDisplay} alt="Brewer Display"
        />*/}
        <div className="Main--content-beerLover-container">
          <div className="Main--container-for-border">
            <h3>Beer Lovers</h3>
            <p>
              Explore breweries, register to rate your
              <br />
              favorite beers
              <br />
            </p>
            <p className="Main--register-button">
              <Link to="/register">REGISTER</Link>
            </p>
          </div>
        </div>
        <img
          className="Main--content-beerLoverDisplay Main--content-displays"
          src={beerLoversDisplay} alt="beer lovers display"
        />
      </div>
      <div>
        <Footer />
      </div>
    </div>
  );
}
