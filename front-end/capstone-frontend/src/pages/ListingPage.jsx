import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { Card } from "@/components/ui/card";

function ListingPage() {
  const { id } = useParams();

  const [product, setProduct] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [inCart, setInCart] = useState(0);
  const [added, setAdded] = useState(false);
  const [loading, setLoading] = useState(true);

  // 📦 Fetch product
  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const res = await fetch(`http://localhost:8080/products/${id}`);
        if (!res.ok) throw new Error("Failed to fetch product");

        const data = await res.json();
        setProduct(data);

        const stored = localStorage.getItem(data.productID);
        setInCart(stored ? parseInt(stored, 10) : 0);

      } catch (err) {
        console.error("Fetch error:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [id]);

  // ⏳ Prevent render before data exists
  if (loading) {
    return <p className="text-center mt-8">Loading product...</p>;
  }

  if (!product) {
    return <p className="text-center mt-8">Product not found.</p>;
  }

  // 🔢 Quantity handlers
  const addQty = () => {
    if (quantity < product.stock - inCart) {
      setQuantity(quantity + 1);
    }
  };

  const minusQty = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  const addToCart = () => {
    const newQty = inCart + quantity;
    localStorage.setItem(product.productID, newQty);
    setInCart(newQty);
    setQuantity(1);
    setAdded(true);
  };


  return (
    <div className="flex justify-center">
      <Card className="w-[80%] h-96 flex gap-4 items-center p-6 m-8 border-2">

        {product.imgPath && (
          <img
            className="h-full mx-auto m-4"
            src={`http://localhost:8080${product.imgPath}`}
            alt={product.productName}
          />
        )}

        <div className="w-1/2 pr-8 flex flex-col">
          <h1 className="font-semibold text-5xl mb-2">
            {product.productName}
          </h1>

          <p className="text-sm text-gray-500">
            {product.seriesName}
          </p>

          <Link to={`/user/${product.userEmail}`}>
            <p className="mt-4">Listed by: {product.userEmail.split("@")[0]}</p>
          </Link>

          <p className="text-blue-600 font-bold text-3xl my-4">
            ${Number(product.price).toFixed(2)}
          </p>

          {product.productType !== "CARD" && (
            <div className="flex gap-4 items-center">
              <p>Quantity:</p>
              <button onClick={minusQty} className="border px-2">-</button>
              <p>{quantity}</p>
              <button onClick={addQty} className="border px-2">+</button>
            </div>
          )}

          {product.stock - inCart > 0 ? (
            <button
              onClick={addToCart}
              className="my-2 w-32 bg-gray-200 py-2 rounded hover:bg-black hover:text-white"
            >
              Add to cart
            </button>
          ) : (
            <button
              disabled
              className="my-2 w-32 bg-gray-300 py-2 rounded"
            >
              Unavailable
            </button>
          )}

          {added && <p className="text-green-600 mt-2">Added to cart.</p>}
          <p>In cart: {inCart}</p>
        </div>
      </Card>
    </div>
  );
}

export default ListingPage;
