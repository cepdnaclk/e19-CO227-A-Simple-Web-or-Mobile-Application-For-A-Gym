import React, { useState, useEffect } from 'react';
import './Hero.css'
import Header from '../Header/Header'
import backgroundImage from '../../assets/Hero1.jpg'



const Hero = () => {

  const mobile = window.innerWidth<=768 ? true: false;
    
  const [animateHiAd, setAnimateHiAd] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setAnimateHiAd(true);
    }, 1000); // Delay the animation for 1 second (adjust as needed)

    return () => clearTimeout(timer);
  }, []);

  const hiAdStyle = {
    left: animateHiAd ? '8px' : mobile? "210px" : '440px',
    transition: 'left 1s ease-in-out', // Adjust the duration and timing function
  };  

      return (
        <div
          className="hero"
          style={{
            backgroundImage: `url(${backgroundImage})`
            
          }}
        >
          <div className="blur hero-blur"></div>
          <div className="left-h">
              <Header/>
  
              {/*hi add*/}
              <div className='hi-ad'>
              <div style={hiAdStyle}></div>
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
  
  
  export default Hero;