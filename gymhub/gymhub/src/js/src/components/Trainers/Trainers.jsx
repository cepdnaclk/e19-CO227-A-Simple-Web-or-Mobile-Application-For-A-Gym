import React, { Component } from 'react';
import Header from "../Header/Header";
import backgroundImage from "../../assets/Hero2.jpg"


import "./Trainers.css"




class Trainers extends Component {
    render() {
        return (
            <>
            <section>
            <div className="trainers">
                <div className='leftt'>
        <Header/>
        <div className='login-text'>
        <div><span className='stroke-text'>FIND A TRAINER</span>
        </div>
        
        </div>
        </div>
        <div className="style" style={{
        backgroundImage: `url(${backgroundImage})`}}
        ></div>

        <div className="container">
            <input type="search" className="inputSearch" placeholder="Search Trainer" />
            <button>Search</button>
        </div>
            
        </div>
        </section>
            </>
        );
    }
    
}

export default Trainers


