import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Card } from "@/components/ui/card";

function ListingPage() {
  const [product, setProduct] = useState([]);
  const { id } = useParams();
  const [quantity, setQuantity] = useState(1);
  const [added, setAdded] = useState(false);
  const [inCart, setInCart] = useState(
    localStorage.getItem(product.productID) !== null
      ? localStorage.getItem(product.productID)
      : 0
  );

  const addQty = () => {
    if (quantity < product.stock-inCart) {
      setQuantity(quantity + 1);
    }
  };

  const minusQty = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  const addToCart = () => {
    if (localStorage.getItem(product.productID) !== null)
      localStorage.setItem(
        product.productID,
        parseInt(localStorage.getItem(product.productID)) + parseInt(quantity)
      );
    else localStorage.setItem(product.productID, quantity);
    setAdded(true);
    setInCart(localStorage.getItem(product.productID));
    setQuantity(0);
  };

  useEffect(() => {
    if (product?.productID) {
      const stored = localStorage.getItem(product.productID);
      setInCart(stored ? parseInt(stored) : 0);
    }

    if (inCart >= product.stock)
        setQuantity(0);
  }, [product]);

  useEffect(() => {
    fetch(`http://localhost:8080/products/${id}`)
      .then((res) => {
        console.log("Response status:", res.status);
        return res.json();
      })
      .then((data) => {
        console.log("Fetched products:", data);
        setProduct(data);
      })
      .catch((err) => console.error("Fetch error:", err));
  }, [id]);
  return (
    <div className="flex justify-center">
      <Card className="w-[80%] h-96 flex gap-4 items-center p-6 m-8 border-2">
        <img
          className="h-full mx-auto m-4"
          src={"http://localhost:8080" + product.imgPath}
          alt={product.productName}
        />
        <div className="w-1/2 ml-0 pr-8 flex flex-col justify-left">
          <div className="font-semibold text-5xl mt-4">
            {product.productName}
          </div>

          <div className="text-sm text-gray-500 my-2">{product.seriesName}</div>

          <p className="text-blue-600 font-bold text-3xl">
            ${Number(product.price).toFixed(2)}
          </p>

          <p className="mt-4 mb-2">
            {" "}
            Umbreon EX from Eevee Grove set, near mint with no observable
            damage. Great for binder collections!
          </p>

          {product.productType !== "CARD" && (
            <div className="flex gap-4">
              <p>Quantity: </p>
              <button
                onClick={minusQty}
                className="border border-gray-300 rounded px-1"
              >
                -
              </button>
              <p>{quantity}</p>
              <button
                onClick={addQty}
                className="border border-gray-300 rounded px-1"
              >
                +
              </button>
            </div>
          )}
        {
            product.stock - inCart !== 0 &&
          <button
            onClick={addToCart}
            className="my-2 w-24 bg-gray-200 text-black py-2 rounded-lg hover:bg-black hover:text-white"
          >
            Add to cart
          </button>
        }

        {
            product.stock - inCart === 0 &&

            <button disabled
            className="my-2 w-24 bg-gray-200 text-black py-2 rounded-lg "
          >
            Unavailable
          </button>
        }

          {added && <p className="text-green-600 mt-2">Added to cart.</p>}
          <p>In cart: {inCart}</p>
        </div>
      </Card>
    </div>
  );
}
export default ListingPage;
