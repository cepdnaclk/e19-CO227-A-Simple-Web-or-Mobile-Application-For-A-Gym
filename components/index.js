import React from 'react';//Import necessary modules from the react library 
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
//Import function for measuring web performance
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
  document.getElementById('root')
);

/* If you want to start measuring performance in your app, pass a function
/to log results (for example: reportWebVitals(console.log))
/ or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
*/
reportWebVitals();