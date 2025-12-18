import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { getCookie } from "../lib/cookies";

function Checkout() {
  const [products, setProducts] = useState([]);
  const [quantities, setQuantities] = useState({});
  const [total, setTotal] = useState(0);
  const navigate = useNavigate();
  const orderedItems = Object.entries(localStorage)
    .filter(([key, value]) => Number(value) > 0)
    .map(([key, value]) => ({
        productId: key,
        quantity: Number(value)
    }));

   const [formData, setFormData] = useState({
    shippingAddress: "",
    email: getCookie("user") || "",   // initialize from cookie
    orderedItems: orderedItems
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  };

const handleSubmit = async (e) => {
  e.preventDefault();

  console.log(formData)
  try {
    const response = await fetch("http://localhost:8080/order/submit", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(formData) // send your object as JSON
    });

    if (!response.ok) {
      throw new Error("Failed to submit order");
    }

    const data = await response.json();
    console.log("Order submitted successfully:", data);
    localStorage.clear();
    navigate("/thankyou", {state: {orderId: data}});
  } catch (error) {
    alert("Error submitting order, please check cart:", error);
    navigate("/cart");
  }
};



  const calculateTotal = () => {
    const sum = products.reduce((acc, product) => {
      const qty = quantities[product.productID] || 0;
      return acc + product.price * qty;
    }, 0);
    setTotal(sum);
  };

  useEffect(() => {
    calculateTotal();
  }, [products, quantities]);

  useEffect(() => {
    const allItems = {};
    for (let i = 0; i < localStorage.length; i++) {
      const key = localStorage.key(i);
      allItems[key] = parseInt(localStorage.getItem(key)) || 0;
    }
    setQuantities(allItems);
  }, []);

  useEffect(() => {
    const allItems = {};
    for (let i = 0; i < localStorage.length; i++) {
      const key = localStorage.key(i);
      allItems[key] = localStorage.getItem(key);
    }

    const keys = Object.keys(allItems);

    const fetchProducts = async () => {
      try {
        const responses = await Promise.all(
          keys.map((id) =>
            fetch(`http://localhost:8080/products/${id}`).then((res) =>
              res.json()
            )
          )
        );
        setProducts(responses);
      } catch (err) {
        console.log("Error fetching cart items: ", err);
      }
    };
    fetchProducts();
  }, []);

  return (
    <div className="flex flex-col">
      <Link to="/cart">
        <h2 className="px-20 py-5"> &larr; Back</h2>
      </Link>
      <div className="flex justify-center">
        <div className="p-10">
          <h1>Items Overview</h1>
          <div className="p-10">
            {products.map(
              (product) =>
                localStorage.getItem(product.productID) > 0 && (
                  <div
                    key={product.productID}
                    className="flex items-center justify-between gap-6 py-4 border-b border-gray-300"
                  >
                    <div className="flex items-center gap-6 min-w-0">
                      <img
                        src={"http://localhost:8080" + product.imgPath}
                        alt={product.productName}
                        className="w-24 h-24 object-contain flex-shrink-0 rounded"
                      />
                      <p className="font-medium truncate min-w-0">{product.productName}</p>
                    </div>

                    <div className="flex items-center gap-10 min-w-[200px] justify-end">
                      <div className="flex items-center gap-2 whitespace-nowrap">
                        <span className="font-semibold">Quantity:</span>
                        <span>{localStorage.getItem(product.productID)}</span>
                      </div>
                      <p className="font-semibold">${product.price.toFixed(2)}</p>
                    </div>
                  </div>
                )
              )}

            <div className="flex justify-end mt-6">
              <p className="text-xl font-bold">Total: ${total.toFixed(2)}</p>
            </div>
          </div>
        </div>

        <div className="p-10 border-l-2">
          <h1>Customer Details</h1>
          <p className="pt-5">Fill in your payment details and complete the order.</p>
          <form action="post" onSubmit={handleSubmit} className="pt-10">
            <p className="font-bold">Shipping Address: </p>
            <input type="text" name="shippingAddress" className="bg-gray-300 border-black h-10 border-2 rounded w-64" onChange={handleChange} required />
            <p className="font-bold">Email: </p>
            <p className="bg-gray-300 border-black h-10 border-2 rounded w-64 leading-10">{formData.email}</p>
            <p className="font-bold">Payment method:</p>
            <select name="payment" id="payment" className="block">
                <option value="cod">Cash On Delivery</option>
            </select>
            <button type="submit" className="bg-gray-300 hover:bg-orange-500 text-lg font-bold rounded p-3 mt-4">
                Submit Order
            </button>

          </form>
        </div>
      </div>
    </div>
  );
}

export default Checkout;
