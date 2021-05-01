import Item from "../Item/Item"
import Navbar from "../Navbar/Navbar"
import "./Items.css"
const Items = ({items,track,number,setNumber,handleAddCart, handleGetHome,handleGetCategory,special_category,timesNewArrival,timesDiscounted,timesBased,setTimesNewArrival,setTimesBased,setTimesDiscounted})=>{
    if(special_category == "discounted"){
    return(
        <div className="row">
            <h1>Check out our discounted section</h1>
            {(items).map((item, i) => <div  className="column" key={i}>
                <Item 
                    item={item}  
                    number = {number} 
                    setNumber = {setNumber} 
                    handleAddCart = {handleAddCart}
                    special_category ={special_category}
                    timesNewArrival = {timesNewArrival}
                    timesBased = {timesBased}
                    timesDiscounted = {timesDiscounted}
                    seTimesNewArrival = {setTimesNewArrival}
                    setTimesBased = {setTimesBased}
                    setTimesDiscounted = {setTimesDiscounted}
                />
            </div>)}
        </div>
    )
    }
    else if(special_category == "newArrivals"){
        return(
            <div className="row">
            <h1>Check out our New Arrivals</h1>
            {(items).map((item, i) => <div  className="column" key={i}>
                <Item 
                    item={item}  
                    number = {number} 
                    setNumber = {setNumber} 
                    handleAddCart = {handleAddCart}
                    special_category ={special_category}
                    timesNewArrival = {timesNewArrival}
                    timesBased = {timesBased}
                    timesDiscounted = {timesDiscounted}
                    seTimesNewArrival = {setTimesNewArrival}
                    setTimesBased = {setTimesBased}
                    setTimesDiscounted = {setTimesDiscounted}
                />
            </div>)}
        </div>
        )
    }
    else{
        if(items.length > 0){
        return(
            <div className="row">
            <h1>Items just for you</h1>
            {(items).map((item, i) => <div  className="column" key={i}>
                <Item 
                    item={item}  
                    number = {number} 
                    setNumber = {setNumber} 
                    handleAddCart = {handleAddCart}
                    special_category ={special_category}
                    timesNewArrival = {timesNewArrival}
                    timesBased = {timesBased}
                    timesDiscounted = {timesDiscounted}
                    seTimesNewArrival = {setTimesNewArrival}
                    setTimesBased = {setTimesBased}
                    setTimesDiscounted = {setTimesDiscounted}
                />
            </div>
            )}
            </div>)
        }
        else{
         return(
            <div className="row">
            {(items).map((item, i) => <div  className="column" key={i}>
                <Item 
                    item={item}  
                    number = {number} 
                    setNumber = {setNumber} 
                    handleAddCart = {handleAddCart}
                    special_category ={special_category}
                    timesNewArrival = {timesNewArrival}
                    timesBased = {timesBased}
                    timesDiscounted = {timesDiscounted}
                    seTimesNewArrival = {setTimesNewArrival}
                    setTimesBased = {setTimesBased}
                    setTimesDiscounted = {setTimesDiscounted}
                />
            </div>
            )}
            </div>
        )
    }
}
}

export default Items;
