import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { Card } from "@/components/ui/card";

function ListingPage() {
    const [product, setProducts] = useState(null);
    const { id } = useParams();

    useEffect(() => {
        fetch(`http://localhost:8080/products/${id}`)
            .then(res => {
            console.log("Response status:", res.status);
            return res.json();
            })
            .then(data => {
            console.log("Fetched products:", data);
            setProduct(data);
            })
            .catch(err => console.error("Fetch error:", err));
        }, [id]);

        if (!product) {
            return <div className="text-center mt-10">Loading...</div>;
        }

    return (
        <div className="flex justify-center">
            <Card className="w-[80%] h-96 flex gap-4 items-center p-6 m-8 border-2">
            <img
            className="h-full mx-auto m-4"
            src={"http://localhost:8080" + product.imgPath}
            alt={product.productName}
            />
            <div className="w-1/2 ml-0 pr-8 flex flex-col justify-left">
                <div>
                    <div className="font-semibold text-5xl mt-4">{product.productName}</div>
                </div>

                <div className="text-sm text-gray-500 my-2">{product.seriesName}</div>
                    <Link to={`/user/${product.userEmail}`}>
                        <p>Listed by: {product.userEmail.split("@")[0]}</p>
                    </Link>

                <p className="text-blue-600 font-bold text-3xl">${Number(product.price).toFixed(2)}</p>


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
            quantity !== 0 &&
          <button
            onClick={addToCart}
            className="my-2 w-24 bg-gray-200 text-black py-2 rounded-lg hover:bg-black hover:text-white"
          >
            Add to cart
          </button>
        }

        {
            quantity ===0 &&

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
