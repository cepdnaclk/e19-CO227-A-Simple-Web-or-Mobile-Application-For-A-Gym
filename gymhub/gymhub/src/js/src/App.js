
import './App.css';
import { getAllTrainers } from './client';
import Hero from './components/Hero/Hero';

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
      <Hero/>
  </div>
  );
  
}

export default App;
