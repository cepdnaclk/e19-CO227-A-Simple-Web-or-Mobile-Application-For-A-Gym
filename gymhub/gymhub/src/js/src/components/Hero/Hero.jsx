import React, { Component } from 'react';
import './Hero.css'
import Header from '../Header/Header'
import backgroundImage from '../../assets/Hero1.jpg'



class Hero extends Component {
    
  
    render() {
      return (
        <div
          className="hero"
          style={{
            backgroundImage: `url(${backgroundImage})`
            
          }}
        >
          <div className="left-h">
              <Header/>
  
              {/*hi add*/}
              <div className='hi-ad'>
                  <div></div>
                  <span>Hi This Is GYMHUB - Elevate Your Fitness Journey With Us</span>
              </div>
  
              {/*Hero Heading*/}
              <div className='hero-text'>
                  <div><span className='the'>THE</span></div>
                  <div><span className='stroke-text'>FITNESS HEAVEN </span>
                  </div>
                  
              </div>
             

             {/*hero buttons*/}
             
          </div>
          <div className="right-h">
            
            
          </div>
         
  
        </div>
      );
    }
  }
  
  export default Hero;