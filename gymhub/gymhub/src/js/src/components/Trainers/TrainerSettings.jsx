import React, { useState } from 'react';
import './TrainerSettings.css'
import TrainerProfile from './TrainerProfile';



const TrainerSettings = () => {

    const [trainerDetails, setTrainerDetails] = useState({
        name: '',
        username: '',
        phoneNumber: '',
        address: '',
        height: '',
        weight: '',
        bmi: '',
        email: '',
        password: '',
        nic: '',
      });
    
     // const [timeSlots, setTimeSlots] = useState([]);
      const [newTimeSlot, setNewTimeSlot] = useState({
        day: '',
        startTime: '',
        endTime: '',
      });
    
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
        <form onSubmit={handleSubmitTrainerDetails}
        style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }} className="trainerdetailsform">
        <input
          type="text"
          name="name"
          value={trainerDetails.name}
          onChange={handleTrainerDetailsChange}
          placeholder="Name"
          className='trainerdetails'
          style={{width: '40rem', backgroundColor: 'black'}}
        />
        <input
          type="text"
          name="email"
          value={trainerDetails.email}
          onChange={handleTrainerDetailsChange}
          placeholder="Email"
          className='trainerdetails'
          style={{width: '40rem', backgroundColor: 'black'}}
        />
        <input
          type="text"
          name="phoneNumber"
          value={trainerDetails.phoneNumber}
          onChange={handleTrainerDetailsChange}
          placeholder="Phone Number"
          className='trainerdetails'
          style={{width: '40rem', backgroundColor: 'black'}}
        />
       <input
          type="text"
          name="username"
          value={trainerDetails.username}
          onChange={handleTrainerDetailsChange}
          placeholder="Username"
          className='trainerdetails'
          style={{width: '40rem', backgroundColor: 'black'}}
        />
        <input
          type="text"
          name="password"
          value={trainerDetails.password}
          onChange={handleTrainerDetailsChange}
          placeholder="Password"
          className='trainerdetails'
          style={{width: '40rem', backgroundColor: 'black'}}
        />
        <input
          type="text"
          name="nic"
          value={trainerDetails.nic}
          onChange={handleTrainerDetailsChange}
          placeholder="Nic"
          className='trainerdetails'
          style={{width: '40rem', backgroundColor: 'black'}}
        />
        <input
          type="text"
          name="height"
          value={trainerDetails.height}
          onChange={handleTrainerDetailsChange}
          placeholder="Height"
          className='trainerdetails'
          style={{width: '40rem', backgroundColor: 'black'}}
        />
        <input
          type="text"
          name="weight"
          value={trainerDetails.weight}
          onChange={handleTrainerDetailsChange}
          placeholder="Weight"
          className='trainerdetails'
          style={{width: '40rem', backgroundColor: 'black'}}
        />
        <input
          type="text"
          name="bmi"
          value={trainerDetails.bmi}
          onChange={handleTrainerDetailsChange}
          placeholder="BMI"
          className='trainerdetails'
          style={{width: '40rem', backgroundColor: 'black'}}
        />
        <button type="submit" className="trainerdetailssubmit" style={{width: '40rem'}}>Save</button>
      </form>
      <div  className="time">
      <h3 style={{color: 'white', marginLeft: '36rem', fontSize: '1.5rem', marginBottom: '2rem'}}>Add a Time Slot</h3>
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
      <label style={{marginLeft: '3rem', fontSize:'1.25rem'}}>
        Start Time:
        <input
          type="time"
          name="startTime"
          value={newTimeSlot.startTime}
          onChange={(e) => setNewTimeSlot({ ...newTimeSlot, startTime: e.target.value })}
          className="traineravailabletime"
          style={{ color: 'black', marginLeft: '1rem', backgroundColor: 'rgb(188, 88, 88)', fontSize:'1.25rem' }}/>
        
      </label>
      <label style={{marginLeft: '3rem', fontSize:'1.25rem'}}>
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
      <button onClick={handleAddTimeSlot} style={{marginLeft: '41.5rem', marginTop: '3rem', width: '40rem'}}>Add</button>
      </div>
      {/* Display existing time slots */}
      {timeSlots.map((timeSlot, index) => (
        <div key={index} style={{color: 'white', backgroundColor: 'gray', marginLeft: "10rem", marginRight: "10rem", paddingBottom:"1.5rem", paddingTop:"0.5rem"}}>
          {/* Display time slot information */}
          <span style={{marginLeft: '5rem', marginTop: '-3rem'}}>{timeSlot.day} - {timeSlot.startTime} to {timeSlot.endTime}</span>
          <button onClick={() => handleRemoveTimeSlot(timeSlot)} style={{marginLeft: '30rem', width:'25rem'}}>Remove</button>
        </div>
    ))}
    <div></div>
    </>
  );

      }

export default TrainerSettings;