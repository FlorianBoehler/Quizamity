import React from "react";
import ReactDOM from "react-dom/client";
import Carousel from "./Carousel.jsx";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

ReactDOM.createRoot(document.getElementById("dashboard-root")).render(
  <React.StrictMode>
    <Carousel />
  </React.StrictMode>
);