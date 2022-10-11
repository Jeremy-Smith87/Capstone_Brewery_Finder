import Main from "./Components/Main/Main";
import Login from "./Components/Login/Login";
import BeerLoverHome from "./Components/BeerLoverHome/BeerLoverHome";
import BrewerHome from "./Components/BrewerHome/BrewerHome";
import BreweryHome from "./Components/BreweryHome/BreweryHome";
import IndividualBrew from "./Components/IndividualBrew/IndividualBrew";
import AdminHome from './Components/Admin/AdminHome';
import { Routes, Route } from "react-router-dom";
import RequireAuth from "./Components/RequireAuth";
import NavBar from "./Components/NavBar/NavBar";
import Layout from "./Components/Layout";
// import Register from "./Components/Register/Register";
import BeerLoverRegister from "./Components/Register/BeerLoverRegister";
import Footer from "./Components/Footer/Footer";

const ROLES = {
  'User': 'ROLE_USER',
  'Brewer': 'ROLE_BREWER',
  'Admin': 'ROLE_ADMIN'
}



function App() {
  return (
  <>
    <NavBar/>
    <Routes>
    <Route path="/" element={<Layout />}>
      {/* public routes */}
      <Route path="main" element={<Main />}/>
      <Route path="login" element={<Login />} />
      {/* <Route path="register" element={<Register />} /> this is the old register code given to us */}
      <Route path="register" element={<BeerLoverRegister />} />
      <Route path="breweries" element={<BreweryHome />} />
      <Route path="beer" element={<IndividualBrew />} />

      {/* we want to protect these routes */}
      <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
        <Route path="/" element={<BeerLoverHome />} />
        <Route path="/addFavorite" element={<BeerLoverHome />} />
        <Route path="/favorites/:favoriteId" element={<BeerLoverHome />} />
        <Route path="/" element={<BeerLoverHome />} />
      </Route>

      <Route element={<RequireAuth allowedRoles={[ROLES.Brewer]} />}>
        <Route path="/brewer/:breweryId" element={<BrewerHome />} />
        <Route path="/brewer/:breweryId/addBeer" element={<BrewerHome />} />
        <Route path="/brewer/beer/:beerId" element={<BrewerHome />} />
      </Route>


      <Route element={<RequireAuth allowedRoles={[ROLES.Admin]} />}>
        <Route path="admin" element={<AdminHome />} />
        <Route path="admin/users" element={<AdminHome />} />
        <Route path="admin/users/:userId" element={<AdminHome />} />
        <Route path="admin/addBrewery" element={<AdminHome />} />
        <Route path="admin/breweries/:breweryId" element={<AdminHome />} />
      </Route>

      <Route element={<RequireAuth allowedRoles={[ROLES.Brewer, ROLES.Admin]} />}>
        <Route path="brewer" element={<BrewerHome />} />
      </Route>

      {/* catch all */}
      <Route path="*" element={<Main/>} />
    </Route>
  </Routes>
  <Footer/>
  </>
  );
}

export default App;
