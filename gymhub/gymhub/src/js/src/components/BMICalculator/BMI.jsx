import React, { useState } from 'react';
import './BMI.css';


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
    <div>
      <h1>BMI Calculator</h1>
      <div>
        <label htmlFor="weight">Weight (kg):</label>
        <input
          type="number"
          id="weight"
          value={weight}
          onChange={(e) => setWeight(e.target.value)}
          placeholder="Enter weight"
        />
      </div>
      <div>
        <label htmlFor="height">Height (cm):</label>
        <input
          type="number"
          id="height"
          value={height}
          onChange={(e) => setHeight(e.target.value)}
          placeholder="Enter height"
        />
      </div>
      <button onClick={calculateBMI}>Calculate BMI</button>
      <button onClick={clearFields}>Clear</button>
      <div>
        {bmi && <p>Your BMI is: {bmi}</p>}
        {message && <p>{message}</p>}
      </div>
    </div>
  );
}

export default BMICalculator;
