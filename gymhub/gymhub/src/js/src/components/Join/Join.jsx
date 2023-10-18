
import React, { useRef } from 'react'
import './Join.css'
import emailjs from '@emailjs/browser'

const Join = () => {
    const form = useRef()

    // Function to send an email using emailjs
    /* const sendEmail = (e) => {
        e.preventDefault();
        
        // Call emailjs to send the email
        emailjs.sendForm('service_6vwdg9t', 'template_870gedn', form.current, 'SfFoyX5A4a5W9fv4P')
          .then((result) => {
              console.log(result.text);
          }, (error) => {
              console.log(error.text);
          });
      }; */   
  return (
    <div className="Join" id="join-us">
    <div className="left-j">
        <hr />
    <div>
        <span className="stroke-text">READY TO</span>
        <span>LEVEL UP</span>
    </div>
    <div>
        <span>YOUR BODY</span>
        <span className="stroke-text">WITH US?</span>
    </div>
    </div>
    {/* Uncommented code for sending an email via emailjs */}
    {/* <div className="right-j">
        <form ref={form} className="email-container" onSubmit={sendEmail}>
            <input type="email" name="user_email" placeholder="Enter your Email address" />
            <button type="button" className="btn btn-j">Join Now</button>
        </form> 
        </div>  */}   
    </div>
  )
}


export default Join;