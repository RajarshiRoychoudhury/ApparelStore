import "./Navbar.css"
import {Link} from 'react-router-dom';
 {/* <div className="topnav"> */}
const NavBar = ({track,number,handleGetHome,handleGetCategory})=>{
return(
<div> 
  <h1>Welcome to Apparel Store</h1>
  <Link className= "hemu" onClick = {event=>null} to={"/viewcart"}>Cart({number})</Link>
  <Link name = "Skirt" className= "rover" onClick = {event=>{handleGetCategory(event)}} to={"/Skirt"}>Skirts</Link>
  <Link name = "Top" className= "rover" onClick = {event=>{handleGetCategory(event)}} to={"/Top"}>Tops</Link>
  <Link name = "Shoe" className= "rover" onClick = {event=>{handleGetCategory(event)}} to={"/Shoe"}>Shoe</Link>

  <Link name = "Pant" className= "rover" onClick = {event=>{handleGetCategory(event)}} to={"/Pant"}>Pant</Link>
  <Link name = "Shirt" className= "rover" onClick = {event=>{handleGetCategory(event)}} to={"/Shirt"}>Shirts</Link>
  <Link name = "Tshirt" className= "rover" onClick = {event=>{handleGetCategory(event)}} to={"/Tshirt"}>Tshirts</Link>
  <button onClick = {event=>handleGetHome(event)}>Go to Home</button>
</div>
)
}

export default NavBar;
