import "./SignUp.css"
import {Link} from 'react-router-dom';

const SignUp = ({message,handleSignUp,setEmail,setUserName,setPassword,setFirstName,setLastName,setGender})=>{


    return(
        <div className="container">
        <h1>{message}</h1>
        <form>
                    <label for="email"><b>Email</b></label>
                        <input type="text" 
                            name = "email"
                            onChange={(event)=>{
                                setUserName(event.target.value);
                                setEmail(event.target.value);
                                }}>
                        </input>
                    <label for="password"><b>Password</b></label>
                        <input type = "password"
                            name="password" 
                            onChange={(event)=>setPassword(event.target.value)}>
                        </input>
                        <label for="fistname"><b>FirstName</b></label>
                        <input type="text" 
                            name = "firstname"
                            onChange={(event)=>setFirstName(event.target.value)}>
                        </input>
                    <label for="lastName"><b>LastName</b></label>
                        <input type = "text"
                            name="lastname" 
                            onChange={(event)=>setLastName(event.target.value)}>
                        </input>
    
                        <input type="radio" id="male" name="gender" value="M" onClick = {event=>setGender(event.target.value)}></input>
                        <label for="M">Male</label><br></br>
                        <input type="radio" id="female" name="gender" value="F" onClick = {event=>setGender(event.target.value)}></input>
                        <label for="F">Female</label><br></br>  
                        <button onClick = {(event)=>handleSignUp(event)}>SignUp</button>
                        <div className="topnav_signup">
                        <Link className = "rover_signup" onClick = {(event)=>null} to={`/login`}>Already Registered ? Login</Link> 
                        </div>
            </form>
                       
          </div>
    )
    }

export default SignUp;
