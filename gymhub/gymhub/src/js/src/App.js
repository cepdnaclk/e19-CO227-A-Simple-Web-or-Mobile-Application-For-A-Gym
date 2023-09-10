
import './App.css';
import { getAllTrainers } from './client';

import Hero from './components/Hero/Hero';
import Footer from './components/Footer/Footer';
import Login from './components/Login/Login';
import { Register } from './components/Register/Register';
import Join from "./components/Join/Join";
import {Routes, Route } from 'react-router-dom';

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
        
        <Route path='/login' element={<Login/>} />
        <Route path='/register' element={<Register/>} />
        
      </Routes>
      <Join/>
      <Footer/>

  </div>
  );
  
}

export default App;
