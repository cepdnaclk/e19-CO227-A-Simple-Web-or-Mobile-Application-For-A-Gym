// src/axios.js
import axios from "axios";

// Create and export an instance of axios with a specific configuration
const instance = axios.create({
    // Define the base URL for the HTTP requests
    baseURL: "http://localhost:8080",
});

export default instance;
