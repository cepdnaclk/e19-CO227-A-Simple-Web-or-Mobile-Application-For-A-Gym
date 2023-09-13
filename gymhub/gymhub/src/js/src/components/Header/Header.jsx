import React from 'react'
import './Header.css'
import Logo from '../../assets/logo.png'
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <div className="header">
        <img src={Logo} alt='Logo' className="logo" />

        <ul className='header-menu'>
          <li><Link to='/' className="no-underline">HOME</Link></li>
          <li>ABOUT</li>
          <li>GALLERY</li>
          
          <li><Link to='/login' className="no-underline">LOGIN</Link></li>
          <li><Link to='/register' className="no-underline">REGISTER</Link></li>
          <li><Link to='/appointments' className="no-underline">APPOINTMENTS</Link></li>
          <li>BMI CALCULATOR</li>
        </ul>
    </div>
  )
}

export default Header