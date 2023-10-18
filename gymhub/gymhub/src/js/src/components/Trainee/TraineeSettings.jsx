import React, { useState, useEffect } from 'react';
import './TraineeSettings.css'
import TraineeProfile from './TraineeProfile';

const TraineeSettings = () => {
    // State to store trainee details
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
    
    // Function to calculate BMI
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
  
    // Function to handle trainee details change
    const handleTraineeDetailsChange = (e) => {
      const { name, value } = e.target;
      setTraineeDetails({
        ...traineeDetails,
        [name]: value,
      });
    };
    
    // Function to submit trainee details
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

          {/* Include the TraineeProfile component */}
          <TraineeProfile />

          {/* Container for the trainee details form */}
          <div className="form-container">
            <form 
            onSubmit={handleSubmitTraineeDetails}
            style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }} 
            className="traineedetailsform"
          >

            {/* Input field for trainee's name */}
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

            {/* Input field for trainee's email */}
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

            {/* Input field for trainee's phonenumber */}
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

            {/* Input field for trainee's username */}
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

            {/* Input field for trainee's nic */}
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

            {/* Input field for trainee's height */}
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

            {/* Input field for trainee's weight */}
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

            {/* Input field for trainee's bmi */}
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

            {/* Submit button to save the trainee details */}
            <button type="submit" className="traineedetailssubmit">Save</button>
        </form>
      </div>
      <div></div>
    </>
  );
}

export default TraineeSettings;