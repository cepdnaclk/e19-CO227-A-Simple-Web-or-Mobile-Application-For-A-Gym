

import './App.css';
import { getAllTrainers } from './client';

import Hero from './components/Hero/Hero';
import Footer from './components/Footer/Footer';
import Login from './components/Login/Login';
import Register from './components/Register/Register'
import Join from "./components/Join/Join";
import BMI from './components/BMI/BMI';
import {Routes, Route } from 'react-router-dom';
import TrainerProfile from './components/Trainers/TrainerProfile';
import Appointments from './components/Trainers/Appointments'
import TrainerSettings from './components/Trainers/TrainerSettings';
import ViewTrainers from './components/Trainee/ViewTrainers';
import Bookings from './components/Trainee/Bookings';
import TraineeSettings from './components/Trainee/TraineeSettings'
import TraineeProfile from './components/Trainee/TraineeProfile';


function App() {
  
    getAllTrainers()
      .then((response) => {
        
        return response.data; // Extract the response data
        
      })
      .then((trainers) => {
        console.log(trainers);
      })
      
      
  

  return (
  <div className = "App">
    
      <Routes>
        
        <Route path='/' element={< Hero/>} />
        
        <Route path='/login' element={<Login />} />
        
        <Route path='/register' element={<Register/>} />
        
        
        <Route path='/trainerprofile' element={<TrainerProfile />} />
        <Route path='/appointments' element={<Appointments />} />
        <Route path='/trainersettings' element={<TrainerSettings />} />
        <Route path='/traineeprofile' element={<TraineeProfile />} />
        <Route path='/bookings' element={<Bookings />} />
        <Route path='/traineesettings' element={<TraineeSettings />} />
        <Route path='/viewtrainers' element={<ViewTrainers />} />
        <Route path='/bmi' element={<BMI />} />
      </Routes>
      <Join/>
      <Footer/>

  </div>
  );
  
}

export default App;

