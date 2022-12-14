import React from "react";
import ReactDOM from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "font-awesome/css/font-awesome.min.css";
import "bootstrap-social/bootstrap-social.css";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { AuthProvider } from "./Components/Login/AuthProvider";
import { BrowserRouter, Routes, Route} from "react-router-dom"

ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
    <AuthProvider>
    <Routes>
       <Route path="/*" element={<App />} />
       </Routes>
       </AuthProvider>
      </BrowserRouter>
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
