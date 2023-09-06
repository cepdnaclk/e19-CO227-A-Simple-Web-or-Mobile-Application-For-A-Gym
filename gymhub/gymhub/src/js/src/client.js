import axios from 'axios';

export const getAllTrainers = () => {
  const apiUrl = '/trainers'; // Replace with your API URL
  return axios.get(apiUrl);
};
