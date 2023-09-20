import React, { useState, useEffect } from 'react'
import Header from "../Header/Header";
import backgroundImage from "../../assets/Hero2.jpg"
import './TrainerProfile.css'
import {Routes, Route, Link, useLocation } from 'react-router-dom'
import TrainerOverview from "./TrainerOverview"
import Appointments from "./Appointments"

const TrainerProfile = () => {

        const [TrainerName, setTrainerName] = useState('');
        const location = useLocation();

        useEffect(() => {
            //an HTTP request to fetch the user's name when the component mounts
            fetch('/api/user/name', { // insert the relevant url
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, // Include the user's access token
                },
            })
            .then((response) => response.json())
            .then((data) => {
                setTrainerName(data.name);  // // Set the user's name in state
            })
            .catch((error) => {
                console.error('Error fetching user name;', error);
            });
        }, []); // The empty array [] as the second argument ensures that the effect runs only once when the component mounts

  return (

    <div className="trainerprofile">
        <div className='lefttp'>
            <Header/>
            <div className='tp-text'>
            <div><span className='stroke-text'>Hi {TrainerName} </span>
            </div>
        
            </div>
        </div>
        <div className="style" style={{
        backgroundImage: `url(${backgroundImage})`}}
        ></div>
       
            <div className='container'>
                <ul className="listoflinks">
                    <li className="no-underline"><Link to="/overview" className={location.pathname === '/overview' ? 'active-link' : ''}>Overview</Link></li>
                    <li className="no-underline"><Link to="/appointments" className={location.pathname === '/appointments' ? 'active-link' : ''}>Appointments</Link></li>
                    <li className="no-underline"><Link to="/trainersettings" className={location.pathname === '/trainersettings' ? 'active-link' : ''}>Profile</Link></li>
                </ul>
                <div className="trainerprofilebuttons">
                <button className="btn">Logout</button>
                <button className="btn">Delete Account</button>
                </div>
            </div>
       
        
    </div>
  )
}

export default TrainerProfile;