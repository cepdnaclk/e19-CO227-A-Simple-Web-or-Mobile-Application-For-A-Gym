import React from 'react'
import './Footer.css'
import Location from "../../assets/location.jpg"
import Phone from "../../assets/Phone.jpg"
import Email from "../../assets/email.jpg"
import Logo from "../../assets/logo.png"
const Footer = () => {
  return (
    <div className="Footer-container">
    <hr />
    <div className="footer">
        <div className="social-links">
        <img src={Location} alt="" />
        <img src={Phone} alt="" />
        <img src={Email} alt="" />
        </div>
        
        <div className="logo-f">
            <img src={Logo} alt="" />
            </div>
        </div>

        <div className="blur blur-f-1"></div>
        <div className="blur blur-f-2"></div>
    </div>
  )
}

export default Footer    