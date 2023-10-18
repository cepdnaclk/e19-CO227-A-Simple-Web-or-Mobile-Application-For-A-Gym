import React, { useState } from 'react';
import './BMI.css';
import backgroundImage from '../../assets/Hero2.jpg'
import Header from '../Header/Header'

function BMICalculator() {
  const [weight, setWeight] = useState('');
  const [height, setHeight] = useState('');
  const [bmi, setBMI] = useState(null);
  const [message, setMessage] = useState('');

  const calculateBMI = () => {
    const weightKg = parseFloat(weight);
    const heightMeters = parseFloat(height) / 100;
    const calculatedBMI = (weightKg / (heightMeters * heightMeters)).toFixed(2);
    setBMI(calculatedBMI);

    if (calculatedBMI <= 18.5) {
      setMessage('You are underweight');
    } else if (calculatedBMI <= 24.9) {
      setMessage('You are a healthy weight');
    } else {
      setMessage('You are overweight');
    }
  };

  const clearFields = () => {
    setWeight('');
    setHeight('');
    setBMI(null);
    setMessage('');
  };

  return (
    <>
             
    <section>

      <div
          className="bmi">
          
          <div className='leftbmi'>
            <div className='bmiheader'>
          <Header/>
                </div>
          
        
        <div className='bmi-text'>
          
                  <div><span className='stroke-text'>BMI CALCULATOR</span>
                  </div>
                  
              </div>
              </div>
              <div className="style" style={{
            backgroundImage: `url(${backgroundImage})`
            
          }}
        ></div>
      
      <div className="leftf">
      <form className="lf" >
                        <label className='BMIlabel' htmlFor="weight">
                            Weight:
                        </label>
                        <input
                            className='bmiinput'
                            type="number"
                            id="weight"
                            
                            onChange={(e) => setWeight(e.target.value)}
                            
                           
                        />
                        

                        <label className='bmilabel' htmlFor="height">
                            Height:
                            
                        </label>
                        <input
                            className='bmiinput'
                            type="number"
                            id="height"
                            
                            onChange={(e) => setHeight(e.target.value)}
                            
                        />
      <button className='bmibutton' onClick={calculateBMI}>Calculate BMI</button>
      <button className='bmibutton' onClick={clearFields}>Clear</button>
     
        {bmi && <p className='bmip'>Your BMI is: {bmi}</p>}
        {message && <p className='bmip'>{message}</p>}
        </form>
          </div>
    
          
          
          </div> 
        </section>
                
        </>
      )
    }

export default BMICalculator;
