import React from 'react';
import './BMI.css';
import Header from '../Header/Header';
import AddGymForm from './addGymForm';
import Table from './Table';
import LineCharts from './LineCharts';
import { chartData } from './LineChartData';
import backgroundImage from '../../assets/Hero2.jpg';

function BMI() {
  return (
    <>
      <div
        className="top-section"
        style={{
          backgroundImage: `url(${backgroundImage})`,
          backgroundSize: 'cover',
          backgroundRepeat: 'no-repeat',
          backgroundPosition: 'center',
          height: '15rem', // Set the height to match the Login page
        }}
      >
        <Header />
      </div>
      <div className="bmi-content">
        <div className="login-text">
          <span className="stroke-text">Track Your Progress</span>
        </div>
        <AddGymForm />
        <Table />
        <LineCharts data={chartData} />
      </div>
    </>
  );
}

export default BMI;
