import ItemSearch from "../ItemSearch/ItemSearch"
import Navbar from "../Navbar/Navbar"
import "./ItemsSearch.css"
const ItemsSearch = ({items,track,number,setNumber,handleAddCart, handleGetHome,handleGetCategory,special_category,timesNewArrival,timesDiscounted,timesBased,setTimesNewArrival,setTimesBased,setTimesDiscounted})=>{
	console.log(items);
    return(
        <div className="row">
            <Navbar track = {track} number = {number} handleGetHome = {handleGetHome} handleGetCategory = {handleGetCategory}/>
            {(items).map((item, i) => <div  className="column" key={i}>
                <ItemSearch 
                    item={item}  
                    number = {number} 
                    setNumber = {setNumber} 
                    handleAddCart = {handleAddCart}
                    special_category ={special_category}
                    timesNewArrival = {timesNewArrival}
                    timesBased = {timesBased}
                    timesDiscounted = {timesDiscounted}
                    setTimesNewArrival = {setTimesNewArrival}
                    setTimesBased = {setTimesBased}
                    setTimesDiscounted = {setTimesDiscounted}
                />
            </div>)}
        </div>
    )
    };
    

export default ItemsSearch;
