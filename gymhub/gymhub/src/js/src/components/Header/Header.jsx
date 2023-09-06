import React from 'react'
import './Header.css'
import Logo from '../../assets/logo.png'

const Header = () => {
  return (
    <div className="header">
        <img src={Logo} alt='Logo' className="logo" />

        <ul className='header-menu'>
          <li>HOME</li>
          <li>ABOUT</li>
          <li>GALLERY</li>
          <li>LOGIN</li>
          <li>REGISTER</li>
          <li>BMI CALCULATOR</li>
        </ul>
    </div>
  )
}

export default Header