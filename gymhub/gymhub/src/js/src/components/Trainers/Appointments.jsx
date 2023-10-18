
import React, { useState, useEffect } from 'react';
import './Appointments.css'
import Table from './Table'
import TrainerProfile from './TrainerProfile';
import { useAuth } from '../../context/AuthProvider';


const Appointments = () => {

    const { loggedInUserId } = useAuth(); // Retrieve the logged-in user's ID from the AuthContext

    // State to manage appointments
    const [pendingAppointments, setPendingAppointments] = useState([]);
    const [acceptedAppointments, setAcceptedAppointments] = useState([]);

    useEffect(() => {

        // Fetch the user's appointments based on the logged-in user's ID
        fetch(`/api/users/${loggedInUserId}/appointments`)
          .then((response) => response.json())
          .then((data) => {

            // Split the fetched data into pending and accepted appointments
            const pending = data.filter(appointment => appointment.status === 'pending');
            const accepted = data.filter(appointment => appointment.status === 'accepted');
            setPendingAppointments(pending);
            setAcceptedAppointments(accepted);
          })
          .catch((error) => {
            console.error('Error fetching appointments: ', error);
          });
    }, [loggedInUserId]); // Trigger the effect whenever the logged-in user ID changes


    // Function to accept an appointment
    const handleAccept = (appointmentId) => {

        // Make an API request to mark the appointment as accepted in the database
        fetch(`/api/appointments/${appointmentId}/accept`, {
            method: 'PUT', // Assuming we use a PUT request to update the appointment status
        })
        .then((response) => {
            if (response.ok) {

                // If the update was successful, update the appointments state
                setPendingAppointments((prevAppointments) =>
                    prevAppointments.map((appointment) =>
                        appointment.id === appointmentId
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
             setPendingAppointments((prevAppointments) => 
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

        {/* Display Trainer's Profile */}
        <div>
            <TrainerProfile/>
        </div>

        {/* Container for Accepted Appointments */}
        <div className="acont">
            <h2 style={{color:'white'}}>Accepted Appointments</h2>

            {/* Render a table with accepted appointments, allowing actions to accept or decline */}
            <Table 
                className="tcont"
                appointments={acceptedAppointments}
                onAccept={handleAccept}
                onDecline={handleDecline}
            />
        </div>

        {/* Container for Pending Appointments */}
        <div className="rcont">
            <h2 style={{color:'white'}}>Pending Appointments</h2>
        
            {/* Render a table with pending appointments, allowing actions to accept or decline */}
            <Table 
                className="tcont"
                appointments={pendingAppointments}
                onAccept={handleAccept}
                onDecline={handleDecline}
            />
        </div>
    </>
  )
}


export default Appointments;