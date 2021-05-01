
import {Link} from 'react-router-dom';
import './Login.css'



const Login = ({message,setUserName,setPassword,handleLogin})=>{


return(
	
    <div className="container">
    		<h1>{message}</h1>
                <form>
                <label for="username"><b>Username</b></label>
                    <input type="text" 
                        name = "username"
                        onChange={(event)=>setUserName(event.target.value)}>
                    </input>
                <label for="password"><b>Password</b></label>
                    <input type = "password"
                        name="password" 
                        onChange={(event)=>setPassword(event.target.value)}>
                    </input>

                    <button onClick = {(event)=>handleLogin(event)}>login</button>
                    <br></br>
                    <div className="topnav_login">
                    <Link  className = "rover" onClick = {(event)=>null} to={`/signup`}>Not registered ? SignUp</Link> 
                    </div>
                    </form>
                   
      </div>
)
}

export default Login;
