// homepage view: topbar, view cart, buy these items,
import Navbar from "../Navbar/Navbar"
import Items from "../Items/Items" 
import {Link} from "react-router-dom";
const Home = ({items,track,userName,handleLogout,handleLogin,loggedIn,number,setNumber,handleAddCart,handleGetHome,handleGetCategory,based,discounted,newArrivals,firstName,timesNewArrival,timesDiscounted,timesBased,setTimesNewArrival,setTimesBased,setTimesDiscounted})=>{
	console.log("Inside home");
    if(loggedIn){
        if(timesDiscounted >= timesNewArrival){
            return(
                <div>
                    <h1>Welcome to Apparel store, {userName} </h1>
                    <Link  onClick = {event=>handleLogout(event)} to = {"/login"}>Logout</Link>
                    <Navbar track = {track} number = {number} handleGetHome = {handleGetHome} handleGetCategory = {handleGetCategory}/>
                    <Items 
                        items={based}  
                        track = {track} 
                        number = {number} 
                        setNumber = {setNumber} 
                        handleAddCart={handleAddCart} 
                        handleGetHome = {handleGetHome}
                        handleGetCategory = {handleGetCategory}
                        special_category = "based"
                        timesNewArrival = {timesNewArrival}
                        timesBased = {timesBased}
                        timesDiscounted = {timesDiscounted}
                        setTimesNewArrival = {setTimesNewArrival}
                        setTimesBased = {setTimesBased}
                        setTimesDiscounted = {setTimesDiscounted}
                    />   


                    <Items 
                        items={discounted}  
                        track = {track} 
                        number = {number} 
                        setNumber = {setNumber} 
                        handleAddCart={handleAddCart} 
                        handleGetHome = {handleGetHome}
                        handleGetCategory = {handleGetCategory}
                        special_category = "discounted"
                        timesNewArrival = {timesNewArrival}
                        timesBased = {timesBased}
                        timesDiscounted = {timesDiscounted}
                        setTimesNewArrival = {setTimesNewArrival}
                        setTimesBased = {setTimesBased}
                        setTimesDiscounted = {setTimesDiscounted}
                    />         
                    <Items 
                        items={newArrivals}  
                        track = {track} 
                        number = {number} 
                        setNumber = {setNumber} 
                        handleAddCart={handleAddCart} 
                        handleGetHome = {handleGetHome}
                        handleGetCategory = {handleGetCategory}
                        special_category = "newArrivals"
                        timesNewArrival = {timesNewArrival}
                        timesBased = {timesBased}
                        timesDiscounted = {timesDiscounted}
                        setTimesNewArrival = {setTimesNewArrival}
                        setTimesBased = {setTimesBased}
                        setTimesDiscounted = {setTimesDiscounted}
                    />   
                </div>
            )
        }
        else{
            return(
                <div>
                    <h1>Welcome to Apparel store,{userName}</h1>
                    <Link  onClick = {event=>handleLogout(event)} to = {"/login"}>Logout</Link>
                    <Navbar track = {track} number = {number} handleGetHome = {handleGetHome} handleGetCategory = {handleGetCategory}/>
                    <Items 
                        items={based}  
                        track = {track} 
                        number = {number} 
                        setNumber = {setNumber} 
                        handleAddCart={handleAddCart} 
                        handleGetHome = {handleGetHome}
                        handleGetCategory = {handleGetCategory}
                        special_category = "based"
                        timesNewArrival = {timesNewArrival}
                        timesBased = {timesBased}
                        timesDiscounted = {timesDiscounted}
                        setTimesNewArrival = {setTimesNewArrival}
                        setTimesBased = {setTimesBased}
                        setTimesDiscounted = {setTimesDiscounted}
                    />   
                    <Items 
                        items={newArrivals}  
                        track = {track} 
                        number = {number} 
                        setNumber = {setNumber} 
                        handleAddCart={handleAddCart} 
                        handleGetHome = {handleGetHome}
                        handleGetCategory = {handleGetCategory}
                        special_category = "newArrivals"
                        timesNewArrival = {timesNewArrival}
                        timesBased = {timesBased}
                        timesDiscounted = {timesDiscounted}
                        setTimesNewArrival = {setTimesNewArrival}
                        setTimesBased = {setTimesBased}
                        setTimesDiscounted = {setTimesDiscounted}
                    />   

                    <Items 
                        items={discounted}  
                        track = {track} 
                        number = {number} 
                        setNumber = {setNumber} 
                        handleAddCart={handleAddCart} 
                        handleGetHome = {handleGetHome}
                        handleGetCategory = {handleGetCategory}
                        special_category = "discounted"
                        timesNewArrival = {timesNewArrival}
                        timesBased = {timesBased}
                        timesDiscounted = {timesDiscounted}
                        setTimesNewArrival = {setTimesNewArrival}
                        setTimesBased = {setTimesBased}
                        setTimesDiscounted = {setTimesDiscounted}
                    />   
                </div>      
            )
        }
    }
    else{
            return(
                <div>
                    <h1>Welcome to Apparel store</h1>
                    <Link  onClick = {event=>null} to = {"/login"}>Login</Link>
                    <Navbar track = {track} number = {number} handleGetHome = {handleGetHome} handleGetCategory = {handleGetCategory}/>

                    <Items 
                        items={discounted}  
                        track = {track} 
                        number = {number} 
                        setNumber = {setNumber} 
                        handleAddCart={handleAddCart} 
                        handleGetHome = {handleGetHome}
                        handleGetCategory = {handleGetCategory}
                        special_category = "discounted"
                        timesNewArrival = {timesNewArrival}
                        timesBased = {timesBased}
                        timesDiscounted = {timesDiscounted}
                        setTimesNewArrival = {setTimesNewArrival}
                        setTimesBased = {setTimesBased}
                        setTimesDiscounted = {setTimesDiscounted}
                    />   
                    <Items 
                        items={newArrivals}  
                        track = {track} 
                        number = {number} 
                        setNumber = {setNumber} 
                        handleAddCart={handleAddCart} 
                        handleGetHome = {handleGetHome}
                        handleGetCategory = {handleGetCategory}
                        special_category = "newArrivals"
                        timesNewArrival = {timesNewArrival}
                        timesBased = {timesBased}
                        timesDiscounted = {timesDiscounted}
                        setTimesNewArrival = {setTimesNewArrival}
                        setTimesBased = {setTimesBased}
                        setTimesDiscounted = {setTimesDiscounted}
                    />   

                </div>      
            )
    }
}

export default Home;
