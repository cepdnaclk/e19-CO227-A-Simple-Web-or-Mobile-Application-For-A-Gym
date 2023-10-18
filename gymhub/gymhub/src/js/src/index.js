import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import { AuthProvider } from './context/AuthProvider';

// Create a root to render your React app

const root = ReactDOM.createRoot(document.getElementById('root'));

// Render the app inside a BrowserRouter and wrap it with AuthProvider
root.render(
  <BrowserRouter>
  <AuthProvider>
  <App />
  </AuthProvider>
    
  </BrowserRouter>
);

reportWebVitals();
