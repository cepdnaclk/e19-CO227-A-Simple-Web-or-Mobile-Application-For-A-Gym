import React from "react";

// Functional component for rendering the website header
function Header() {
  return (
    <div className="container">
      <div className="row">
        <div className="col-12">

          {/* Navigation bar */}
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

                {/* Home link */}
                <li className="nav-item p-4">
                  <a class="nav-link" style={{ fontWeight: 700 }} href="#">
                    Home
                  </a>
                </li>

                {/* About link */}
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    About
                  </a>
                </li>

                {/* Course link */}
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    Course
                  </a>
                </li>

                {/* Pricing link */}
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    Pricing
                  </a>
                </li>

                {/* Gallery link */}
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    Gallery
                  </a>
                </li>

                {/* Login/Register link */}
                <li className="nav-item p-4">
                  <a className="nav-link" style={{ fontWeight: 700 }} href="#">
                    Login/Register
                  </a>
                </li>

                {/* BMI Calculator link */}
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

// Export the Header component for use in other parts of the application
export default Header;
