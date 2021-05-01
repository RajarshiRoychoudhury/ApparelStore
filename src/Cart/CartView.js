const CartView = ({cart_item,handleRemoveItem})=>{
    var discounted_price = cart_item.price;
    if(cart_item.hasDiscount){
        discounted_price = cart_item.price - (cart_item.price*cart_item.discount/100);
    }
    console.log(cart_item.id);
    return(
        <div>
            <p>Item name: {cart_item.name}</p>
            <p>Item Price:{discounted_price}</p>
            <p>Quantity: {cart_item.quantity}</p>
            <p>Total Price: {cart_item.quantity*discounted_price}</p>
            <p>Category: {cart_item.category}</p>
             <p>Item Id: {cart_item.id}</p>
            <button name = {cart_item.id} onClick = {event=>handleRemoveItem(event)}>Remove item</button>
        </div>
    )
}
export default CartView;
