// src/axios.js
import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8080", // Replace with your Spring Boot API's base URL
});

export default instance;
