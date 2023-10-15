import axios from 'axios';

// Define a function to fetch all trainers
export const getAllTrainers = () => {
  const apiUrl = '/trainers'; 

  // Send a GET request to the API endpoint
  return axios.get(apiUrl);
};
