import React, {useEffect, useState} from 'react';
import axios from 'axios';
import TraineeProfile from './TraineeProfile';
import { useAuth } from '../../context/AuthProvider';
import './ViewTrainers.css';

const ViewTrainers = () => {
  const { traineeId } = useAuth();
  const [users, setUsers] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredUsers, setFilteredUsers] = useState([]);

  useEffect(() => {
    axios.get('/api/users')
    .then((response) => {
      setUsers(response.data);
    })
    .catch((error) => {
      console.error(error);
    });
  }, []);

   // Handle search input change
   const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  // Handle search button click
  const handleSearch = () => {
    if (searchTerm.trim() === '') {
      // If search term is empty, display the full list
      setFilteredUsers([]);
    } else {
      // Filter the list based on the search term
      const filtered = users.filter((user) =>
        user.name.toLowerCase().includes(searchTerm.toLowerCase())
      );
      setFilteredUsers(filtered);
    }
  };

  // Assuming you have retrieved the trainee's ID and stored it in a variable
//const traineeId = getTraineeIdFromAuthentication(); // Replace this with your actual implementation

const handleAppointmentRequest = (trainerId) => {
  // Create an appointment request object
  const appointmentRequest = {
    trainerId, // The ID of the selected trainer
    traineeId, // The actual trainee's ID
    // Add any other required data for the appointment request
  };

  // Send a POST request to your backend API
  axios.post('/api/appointment-requests', appointmentRequest)
    .then((response) => {
      // Handle success, e.g., show a success message
      console.log('Appointment request sent successfully');
      console.log('Response data:', response.data);
    })
    .catch((error) => {
      // Handle error, e.g., show an error message
      console.error('Error sending appointment request:', error);
    });
};

  return (
    <>
    <section className='view'>
      <div><TraineeProfile /></div>
      <div>
      <div className='search-container' >
        <h1 className='heading' style={{color:'white'}}>Find a Trainer</h1>
        <div style={{display: 'flex', alignItems: 'center'}}>
          <input type="searchin" style={{backgroundColor: 'rgb(188, 88, 88)', width:'20rem', height:'2.1rem', borderRadius:'0.375rem', cursor:'pointer'}} placeholder='search Trainer' value={searchTerm} onChange={handleSearchChange}/>
          <button className="search-button" onClick={handleSearch}>Search</button>
          </div>
      </div>
      </div>
    <div>
    <ul>
            {searchTerm === '' ? (
              // Display full list when search term is empty
              users.map((user) => (
                <li key={user.id}>
                  {user.name}
                  <ul>
                    {user.timeSlots.map((timeSlot) => (
                      <li key={timeSlot.id}>{timeSlot.slot}</li>
                    ))}
                  </ul>
                </li>
              ))
            ) : filteredUsers.length === 0 ? (
              <p className='msg'>No trainers found.</p>
            ) : (
              // Display filtered list when search term is not empty
              filteredUsers.map((user) => (
                <li key={user.id}>
                  {user.name}
                  <button
                    onClick={() => handleAppointmentRequest(user.id)} // Pass trainer's ID to the handler
                    style={{ marginLeft: '1rem' }}
                  >
                    Request Appointment
                  </button>
                  <ul>
                    {user.timeSlots.map((timeSlot) => (
                      <li key={timeSlot.id}>{timeSlot.slot}</li>
                    ))}
                  </ul>
                </li>
              ))
            )}
          </ul>
      
    </div>
    </section>
    </>
  );
        }

export default ViewTrainers;