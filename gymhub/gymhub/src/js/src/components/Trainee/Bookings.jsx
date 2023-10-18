import React, { useState, useEffect } from 'react';
import { useAuth } from '../../context/AuthProvider';
import TraineeProfile from './TraineeProfile';

const Bookings = () => {
  const { loggedInUserId } = useAuth(); // Assuming you have a way to get the logged-in trainer's ID
  const [acceptedAppointments, setAcceptedAppointments] = useState([]);

  useEffect(() => {
    // Fetch the accepted appointments based on the logged-in trainer's ID
    fetch(`/api/trainers/${loggedInUserId}/accepted-appointments`)
      .then((response) => response.json())
      .then((data) => {
        // Assuming the data contains an array of accepted appointments
        setAcceptedAppointments(data);
      })
      .catch((error) => {
        console.error('Error fetching accepted appointments: ', error);
      });
  }, [loggedInUserId]); // Execute this effect when the loggedInUserId changes

  return (
    <>
        <section className="bookings-container">
          <div>
            <TraineeProfile /></div>
            <div className='booking' style={ {top: '17rem', right: '45rem' }}>
              <div className="bookingsheader">
            <h2 style={{color:'white'}}>Accepted Appointments</h2></div>
          <ul>
            {acceptedAppointments.map((appointment) => (
              <li key={appointment.id}>
                {/* Display appointment details */}
                <p>Date: {appointment.date}</p>
                <p>Start Time: {appointment.starttime}</p>
                <p>End Time: {appointment.endtime}</p>
                {/* Add more appointment details here */}
              </li>
            ))}
          </ul>
        </div>
      </section>
    </>
  );
};

export default Bookings;