import { useEffect, useState } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import { getCookie } from "../lib/cookies";

function ThankYou() {
  const location = useLocation();
  const [order, setOrder] = useState({});
  const {orderId} = location.state || {};

  useEffect(()=> {
    const fetchOrder = async () => {
      try {
        const response = await fetch(`http://localhost:8080/order/details?orderId=${orderId}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        setOrder(data);
      } catch (err) {
        console.error("Error fetching order:", err);
      }
    };

    if (orderId) {
      fetchOrder();
    }
  }, [orderId]);
  
  useEffect(()=> {
    console.log(order.orderedItems);
  },[order])
  
  return (
    <div className="flex flex-col items-center p-20">
        <h1> Thank you for your purchase.</h1>
        <h2>Your order ID is: {orderId}</h2>

        <h2 className="pt-10">Item Overview</h2>

        <div>
        {order.orderedItems?.map((item) => (
          <div className="flex justify-around item-center border rounded mb-4 p-2 pr-8">
            <img src={`http://localhost:8080${item.product.imgPath}`} alt="item image" className="w-48 h-48" />
            <div className="flex flex-col justify-between">
            <p className="font-bold">{item.product.productName}</p>
            <p>Quantity: {item.quantity}</p>
            <p>Price: ${item.product.price}</p>
            </div>

          </div>
            ))}

        </div>

    </div>
  );
}

export default ThankYou;
