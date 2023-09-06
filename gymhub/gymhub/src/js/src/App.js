
import './App.css';
import { getAllTrainers } from './client';

function App() {
  
    getAllTrainers()
      .then((response) => {
        
        return response.data; // Extract the response data
      })
      .then((trainers) => {
        console.log(trainers);
      })
      
      
  

  return <h1>Hello World</h1>;
}

export default App;
