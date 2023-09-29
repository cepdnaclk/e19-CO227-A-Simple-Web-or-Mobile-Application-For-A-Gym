import React, { useState, useEffect } from 'react';
import './TraineeSettings.css'
import TraineeProfile from './TraineeProfile';



const TraineeSettings = () => {

    const [traineeDetails, setTraineeDetails] = useState({
        name: '',
        username: '',
        phoneNumber: '',
        address: '',
        height: '',
        weight: '',
        bmi: '',
        email: '',
       
        nic: '',
      });
    
      const calculateBMI = () => {
        const { height, weight } = traineeDetails;
    
        if (height && weight) {
          const heightInMeters = height / 100; // Convert height to meters
          const bmi = (weight / (heightInMeters * heightInMeters)).toFixed(2); // Calculate BMI
    
          setTraineeDetails({
            ...traineeDetails,
            bmi: bmi,
          });
        }
      };

      useEffect(() => {
        calculateBMI();
      }, [traineeDetails.height, traineeDetails.weight]);
    
      const handleTraineeDetailsChange = (e) => {
        const { name, value } = e.target;
        setTraineeDetails({
          ...traineeDetails,
          [name]: value,
        });
      };
    
      const handleSubmitTraineeDetails = async (e) => {
        e.preventDefault();
        // Send a request to update the personal details in the backend
        try {
          const response = await fetch('/api/updateTraineeDetails', {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(traineeDetails),
          });
    
          if (response.ok) {
            console.log('Trainer details updated successfully.');
          } else {
            console.error('Failed to update trainer details.');
          }
        } catch (error) {
          console.error('Error updating trainer details:', error);
        }
      };

      

  return (
    <>
        <TraineeProfile />
        <div className="form-container">
        <form onSubmit={handleSubmitTraineeDetails}
        style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }} className="traineedetailsform">
        <label className='traineeprofilein' htmlFor="name">Name:</label>
        <input
          type="text"
          name="name"
          value={traineeDetails.name}
          onChange={handleTraineeDetailsChange}
          placeholder="Name"
          className='traineedetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='traineeprofilein' htmlFor="email">Email:</label>
        <input
          type="text"
          name="email"
          value={traineeDetails.email}
          onChange={handleTraineeDetailsChange}
          placeholder="Email"
          className='traineedetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='traineeprofilein' htmlFor="phoneNumber">Phone Number:</label>
        <input
          type="text"
          name="phoneNumber"
          value={traineeDetails.phoneNumber}
          onChange={handleTraineeDetailsChange}
          placeholder="Phone Number"
          className='traineedetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='traineeprofilein' htmlFor="username">User Name:</label>
       <input
          type="text"
          name="username"
          value={traineeDetails.username}
          onChange={handleTraineeDetailsChange}
          placeholder="Username"
          className='traineedetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='traineeprofilein' htmlFor="nic">NIC:</label>
        <input
          type="text"
          name="nic"
          value={traineeDetails.nic}
          onChange={handleTraineeDetailsChange}
          placeholder="Nic"
          className='traineedetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='traineeprofilein' htmlFor="height">Height:</label>
        <input
          type="text"
          name="height"
          value={traineeDetails.height}
          onChange={handleTraineeDetailsChange}
          placeholder="Height"
          className='traineedetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='traineeprofilein' htmlFor="weight">Weight:</label>
        <input
          type="text"
          name="weight"
          value={traineeDetails.weight}
          onChange={handleTraineeDetailsChange}
          placeholder="Weight"
          className='traineedetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='traineeprofilein' htmlFor="bmi">    BMI:</label>
        <input
          type="text"
          name="bmi"
          value={traineeDetails.bmi}
          
          placeholder="BMI"
          className='traineedetails'
          style={{backgroundColor: 'black'}}
          readOnly
        />
        <button type="submit" className="traineedetailssubmit">Save</button>
      </form>
      </div>
      
    
    <div></div>
    </>
  );

      }

export default TraineeSettings;