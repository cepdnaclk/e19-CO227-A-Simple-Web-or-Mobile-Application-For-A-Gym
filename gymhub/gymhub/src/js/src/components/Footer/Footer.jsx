
import React from 'react'
import './Footer.css'

// Importing images
import Location from "../../assets/location.jpg"
import Phone from "../../assets/Phone.jpg"
import Email from "../../assets/email.jpg"
import Logo from "../../assets/logo.png"

// Functional component for the website footer
const Footer = () => {
  return (
    <div className="Footer-container">
    <hr /> {/* Horizontal rule to separate the footer */}
    <div className="footer">
        <div className="social-links">

        {/* Social media icons */}
        <img src={Location} alt="" />
        <img src={Phone} alt="" />
        <img src={Email} alt="" />
        </div>
        
        <div className="logo-f">
            <img src={Logo} alt="" />
            </div>
        </div>

        {/* Blurred background shapes */}
        <div className="blur blur-f-1"></div>
        <div className="blur blur-f-2"></div>
    </div>
  )
}

// Export the Footer component for use in other parts of the application

export default Footer    