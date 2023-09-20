import React, { useState } from 'react';
import './Appointments.css'
import Table from './Table'
import TrainerProfile from './TrainerProfile';



const Appointments = () => {

    // State to manage appointments
    //const [appointments, setAppointments] = useState([ ... ]); // Initialize with your appointments data
    const [appointments, setAppointments] = useState([]);


    // Function to accept an appointment
    const handleAccept = (appointmentId) => {
        // Make an API request to mark the appointment as accepted in the database
        fetch(`/api/appointments/${appointmentId}/accept`, {
            method: 'PUT', // Assuming we use a PUT request to update the appointment status
        })
        .then((response) => {
            if (response.ok) {
                // If the update was successful, update the appointments state
                setAppointments((prevAppointments) =>
                prevAppointments.map((appointment) =>
                    appointment.id == appointmentId
                    ? { ...appointment, status: 'accepted' }
                    : appointment
                )
                );
            } else {
                console.log("Hi");
            }
        })
        .catch((error) => {
            console.error('Error accepting appointment: ', error);
        });
    };

    // Function to decline an appointment
    const handleDecline = (appointmentId) => {
        // Make an API request to delete the appointment from the database
    fetch(`/api/appointments/${appointmentId}`, {
        method: 'DELETE', // Assuming we use a DELETE request to delete the appointment
      })
      .then((response) => {
        if (response.ok) {
             // If the deletion was successful, update the appointments state
             setAppointments((prevAppointments) => 
                prevAppointments.filter((appointment) => appointment.id !== appointmentId)
             );
        } else {
            console.log("hi");
        }
      })
      .catch((error) => {
        console.error('Error declining appointment: ', error)
      });
    };

  return (
    <>
        <TrainerProfile/>
        
            <Table
            appointments={appointments}
            onAccept={handleAccept}
            onDecline={handleDecline}/>
        
    </>
  )
}

export default Appointments;