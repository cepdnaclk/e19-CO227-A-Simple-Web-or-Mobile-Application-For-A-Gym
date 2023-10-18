import React from "react";

function Header() {
  return (
    <div className="container">
      <div className="row">
        <div className="col-12">
          <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <a className="navbar-brand p-4" href="#">
              GymHub
            </a>
            <button
              className="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item p-4">
                  <a class="nav-link" style={{ fontWeight: 700 }} href="#">
                    Home
                  </a>
                </li>
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    About
                  </a>
                </li>
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    Course
                  </a>
                </li>
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    Pricing
                  </a>
                </li>
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    Gallery
                  </a>
                </li>
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    Login/Register
                  </a>
                </li>
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    BMI Calculator
                  </a>
                </li>
              </ul>
            </div>
          </nav>
        </div>
      </div>
    </div>
  );
}

export default Header;
