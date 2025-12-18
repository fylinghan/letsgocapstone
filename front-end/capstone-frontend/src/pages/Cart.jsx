import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Card } from "@/components/ui/card";


function Cart() {
  const [products, setProducts] = useState([]);
  const [quantities, setQuantities] = useState({});
  const [total, setTotal] = useState(0);

  const calculateTotal = () => {
    const sum = products.reduce((acc, product) => {
      const qty = quantities[product.productID] || 0;
      return acc + product.price * qty;
    }, 0);
    setTotal(sum);
  };

  const addQty = (maxStock, productID) => {
    if (parseInt(localStorage.getItem(productID)) < maxStock) {
      const newQty = parseInt(localStorage.getItem(productID)) + 1;
      localStorage.setItem(productID, newQty);
      setQuantities((prev) => ({ ...prev, [productID]: newQty }));
    }
  };

  const minusQty = (productID) => {
    if (parseInt(localStorage.getItem(productID)) > 0) {
      const newQty = parseInt(localStorage.getItem(productID)) - 1;
      localStorage.setItem(productID, newQty);
      setQuantities((prev) => ({ ...prev, [productID]: newQty }));
    }
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
    <div className="p-20">
      <h1>Your Cart</h1>
      <Card className="p-6 border-2">
        {products.map((product) => (
          <div
            key={product.productID}
            className="flex items-center justify-between py-6 border-b-2"
          >
            <div className="flex items-center gap-6">
              <img
                src={"http://localhost:8080" + product.imgPath}
                alt="cart item picture"
                className="max-h-40 max-w-20"
              />
              <p className="font-bold max-w-40">{product.productName}</p>
            </div>
            <div className="flex gap-10 pr-4">
              <div className="flex gap-2">
                <p>Qty: </p>
                <button
                  onClick={() => minusQty(product.productID)}
                  className="border border-gray-300 rounded px-1"
                >
                  -
                </button>
                <p className="w-5 text-center">{localStorage.getItem(product.productID)}</p>
                <button
                  onClick={() => addQty(product.stock, product.productID)}
                  className="border border-gray-300 rounded px-1"
                >
                  +
                </button>
              </div>
              <p className="text-center w-10">${product.price}</p>
            </div>
            {/* <li key={product.id}>{product.name}</li> */}
          </div>
        ))}
        <div className="flex flex-col items-end mt-6">
          <div>
            <p className="text-xl font-bold">Total: ${total.toFixed(2)}</p>
          </div>
          <div className="p-2">
            <Link to="/checkout">
              <button className="bg-gray-300 border rounded font-bold text-xl p-3 hover:bg-orange-500 hover:text-white">
                Checkout
              </button>
            </Link>
          </div>
        </div>
      </Card>
    </div>
  );
}

export default Cart;
