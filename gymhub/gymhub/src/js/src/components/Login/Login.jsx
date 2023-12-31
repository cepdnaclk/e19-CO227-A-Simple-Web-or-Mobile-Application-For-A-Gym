
import  React, { useRef, useState, useEffect, useContext } from 'react';
import Header from "../Header/Header"
import backgroundImage from '../../assets/Hero2.jpg'
import './Login.css'
import { Link } from 'react-router-dom';
import AuthContext from "../../context/AuthProvider";
import axios from '../../api/axios';

const Login = () => {

    // Access the authentication context
    const { setAuth } = useContext(AuthContext);

    // Create refs for user input and error messages
    const userRef = useRef();
    const errRef = useRef();

    // State to store user input for email, password, error message, and login success
    const [email, setEmail] = useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);
   
    // Focus on the user input field when the component mounts
    useEffect(() => {
        userRef.current.focus();
    }, [])

    // Clear error message when email or password changes
    useEffect(() => {
        setErrMsg('');
    }, [email, pwd])

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(email);

        try {
            const response = await axios.post('api/v1/login',
                JSON.stringify({ email, pwd }),
                {
                    headers: { 'Content-Type': 'application/json' },
                    withCredentials: true
                }
            );

            console.log(JSON.stringify(response?.data));
            //console.log(JSON.stringify(response));

            // Extract data from the response
            const accessToken = response?.data?.accessToken;
            const roles = response?.data?.roles;

            // Update authentication context with user data
            setAuth({ email, pwd, roles, accessToken });

            // Reset email and password input fields
            setEmail('');
            setPwd('');

            // Set login success
            setSuccess(true);
        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            } else if (err.response?.status === 400) {
                setErrMsg('Missing Username or Password');
            } else if (err.response?.status === 401) {
                setErrMsg('Unauthorized');
            } else {
                setErrMsg('Login Failed');
            }
            errRef.current.focus();
        }
    }

  return (
    <>
        <section className="login-section">
            {success ? (
                <section>
                    <h1>You are logged in!</h1>
                    <br />
                    <p>
                        <Link to="/">Go to Home</Link>
                    </p>
                </section>
            ) : (
        <section>
        <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
            <div className="login">
                <div className='leftlog'>
                    <div className="headersection"><Header/></div>
                    <div className='login-text'>
                    <div><span className='stroke-text'>WELCOME BACK</span>
                  </div>
                    </div>
                </div>
                <div className="style" style={{
            backgroundImage: `url(${backgroundImage})`, backgroundSize: 'cover', // or 'contain' depending on your preference
            backgroundRepeat: 'no-repeat',
            backgroundPosition: 'center'}}
            ></div>
            <form onSubmit={handleSubmit} className="loginform">
                        <label htmlFor="email" className="loginlable">Email:</label>
                        <input
                            type="email"
                            id="email"
                            onChange={(e) => setEmail(e.target.value)}
                            ref={userRef}
                            autoComplete="off"
                            value={email}
                            required
                            className='logininput' 
                        />

                        <label htmlFor="password" className="loginlable">Password:</label>
                        <input
                            type="password"
                            id="password"
                            onChange={(e) => setPwd(e.target.value)}
                            value={pwd}
                            required
                            className='logininput'
                        />

                        <button className="signinbutton">Sign In</button>

                        <p style={{color:'white'}}>
                        Need an Account?<br /><br/>
                        
                        <Link to='/register' className="no-underlinelogin" style={{textDecoration:'none', color:'white'}} onMouseEnter={(e) => (e.target.style.color = 'red')}
  onMouseLeave={(e) => (e.target.style.color = 'white')}>SIGN UP</Link>
                        </p>
            </form>
        </div>
    </section>
    )}
    </section>
    </>
  )
}

export default Login;


