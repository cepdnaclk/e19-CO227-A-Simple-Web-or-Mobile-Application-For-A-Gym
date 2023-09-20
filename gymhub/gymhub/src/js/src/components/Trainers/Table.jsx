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
        {appointments.map((appointment) => (
          <tr key={appointment.id}>
            <td>{appointment.date}</td>
            <td>{appointment.startTime}</td>
            <td>{appointment.endTime}</td>
            <td>{appointment.clientName}</td>
            <td>
              <button onClick={() => onAccept(appointment.id)}>Accept</button>
              <button onClick={() => onDecline(appointment.id)}>Decline</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
    )
}

export default Table;