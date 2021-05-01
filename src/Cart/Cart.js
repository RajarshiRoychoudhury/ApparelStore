import CartView from "./CartView";
import Navbar from "../Navbar/Navbar"
const Cart = ({cart,track,number,setNumber,handleAddCart,handleGetHome,handleRemoveItem,handleGetCategory})=>{
        return(
            <div className="row">
                <Navbar track = {track} number = {number} handleGetHome = {handleGetHome} handleGetCategory = {handleGetCategory}/>
                {(cart).map((item, i) =>
                 <div  className="column" key={i}>
                    < CartView 
                        cart_item={item}  
                        number = {number} 
                        setNumber = {setNumber} 
                        handleAddCart = {handleAddCart}
                        handleRemoveItem = {handleRemoveItem}
                        />
                </div>
                )}
            </div>
        )
    
}
export default Cart;
