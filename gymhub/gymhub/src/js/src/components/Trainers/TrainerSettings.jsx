import React, { useState, useEffect } from 'react';
import './TrainerSettings.css'
import TrainerProfile from './TrainerProfile';



const TrainerSettings = () => {

    const [trainerDetails, setTrainerDetails] = useState({
        name: '',
        email: '',
        phoneNumber: '',
        username: '',
        
        nic: '',
        height: '',
        weight: '',
        bmi: '',
        
        
        
      });
    
     // const [timeSlots, setTimeSlots] = useState([]);
      const [newTimeSlot, setNewTimeSlot] = useState({
        day: '',
        startTime: '',
        endTime: '',
      });

      const calculateBMI = () => {
        const { height, weight } = trainerDetails;
    
        if (height && weight) {
          const heightInMeters = height / 100; // Convert height to meters
          const bmi = (weight / (heightInMeters * heightInMeters)).toFixed(2); // Calculate BMI
    
          setTrainerDetails({
            ...trainerDetails,
            bmi: bmi,
          });
        }
      };

      useEffect(() => {
        calculateBMI();
      }, [trainerDetails.height, trainerDetails.weight]);
    
      const handleTrainerDetailsChange = (e) => {
        const { name, value } = e.target;
        setTrainerDetails({
          ...trainerDetails,
          [name]: value,
        });
      };
    
      const handleSubmitTrainerDetails = async (e) => {
        e.preventDefault();
        // Send a request to update the personal details in the backend
        try {
          const response = await fetch('/api/updateTrainerDetails', {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(trainerDetails),
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

      const loadTimeSlotsFromLocalStorage = () => {
        const storedTimeSlots = localStorage.getItem('timeSlots');
        return storedTimeSlots ? JSON.parse(storedTimeSlots) : [];
      };

      const saveTimeSlotsToLocalStorage = (timeSlots) => {
        localStorage.setItem('timeSlots', JSON.stringify(timeSlots));
      };
    
      const handleAddTimeSlot = () => {
        // Validation logic (e.g., checking if all fields are filled)
        if (newTimeSlot.day && newTimeSlot.startTime && newTimeSlot.endTime) {
          const updatedTimeSlots = [...timeSlots, newTimeSlot];
          setTimeSlots(updatedTimeSlots);

      // Save time slots to localStorage
      saveTimeSlotsToLocalStorage(updatedTimeSlots);
          setNewTimeSlot({ day: '', startTime: '', endTime: '' });
        } else {
          alert('Please fill in all fields.');
        }
      };

      const [timeSlots, setTimeSlots] = useState(loadTimeSlotsFromLocalStorage());
    
      const handleRemoveTimeSlot = (timeSlotToRemove) => {
        const updatedTimeSlots = timeSlots.filter((timeSlot) => timeSlot !== timeSlotToRemove);
        setTimeSlots(updatedTimeSlots);

        saveTimeSlotsToLocalStorage(updatedTimeSlots);
      };

  return (
    <>
        <TrainerProfile />
        <div className="form-container">
        <form onSubmit={handleSubmitTrainerDetails}
        style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }} className="trainerdetailsform">
          <label className='trainerprofilein' htmlFor="name">Name:</label>
        <input
          type="text"
          name="name"
          value={trainerDetails.name}
          onChange={handleTrainerDetailsChange}
          placeholder="Name"
          className='trainerdetails'
          style={{ backgroundColor: 'black'}}
        />
        <label className='trainerprofilein' htmlFor="email">Email:</label>
        <input
          type="text"
          name="email"
          value={trainerDetails.email}
          onChange={handleTrainerDetailsChange}
          placeholder="Email"
          className='trainerdetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='trainerprofilein' htmlFor="phoneNumber">Phone Number:</label>
        <input
          type="text"
          name="phoneNumber"
          value={trainerDetails.phoneNumber}
          onChange={handleTrainerDetailsChange}
          placeholder="Phone Number"
          className='trainerdetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='trainerprofilein' htmlFor="username">User Name:</label>
       <input
          type="text"
          name="username"
          value={trainerDetails.username}
          onChange={handleTrainerDetailsChange}
          placeholder="Username"
          className='trainerdetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='trainerprofilein' htmlFor="nic">NIC:</label>
        <input
          type="text"
          name="nic"
          value={trainerDetails.nic}
          onChange={handleTrainerDetailsChange}
          placeholder="Nic"
          className='trainerdetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='trainerprofilein' htmlFor="height">Height:</label>
        <input
          type="text"
          name="height"
          value={trainerDetails.height}
          onChange={handleTrainerDetailsChange}
          placeholder="Height"
          className='trainerdetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='trainerprofilein' htmlFor="weight">Weight:</label>
        <input
          type="text"
          name="weight"
          value={trainerDetails.weight}
          onChange={handleTrainerDetailsChange}
          placeholder="Weight"
          className='trainerdetails'
          style={{backgroundColor: 'black'}}
        />
        <label className='trainerprofilein' htmlFor="bmi">BMI:</label>
        <input
          type="text"
          name="bmi"
          value={trainerDetails.bmi}
          
          placeholder="BMI"
          className='trainerdetails'
          style={{backgroundColor: 'black'}}
          readOnly
        />
        <br/>
        <button type="submit" className="trainerdetailssubmit" >Save</button>
      </form>
      </div>
      <div  className="time">
      <h3 className='timeheading' >Add a Time Slot</h3>
      <label className = "day" >
        Day:
        <select
          name="day"
          value={newTimeSlot.day}
          onChange={(e) => setNewTimeSlot({ ...newTimeSlot, day: e.target.value })}
          className="traineravailableday"
          style={{backgroundColor: 'rgb(188, 88, 88)', fontSize:'1.25rem'}}
        >
          <option value="">Select Day</option>
          <option value="Monday">Monday</option>
          <option value="Tuesday">Tuesday</option>
          <option value="Wednesday">Wednesday</option>
          <option value="Thursday">Thursday</option>
          <option value="Friday">Friday</option>
          <option value="Saturday">Saturday</option>
          <option value="Sunday">Sunday</option>
        </select>
      </label>
      <label className="starttime" >
        Start Time:
        <input
          type="time"
          name="startTime"
          value={newTimeSlot.startTime}
          onChange={(e) => setNewTimeSlot({ ...newTimeSlot, startTime: e.target.value })}
          className="traineravailabletime"
          style={{ color: 'black', marginLeft: '1rem', backgroundColor: 'rgb(188, 88, 88)', fontSize:'1.25rem' }}/>
        
      </label>
      <label className="endtime">
        End Time:
        <input
          type="time"
          name="endTime"
          value={newTimeSlot.endTime}
          onChange={(e) => setNewTimeSlot({ ...newTimeSlot, endTime: e.target.value })}
          style={{ color: 'black', marginLeft: '1rem', backgroundColor: 'rgb(188, 88, 88)', fontSize:'1.25rem' }}
          className="traineravailabletime"
        />
      </label>
      <button className="addtimeslot" onClick={handleAddTimeSlot}>Add</button>
      </div>
      {/* Display existing time slots */}
      {timeSlots.map((timeSlot, index) => (
        <div key={index} className="timeslots" >
          {/* Display time slot information */}
          <span cassName="slot" >{timeSlot.day} - {timeSlot.startTime} to {timeSlot.endTime}</span>
          <button onClick={() => handleRemoveTimeSlot(timeSlot)} className='removeslot'>Remove</button>
        </div>
    ))}
    <div></div>
    </>
  );

      }

export default TrainerSettings;