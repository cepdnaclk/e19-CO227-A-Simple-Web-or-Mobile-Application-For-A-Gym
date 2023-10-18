// Import the axios library
import axios from 'axios';

// Create and export an instance of axios with a specific configuration
export default axios.create({
    // Define the base URL for the HTTP request
    baseURL: 'http://localhost:8080'  
});