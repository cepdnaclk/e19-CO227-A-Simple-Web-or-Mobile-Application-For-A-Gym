// Import the axios library
import axios from 'axios';
const BASE_URL = 'http://localhost:8080'

// Create and export an instance of axios with a specific configuration
export default axios.create({

    // Define the base URL for the HTTP request
    baseURL: 'http://localhost:8080'  
});

