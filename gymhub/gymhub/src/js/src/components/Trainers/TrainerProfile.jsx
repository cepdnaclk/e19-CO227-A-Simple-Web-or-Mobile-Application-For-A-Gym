import React, { useState, useEffect } from 'react'
import Header from "../Header/Header";
import backgroundImage from "../../assets/Hero2.jpg"
import './TrainerProfile.css'
import {Routes, Route, Link, useLocation, useNavigate } from 'react-router-dom'
import TrainerOverview from "./TrainerOverview"
import Appointments from "./Appointments"

const TrainerProfile = () => {

        const [TrainerName, setTrainerName] = useState('');
        const location = useLocation();
        const navigate = useNavigate();
        const [showConfirmation, setShowConfirmation] = useState(false);

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

        // Function to handle logout confirmation
  const handleLogout = () => {
    // Display the confirmation prompt
    setShowConfirmation(true);
  };

  // Function to confirm and perform the logout action
  const confirmLogout = () => {
    // Perform the logout action (e.g., clear user session)
    // Redirect to the logout route or perform any other necessary actions
    localStorage.removeItem('accessToken'); // Remove the access token
    navigate('/login'); // Redirect to the logout route
  };

  // Function to cancel logout
  const cancelLogout = () => {
    // Hide the confirmation prompt
    setShowConfirmation(false);
  };

  // Function to handle user deletion
  const handleDeleteUser = () => {
    const confirmDelete = window.confirm('Are you sure you want to delete your account? This action cannot be undone.');

    if (confirmDelete) {
      // Make an HTTP request to delete the user's account
      fetch('/api/user/delete', {
        method: 'DELETE',
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
        },
      })
      .then((response) => {
        if (response.ok) {
          // Redirect to the login page or perform any other action
          // after successful deletion
          window.location.href = '/login'; // Replace with the desired URL
        } else {
          console.error('Error deleting user account');
        }
      })
      .catch((error) => {
        console.error('Error deleting user account:', error);
      });
    }
  };

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
                    
                    <li className="no-underline"><Link to="/appointments" className={location.pathname === '/appointments' ? 'active-link' : ''} style={{textDecoration: 'none', color:'brown'}}>Appointments</Link></li>
                    <li className="no-underline"><Link to="/trainersettings" className={location.pathname === '/trainersettings' ? 'active-link' : ''} style={{textDecoration: 'none', color:'brown'}}>Profile</Link></li>
                </ul>
                <div className="trainerprofilebuttons">
                <button className="btn" onClick={handleLogout}>Logout</button>
                <button className="btn" onClick={handleDeleteUser}>Delete Account</button>
                </div>
            </div>
       {/* Logout confirmation prompt */}
      {showConfirmation && (
        <div className="confirmation-modal">
          <p>Are you sure you want to logout?</p>
          <button className="btn" onClick={confirmLogout}>
            Yes
          </button>
          <button className="btn" onClick={cancelLogout}>
            No
          </button>
        </div>
      )}
        
    </div>
  )
}

export default TrainerProfile;