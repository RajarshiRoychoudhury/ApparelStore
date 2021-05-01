// private String id;
// private Integer price;
// private Integer stock;
// private String gender;
// private String category;
// private Date date;
// private Integer discount;
// private Boolean hasDiscount;
import { useState } from "react";
import "./Item.css"

const Item = ({item,setNumber,number,handleAddCart,special_category,timesNewArrival,timesDiscounted,timesBased,setTimesNewArrival,setTimesBased,setTimesDiscounted})=>{
    const price = item.price;
    const [now,setNow] = useState(0);
    var discounted_price = item.price;
    console.log(special_category);
    if(special_category === "discounted"){
        discounted_price = item.price - (item.price*item.discount/100);

        
        return(
            <div>
                <div className="myDIV">
                    <p>Name:{item.name}</p>
                    <p>Discounted price({item.discount}% off):{discounted_price}</p>
                           <p>Item Id: {item.id}</p>
                    <form>
                        <input 
                                type="number" 
                                min="1" max={Math.min(item.stock,5)} 
                                onChange = {event=>setNow(parseInt(event.target.value))}>
                        
                        </input>
                        <button name = {item.id} onClick = {event=>{
                            handleAddCart(event,now);
                            setTimesDiscounted((timesDiscounted+1));
                            setNow(0);
                            }}>Add to Cart</button>
                    </form>
                </div>

                <div className="hide">
                <p>Category: {item.category}</p>
                    <p>Item Price Original:{item.price}</p>
                </div>

            </div>
        )
    }
    else{
        return(
            <div>
                <div className="myDIV">
                    <p>Name:{item.name}</p>
                    <p>Price:{item.price}</p>
                           <p>Item Id: {item.id}</p>
                    <form>
                        <input 
                                type="number" 
                                min="1" max={Math.min(item.stock,5)} 
                                onChange = {event=>setNow(parseInt(event.target.value))}>
                        
                        </input>
                        <button name = {item.id} onClick = {event=>{
                            event.preventDefault();
                            if(special_category === "newArrivals"){
                                setTimesNewArrival((timesNewArrival+1));
                            }
                            handleAddCart(event,now);
                            setNumber(number+now);
                            setNow(0);
                            }}>Add to Cart</button>
                    </form>
                </div>

                <div className="hide">
                <p>Category: {item.category}</p>
                    <p>Recent arrival</p>
                </div>
                
            </div>
        
    )
}
}

export default Item;
