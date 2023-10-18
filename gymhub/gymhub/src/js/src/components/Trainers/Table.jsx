import React from 'react';

function Table ({ appointments, onAccept, onDecline }) {
  return (
    <table>
        <thead>
          <tr>
            <th>Date</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Client Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>

          {/* Map through the appointments array and generate a row for each appointment */}
          {appointments.map((appointment) => (
            <tr key={appointment.id}>
              <td>{appointment.date}</td>
              <td>{appointment.startTime}</td>
              <td>{appointment.endTime}</td>
              <td>{appointment.clientName}</td>
              <td>

                {/* Accept button with an onClick handler that calls the onAccept function */}
                <button onClick={() => onAccept(appointment.id)}>Accept</button>
                
                {/* Decline button with an onClick handler that calls the onDecline function */}
                <button onClick={() => onDecline(appointment.id)}>Decline</button>
              </td>
            </tr>
          ))}
      </tbody>
    </table>
  )
}

export default Table;