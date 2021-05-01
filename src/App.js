import { useEffect, useState } from 'react';
import axios from 'axios';
import Login from './Login/Login'
import SignUp from './SignUp/SignUp'
import { Switch, Route, useHistory} from 'react-router-dom';
import Navbar from "./Navbar/Navbar"
import Item from "./Item/Item"
import Items from "./Items/Items"
import Home from "./Home/Home"
import Cart from "./Cart/Cart"
import ItemsSearch from "./ItemsSearch/ItemsSearch";


const ENDPOINT = "https://localhost:3000";
const STATUS_OK = 200;
const STATUS_UNAUTHORISED = 401;
const STATUS_BAD = 400
const STATUS_FORBIDDEN = 403

function App() {
  const item = {
    "name":"abc",
    "price":1200,
    "id":"6080f46e5ed26f51ff461d1d",
    "hasDiscount":true,
    "discount":40,
    "stock":40
  }
  const item1 = {
    "name":"abc",
    "price":1200,
    "id":"6080f46e5ed26f51ff461d2d",
    "hasDiscount":true,
    "discount":40,
    "stock":40
  }
  var history = useHistory();
  const [userName,setUserName] = useState('');
  const [password,setPassword] = useState('');
  const [firstName,setFirstName] = useState('');
  const [lastName,setLastName] = useState('');
  const [gender,setGender] = useState('');
  const [email,setEmail] = useState('');
  const [loggedIn,setLogin] = useState(false);
  const [message,setMessage]  = useState('');
  const [items,setItems] = useState([]);
  const [cart,setCart] = useState([]);
  const [token,setToken] = useState('');
  const [number,setNumber] = useState(0);
  const [newArrivals,setNewArrivals] = useState([]);
  const [discounted,setDiscounted] = useState([]);
  const [based,setBased] = useState([]);
  const [timesNewArrival,setTimesNewArrival] = useState(0);
  const [timesDiscounted,setTimesDiscounted] = useState(0);
  const [timesBased,setTimesBased] = useState(0);
  
    useEffect(() => {
    console.log("new");
    // Update the document title using the browser API
    axios.get("https://localhost:8443/api/user/home").then(res=>{
    console.log(res);
          var arr = [];
           arr.push(...res.data.discounted);
           setDiscounted(arr);
           setItems(arr);
           arr = [];
           arr.push(...res.data.newArrivals);
           setNewArrivals(arr);
           
           arr = [];
           arr.push(...res.data.based);
           setBased(arr);
           history.push("/home");
    });   
      
  },[]);
  
  const handleLogin = (event)=>{
    event.preventDefault();
    console.log("Login clicked");
    const data = {
      "username":userName,
      "password":password
    };
    console.log(data);
    axios.post("https://localhost:8443/api/auth/signin",data)
    .then(res=>{ 
    console.log(res);
                const token_res = res.data.accessToken;
                localStorage.setItem("token",token_res);
                setToken(token_res);
                setLogin(true);
                history.push("/home");
    	  }
    	
    	).catch(err=>{
                if(err.response.status === STATUS_UNAUTHORISED){
                  setMessage("Invalid username/password");
                }
                else if(err.response.status === STATUS_FORBIDDEN){
                  setMessage("The client does not have access rights to the content.It is unauthorized");
                }
                else if(err.response.status === STATUS_BAD){
                  setMessage("Email Exists. Signin?")
                }
                history.push("/login");
    });
   
  };
  const handleSignUp = (event)=>{
    event.preventDefault();
    setMessage('');
    console.log("SignUp clicked");
    console.log(email);
    console.log(password);
    console.log(firstName);
    console.log(lastName);
    console.log(gender);
    const data = {
      "email":email,
      "password":password,
      "firstname":firstName,
      "lastname":lastName,
      "gender":gender
    };
    axios.post("https://localhost:8443/api/auth/signup",data).then(res=>{
      console.log(res);
        history.push("/login");
    }).catch(err=>{
            if(err.response.status === STATUS_UNAUTHORISED){
              setMessage("Invalid username/password");
            }
            else if(err.response.status === STATUS_FORBIDDEN){
              setMessage("The client does not have access rights to the content.It is unauthorized");
            }
            else if(err.response.status === STATUS_BAD){
              setMessage("Email Exists. Signin?")
            }
            history.push("/signup");
    })
  }

  const track = (event)=>{
    if(loggedIn){
        const data = {
          "clicked":event.target.getAttribute("name"),
        };
        const config = {
          headers:{
            Authorization:`Bearer ${token}` ,
              'Content-Type' : "application/json"
          }
        };
        axios.post("https://localhost:8443/api/user/track",data,config).then(res=>{
        console.log(res)})
        .catch(err=>{
          if(err.response.status === STATUS_UNAUTHORISED){
            setMessage("You need to signin username/password");
          }
          else if(err.response.status === STATUS_FORBIDDEN){
            setMessage("The client does not have access rights to the content.It is unauthorized");
          }
          else if(err.response.status === STATUS_BAD){
            setMessage("Email Exists. Signin?")
          }
          history.push("/login");
        });
    }
  };

  const handleLogout = (event)=>{
   //event.preventDefault();
   const config = {
    headers:{
       Authorization:`Bearer ${token}` ,
         'Content-Type' : "application/json"
    }
  };
   axios.get("https://localhost:8443/api/auth/logout",config).then(res=>{
     console.log(res);
     setMessage("Successful Logout")
     
    }).catch(err=>{
      setMessage("Something unexpected happended");
    })
    window.localStorage.clear();
    setLogin(false);
    //history.push("/login")
  }



  const handleAddCart = (event,now)=>{
    event.preventDefault();
    const item_id  = event.target.getAttribute("name");
    const data = {
      "id":item_id,
      "quantity":now
    }
    const config = {
      headers:{
         Authorization:`Bearer ${token}` ,
           'Content-Type' : "application/json"
      }
    }
    axios.post("https://localhost:8443/api/user/addcart",data,config).then(res=>{
    console.log(res);
    if(res.status === STATUS_OK){
    setNumber(number+now);
      var item = res.data;
      item.quantity = now;
      let updated_cart = cart.filter(cart_item=> cart_item.id === item_id);
      if(updated_cart.length>0){
        const updated_item = updated_cart[0];
        updated_item.quantity += now;
        updated_cart = cart.filter(cart_item=> cart_item.id !== item_id);
        setCart([...updated_cart,updated_item]);
        console.log("Cart is")
        console.log(cart);
      }
      else{
        console.log("Cart is")
        console.log(cart);
      	setCart([...cart,item]);
      }
      }
    })
    .    catch(err=>{
      if(err.response.status === STATUS_UNAUTHORISED){
        setMessage("You need to signin username/password");
      }
      else if(err.response.status === STATUS_FORBIDDEN){
        setMessage("The client does not have access rights to the content.It is unauthorized");
      }
      else if(err.response.status === STATUS_BAD){
        setMessage("Email Exists. Signin?")
      }
      else{
        setMessage("Sorry, the item no longer exists");
        let updated_cart = cart.filter(cart_item=> cart_item.id !== item_id);
        setCart(updated_cart);
      }
      history.push("/login");
    });
  }

  const handleGetHome = (event)=>{
    event.preventDefault();
    const config = {
    headers:{
       Authorization:`Bearer ${token}` ,
         'Content-Type' : "application/json"
    }
  };
        axios.get("https://localhost:8443/api/user/home",config).then(res=>{
    console.log(res);
          var arr = [];
           arr.push(...res.data.discounted);
           setDiscounted(arr);
           setItems(arr);
           arr = [];
           arr.push(...res.data.newArrivals);
           setNewArrivals(arr);
           
           arr = [];
           arr.push(...res.data.based);
           setBased(arr);
           history.push("/home");
    }).catch(err=>{
        setMessage("Something is wrong");
        history.push("/home");
    })
  }

  const handleRemoveItem = (event)=>{
      event.preventDefault();
      console.log(cart);
      const id = event.target.getAttribute("name");
      const cart_items = cart.filter(item=>item.id === id);
      const item = cart_items[0];
      console.log(id);
      const data = {
        "id":id,
        "quantity":item.quantity
      }
      const config = {
        headers:{
           Authorization:`Bearer ${token}` ,
             'Content-Type' : "application/json"
        }
      };
      axios.post("https://localhost:8443/api/user/removeItem",data,config)
      .then(res=>{
        if(res.status ===  STATUS_OK){
          setMessage("Succesfully deleted");
          setNumber(number-item.quantity);
          const updated_cart = cart.filter(item=>item.id !== id);
          setCart(updated_cart);
          history.push("/viewcart");
        }
      }).catch(err=>{
        setMessage("Something went wrong");
        history.push("/viewcart");
      })
  }


  const handleGetCategory = (event)=>{
    // event.preventDefault();
    const category = event.target.getAttribute("name");
    const data = {
      "category":category
    }
    const config = {
      headers:{
         Authorization:`Bearer ${token}` ,
           'Content-Type' : "application/json"
      }
    }
    axios.post("https://localhost:8443/api/user/getCategory",data,config)
    .then(res=>{
      var arr = [];
      arr.push(...res.data);
      setItems(arr);
    })
  }

 // <Route exact path="/">
  //  <Home track = {track} handleAddCart = {handleAddCart} setNumber = {setNumber} number = {number}  loggedIn = {loggedIn} message = {message} setUserName= { setUserName }
  // setPassword = {setPassword} handleLogin = {handleLogin} items ={items} />
  // </Route>
 return( 
  <Switch>
  
  <Route exact path="/login">
    <Login  handleGetHome = {handleGetHome} 
      handleAddCart = {handleAddCart} 
      setNumber = {setNumber} 
      number = {number} 
      loggedIn = {loggedIn} 
      message = {message} 
      setUserName= {setUserName} 
      setPassword = {setPassword} 
      handleLogin = {handleLogin} 
      handleRemoveItem = {handleRemoveItem}
      handleGetCategory = {handleGetCategory}
      newArrivals = {newArrivals}
      based = {based}
      discounted  = {discounted}
      timesNewArrival = {timesNewArrival}
      timesBased = {timesBased}
      timesDiscounted = {timesDiscounted}
      seTimesNewArrival = {setTimesNewArrival}
      setTimesBased = {setTimesBased}
      setTimesDiscounted = {setTimesDiscounted}
      userName = {userName}
      />


  </Route>
  <Route exact path="/signup" >
   <SignUp handleGetHome = {handleGetHome}
      handleAddCart = {handleAddCart}  
      setNumber = {setNumber} 
      number = {number} 
      loggedIn = {loggedIn} 
      message = {message} 
      handleSignUp= {handleSignUp}
      setEmail= {setEmail} 
      setUserName= {setUserName} 
      setPassword = {setPassword} 
      setFirstName= {setFirstName}
      setLastName = {setLastName} 
      setGender = {setGender}
      handleRemoveItem = {handleRemoveItem}
      handleGetCategory = {handleGetCategory}
      newArrivals = {newArrivals}
      based = {based}
      discounted  = {discounted}
      timesNewArrival = {timesNewArrival}
      timesBased = {timesBased}
      timesDiscounted = {timesDiscounted}
      seTimesNewArrival = {setTimesNewArrival}
      setTimesBased = {setTimesBased}
      setTimesDiscounted = {setTimesDiscounted}
      userName = {userName}
      />
  </Route>


  <Route exact path = "/home">
    <Home handleGetHome = {handleGetHome} 
          track = {track} 
          handleAddCart = {handleAddCart}  
          setNumber = {setNumber} 
          number = {number} 
          loggedIn = {loggedIn} 
          handleLogin = {handleLogin} 
          handleLogout = {handleLogout} 
          items={items} 
          handleRemoveItem = {handleRemoveItem}
          handleGetCategory = {handleGetCategory}
          newArrivals = {newArrivals}
          based = {based}
          discounted  = {discounted}
          timesNewArrival = {timesNewArrival}
          timesBased = {timesBased}
          timesDiscounted = {timesDiscounted}
          seTimesNewArrival = {setTimesNewArrival}
          setTimesBased = {setTimesBased}
          setTimesDiscounted = {setTimesDiscounted}
          userName = {userName}
          />
  </Route>


  <Route exact path = "/Skirt">
    <ItemsSearch handleGetHome = {handleGetHome} 
          handleAddCart = {handleAddCart}  
          setNumber = {setNumber} 
          number = {number}  
          loggedIn = {loggedIn} 
          handleLogin = {handleLogin} 
          handleLogout = {handleLogout} 
          items={items} 
          track={ track } 
          handleRemoveItem = {handleRemoveItem}
          handleGetCategory = {handleGetCategory}
          newArrivals = {newArrivals}
          based = {based}
          discounted  = {discounted}
          timesNewArrival = {timesNewArrival}
          timesBased = {timesBased}
          timesDiscounted = {timesDiscounted}
          seTimesNewArrival = {setTimesNewArrival}
          setTimesBased = {setTimesBased}
          setTimesDiscounted = {setTimesDiscounted}
          userName = {userName}
          />
  </Route>
  <Route exact path = "/Pant">
    <ItemsSearch
        handleGetHome = {handleGetHome}
        handleAddCart = {handleAddCart}  
        setNumber = {setNumber} 
        number = {number} 
        items={items} 
        handleRemoveItem = {handleRemoveItem}
        handleGetCategory = {handleGetCategory}
        newArrivals = {newArrivals}
        based = {based}
        discounted  = {discounted}
        timesNewArrival = {timesNewArrival}
        timesBased = {timesBased}
        timesDiscounted = {timesDiscounted}
        seTimesNewArrival = {setTimesNewArrival}
        setTimesBased = {setTimesBased}
        setTimesDiscounted = {setTimesDiscounted}
        userName = {userName}
        />
  </Route>


  <Route exact path = "/Tshirt">
    <ItemsSearch handleGetHome = {handleGetHome} 
          handleAddCart = {handleAddCart}  
          setNumber = {setNumber} 
          number = {number}  
          loggedIn = {loggedIn} 
          handleLogin = {handleLogin} 
          handleLogout = {handleLogout} 
          items={items} 
          track={ track } 
          handleRemoveItem = {handleRemoveItem}
          handleGetCategory = {handleGetCategory}
          newArrivals = {newArrivals}
          based = {based}
          discounted  = {discounted}
          timesNewArrival = {timesNewArrival}
          timesBased = {timesBased}
          timesDiscounted = {timesDiscounted}
          seTimesNewArrival = {setTimesNewArrival}
          setTimesBased = {setTimesBased}
          setTimesDiscounted = {setTimesDiscounted}
          userName = {userName}
          />
  </Route>



  <Route exact path = "/Shoe">
    <ItemsSearch  handleGetHome = {handleGetHome} 
            handleAddCart = {handleAddCart}  
            setNumber = {setNumber} 
            number = {number}  
            loggedIn = {loggedIn} 
            handleLogin = {handleLogin} 
            handleLogout = {handleLogout} 
            items={items} 
            track={ track } 
            handleRemoveItem = {handleRemoveItem}
            handleGetCategory = {handleGetCategory}
            newArrivals = {newArrivals}
            based = {based}
            discounted  = {discounted}
            timesNewArrival = {timesNewArrival}
            timesBased = {timesBased}
            timesDiscounted = {timesDiscounted}
            seTimesNewArrival = {setTimesNewArrival}
            setTimesBased = {setTimesBased}
            setTimesDiscounted = {setTimesDiscounted}
            userName = {userName}
            />
  </Route>
  <Route exact path = "/Shirt">
    <ItemsSearch
          handleGetHome = {handleGetHome} 
          handleAddCart = {handleAddCart}  
          setNumber = {setNumber} 
          number = {number}  
          loggedIn = {loggedIn} 
          handleLogin = {handleLogin} 
          handleLogout = {handleLogout} 
          items={items} track={ track } 
          handleRemoveItem = {handleRemoveItem}
          handleGetCategory = {handleGetCategory}
          newArrivals = {newArrivals}
          based = {based}
          discounted  = {discounted}
          timesNewArrival = {timesNewArrival}
          timesBased = {timesBased}
          timesDiscounted = {timesDiscounted}
          seTimesNewArrival = {setTimesNewArrival}
          setTimesBased = {setTimesBased}
          setTimesDiscounted = {setTimesDiscounted}
          userName = {userName}
          />
  </Route>


  <Route exact path = "/Top">
    <ItemsSearch
          handleGetHome = {handleGetHome} 
          handleAddCart = {handleAddCart}  
          setNumber = {setNumber} 
          number = {number}  
          loggedIn = {loggedIn} 
          handleLogin = {handleLogin} 
          handleLogout = {handleLogout} 
          items={items} 
          track={ track }
          handleRemoveItem = {handleRemoveItem}
          handleGetCategory = {handleGetCategory}
          newArrivals = {newArrivals}
          based = {based}
          discounted  = {discounted}
          timesNewArrival = {timesNewArrival}
          timesBased = {timesBased}
          timesDiscounted = {timesDiscounted}
          seTimesNewArrival = {setTimesNewArrival}
          setTimesBased = {setTimesBased}
          setTimesDiscounted = {setTimesDiscounted}
          userName = {userName}
          />
  </Route>


  <Route exact path = "/cart">
    <Items 
          handleGetHome = {handleGetHome} 
          handleAddCart = {handleAddCart}  
          setNumber = {setNumber} 
          number = {number}  
          loggedIn = {loggedIn} 
          handleLogin = {handleLogin} 
          handleLogout = {handleLogout} 
          items={items} 
          track={ track }  
          cart = {cart}
          handleRemoveItem = {handleRemoveItem}
          handleGetCategory = {handleGetCategory}
          newArrivals = {newArrivals}
          based = {based}
          discounted  = {discounted}
          timesNewArrival = {timesNewArrival}
          timesBased = {timesBased}
          timesDiscounted = {timesDiscounted}
          seTimesNewArrival = {setTimesNewArrival}
          setTimesBased = {setTimesBased}
          setTimesDiscounted = {setTimesDiscounted}
          userName = {userName}
           />
  </Route>

  <Route exact path = "/viewcart">
    <Cart 
          handleGetHome = {handleGetHome} 
          handleAddCart = {handleAddCart}  
          setNumber = {setNumber} 
          number = {number}  
          loggedIn = {loggedIn} 
          handleLogin = {handleLogin} 
          handleLogout = {handleLogout} 
          items={items} 
          track={ track }  
          cart = {cart}
          handleRemoveItem = {handleRemoveItem}
          handleGetCategory = {handleGetCategory}
          newArrivals = {newArrivals}
          based = {based}
          discounted  = {discounted}
          timesNewArrival = {timesNewArrival}
          timesBased = {timesBased}
          timesDiscounted = {timesDiscounted}
          seTimesNewArrival = {setTimesNewArrival}
          setTimesBased = {setTimesBased}
          setTimesDiscounted = {setTimesDiscounted}
          userName = {userName}
           />
  </Route>
</Switch>
 )

}

export default App;
