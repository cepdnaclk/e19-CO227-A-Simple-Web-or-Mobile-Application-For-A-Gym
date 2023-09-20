
import { React, useRef, useState, useEffect, useContext } from 'react';
import Header from "../Header/Header"
import backgroundImage from '../../assets/Hero2.jpg'
import './Login.css'
import { Link } from 'react-router-dom';
import AuthContext from "../../context/AuthProvider";

import axios from '../../api/axios';
const LOGIN_URL = '/auth';


const Login = () => {

    

    const { setAuth } = useContext(AuthContext);
    const userRef = useRef();
    const errRef = useRef();

    const [user, setUser] = useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [user, pwd])

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(LOGIN_URL,
                JSON.stringify({ user, pwd }),
                {
                    headers: { 'Content-Type': 'application/json' },
                    withCredentials: true
                }
            );
            console.log(JSON.stringify(response?.data));
            //console.log(JSON.stringify(response));
            const accessToken = response?.data?.accessToken;
            const roles = response?.data?.roles;
            setAuth({ user, pwd, roles, accessToken });
            setUser('');
            setPwd('');
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
                        <label htmlFor="username" className="loginlable">Username:</label>
                        <input
                            type="text"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setUser(e.target.value)}
                            value={user}
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

                        <p>
                        Need an Account?<br /><br/>
                        
                        <Link to='/register' className="no-underline">SIGN UP</Link>
                           
            
                       
                    </p>
                        </form>

            </div>
        </section>
            )}
    </>
  )
}

export default Login;