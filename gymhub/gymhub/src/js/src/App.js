
import './App.css';
import { getAllTrainers } from './client';
import Hero from './components/Hero/Hero';
import Join from './components/Join/Join';
import Footer from './components/Footer/Footer';

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
      <Join />
      <Footer />
  </div>
  );
  
}

export default App;
