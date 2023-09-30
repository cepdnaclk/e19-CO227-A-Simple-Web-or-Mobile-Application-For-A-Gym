import React from 'react'
import { useRef, useState, useEffect } from "react";
import { faCheck, faTimes, faInfoCircle } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import backgroundImage from '../../assets/Hero2.jpg'
import Header from '../Header/Header'
import './Register.css'
import '../Login/Login'
import { Link } from 'react-router-dom';
import axios from '../../api/axios';


const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const NAME_REGEX = /^[A-Za-z\s]{2,23}$/;
const PHONE_REGEX = /^[0-9]{10}$/;
const EMAIL_REGEX = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;
const NIC_REGEX = /^[0-9]{9}[vV]$/;
const NIC_REGEX_2 = /^[0-9]{12}$/;
const REGISTER_URL = '/register';



export const Register = () => {
  
  const userRef = useRef();
  const errRef = useRef();

  const [name, setName] = useState('');
  const [valid_Name, setValidName] = useState(false);
  const [nameFocus, setNameFocus] = useState(false);

    const [user, setUser] = useState('');
    const [validName, setValidUser] = useState(false);
    const [userFocus, setUserFocus] = useState(false);

    const [email, setEmail] = useState('');
    const [validEmail, setValidEmail] = useState(false);
    const [emailFocus, setEmailFocus] = useState(false);

    const [pNumber, setpNumber] = useState('');
    const [validpNumber, setValidpNumber] = useState(false);
    const [pNumberFocus, setpNumberFocus] = useState(false);

    /* const [role, setRole] = useState('');
    const [validRole, setValidRole] = useState(false);
    const [roleFocus, setRoleFocus] = useState(false); */

    const [nic, setNic] = useState('');
    const [validNic, setValidNic] = useState(false);
    const [nicFocus, setNicFocus] = useState(false);

    const [pwd, setPwd] = useState('');
    const [validPwd, setValidPwd] = useState(false);
    const [pwdFocus, setPwdFocus] = useState(false);

    const [matchPwd, setMatchPwd] = useState('');
    const [validMatch, setValidMatch] = useState(false);
    const [matchFocus, setMatchFocus] = useState(false);

    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
      const result = USER_REGEX.test(user);
      setValidUser(result);
    }, [user]);
  
    // Validation functions for other fields
    useEffect(() => {
      setValidName(NAME_REGEX.test(name));
    }, [name]);

    useEffect(() => {
      setValidEmail(EMAIL_REGEX.test(email));
    }, [email]);
  
    useEffect(() => {
      setValidpNumber(PHONE_REGEX.test(pNumber));
    }, [pNumber]);
  
   /*  useEffect(() => {
      setValidRole(role.trim() !== ''); // Custom validation for role
    }, [role]); */
  
    useEffect(() => {
      setValidNic(NIC_REGEX.test(nic) || NIC_REGEX_2.test(nic));
    }, [nic]);

   
  
    useEffect(() => {
      setValidPwd(PWD_REGEX.test(pwd));
    }, [pwd]);
  
    useEffect(() => {
      setValidMatch(pwd === matchPwd);
    }, [pwd, matchPwd]);

    useEffect(() => {
      setErrMsg('');
  }, [user, pwd, matchPwd])

  const handleSubmit = async (e) => {
    e.preventDefault();
    // if button enabled with JS hack
    const v1 = USER_REGEX.test(user);
    const v2 = PWD_REGEX.test(pwd);
    const v3 = NAME_REGEX.test(name);
    const v4 = EMAIL_REGEX.test(email);
    const v5 = PHONE_REGEX.test(pNumber);
    
    const v6 = (NIC_REGEX.test(nic) || NIC_REGEX_2.test(nic));
    if (!v1 || !v2 || !v3 || !v4 || !v5 || !v6) {
        setErrMsg("Invalid Entry");
        return;
    }
    try {
        const response = await axios.post(REGISTER_URL,
            JSON.stringify({ user, pwd }),
            {
                headers: { 'Content-Type': 'application/json' },
                withCredentials: true
            }
        );
        console.log(response?.data);
        console.log(response?.accessToken);
        console.log(JSON.stringify(response))
        setSuccess(true);
        //clear state and controlled inputs
        //need value attrib on inputs for this
        setUser('');
        setPwd('');
        setMatchPwd('');
    } catch (err) {
        if (!err?.response) {
            setErrMsg('No Server Response');
        } else if (err.response?.status === 409) {
            setErrMsg('Username Taken');
        } else {
            setErrMsg('Registration Failed')
        }
        errRef.current.focus();
    }
}

  return (
    <>
            {success ? (
                <section>
                    <h1>Success!</h1>
                    <p>
                        <Link to="./">Sign In</Link>
                    </p>
                </section>
            ) : (
    
    
    <section>

      <div
          className="register">
          
          <div className='leftreg'>
          <Header/>

          
        
        <div className='register-text'>
          
                  <div><span className='stroke-text'>CREATE A TRAINEE ACCOUNT</span>
                  </div>
                  
              </div>
              </div>
              <div className="style" style={{
            backgroundImage: `url(${backgroundImage})`
            
          }}
        ></div>
      <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
      <div className="leftf">
      <form className="lf" onSubmit={handleSubmit}>
                        <label htmlFor="username">
                            Username:
                            <FontAwesomeIcon icon={faCheck} className={validName ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validName || !user ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="text"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setUser(e.target.value)}
                            value={user}
                            required
                            aria-invalid={validName ? "false" : "true"}
                            aria-describedby="uidnote"
                            onFocus={() => setUserFocus(true)}
                            onBlur={() => setUserFocus(false)}
                        />
                        <p id="uidnote" className={userFocus && user && !validName ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            4 to 24 characters.<br />
                            Must begin with a letter.<br />
                            Letters, numbers, underscores, hyphens allowed.
                        </p>

                        <label htmlFor="email">
                            Email:
                            <FontAwesomeIcon icon={faCheck} className={validEmail ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validEmail || !email ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="text"
                            id="email"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setEmail(e.target.value)}
                            value={email}
                            required
                            aria-invalid={validEmail ? "false" : "true"}
                            aria-describedby="uemailnote"
                            onFocus={() => setEmailFocus(true)}
                            onBlur={() => setEmailFocus(false)}
                        />
                        <p id="uemailnote" className={emailFocus && email && !validEmail ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            Should be in the correct form of your email.
                        </p>

                        <label htmlFor="pwd">
                            Password:
                            <FontAwesomeIcon icon={faCheck} className={validPwd ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validPwd || !pwd ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="password"
                            id="pwd"
                            onChange={(e) => setPwd(e.target.value)}
                            value={pwd}
                            required
                            aria-invalid={validPwd ? "false" : "true"}
                            aria-describedby="pwdnote"
                            onFocus={() => setPwdFocus(true)}
                            onBlur={() => setPwdFocus(false)}
                        />
                        <p id="pwdnote" className={pwdFocus && !validPwd ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            8 to 24 characters.<br />
                            Must include uppercase and lowercase letters, a number and a special character.<br />
                            Allowed special characters: <span aria-label="exclamation mark">!</span> <span aria-label="at symbol">@</span> <span aria-label="hashtag">#</span> <span aria-label="dollar sign">$</span> <span aria-label="percent">%</span>
                        </p>

                        <label htmlFor="confirm_pwd">
                            Confirm Password:
                            <FontAwesomeIcon icon={faCheck} className={validMatch && matchPwd ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validMatch || !matchPwd ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="password"
                            id="confirm_pwd"
                            onChange={(e) => setMatchPwd(e.target.value)}
                            value={matchPwd}
                            required
                            aria-invalid={validMatch ? "false" : "true"}
                            aria-describedby="confirmnote"
                            onFocus={() => setMatchFocus(true)}
                            onBlur={() => setMatchFocus(false)}
                        />
                        <p id="confirmnote" className={matchFocus && !validMatch ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            Must match the first password input field.
                        </p>

                        {/* <label htmlFor="role">
                            Role:
                            <FontAwesomeIcon icon={faCheck} className={validRole ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validRole || !role ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="text"
                            id="role"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setRole(e.target.value)}
                            value={role}
                            required
                            aria-invalid={validRole ? "false" : "true"}
                            aria-describedby="urolenote"
                            onFocus={() => setRoleFocus(true)}
                            onBlur={() => setRoleFocus(false)}
                        /> */}
                        {/* <p id="urolenote" className={roleFocus && role && !validRole ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            Enter either "Trainer" or "Trainee".
                        </p> */}
                        <label>Insert a Profile Image:</label>
                        <div className="uphoto">
                            <input type="file" name="photo" id="customFile" accept=".jpg, .png" />
                        </div>
                        </form>
      </div>

      <div className="rightf">
        <form className="rf" onSubmit={handleSubmit}>
        <label htmlFor="name">
                            Name:
                            <FontAwesomeIcon icon={faCheck} className={valid_Name ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={valid_Name || !name ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="text"
                            id="name"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setName(e.target.value)}
                            value={name}
                            required
                            aria-invalid={valid_Name ? "false" : "true"}
                            aria-describedby="unamenote"
                            onFocus={() => setNameFocus(true)}
                            onBlur={() => setNameFocus(false)}
                        />
                        <p id="unamenote" className={nameFocus && name && !valid_Name ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            Enter your first name.<br />
                            Numbers are not accepted
                        </p>

                        <label htmlFor="pNumber">
                           Phone Number:
                            <FontAwesomeIcon icon={faCheck} className={validpNumber ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validpNumber || !pNumber ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="text"
                            id="pNumber"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setpNumber(e.target.value)}
                            value={pNumber}
                            required
                            aria-invalid={validpNumber ? "false" : "true"}
                            aria-describedby="upNumbernote"
                            onFocus={() => setpNumberFocus(true)}
                            onBlur={() => setpNumberFocus(false)}
                        />
                        <p id="upNumbernote" className={pNumberFocus && pNumber && !validpNumber ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            Only digits are accepted<br/>
                            length should be 10 digits
                        </p>

                        

                        <label htmlFor="nic">
                            NIC:
                            <FontAwesomeIcon icon={faCheck} className={validNic ? "valid" : "hide"} />
                            <FontAwesomeIcon icon={faTimes} className={validNic || !nic ? "hide" : "invalid"} />
                        </label>
                        <input
                            type="text"
                            id="nic"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setNic(e.target.value)}
                            value={nic}
                            required
                            aria-invalid={validNic ? "false" : "true"}
                            aria-describedby="unamenote"
                            onFocus={() => setNicFocus(true)}
                            onBlur={() => setNicFocus(false)}
                        />
                        <p id="unamenote" className={nicFocus && nic && !validNic ? "instructions" : "offscreen"}>
                            <FontAwesomeIcon icon={faInfoCircle} />
                            Old nic with 9 digits and v.<br/>
                            New nic with 12 digits only.
                        </p>

                        <button disabled={!valid_Name || !validPwd || !validMatch || !validEmail || !validName || !validpNumber  ? true : false}>Sign Up</button>
                        <p>
                        Already registered?<br /><br/>
                        <span className="line">
                            
                            <Link to="./login" className="no-underline">Sign In</Link>
                        </span>
                    </p>
        </form>
      </div>
      
      </div> 
    </section>
            )}
    </>
  )
}

export default Register;
