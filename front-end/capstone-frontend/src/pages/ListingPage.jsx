import { useEffect, useState} from "react";
import { useParams } from "react-router-dom";
import { Card } from "@/components/ui/card"

function ListingPage() {
    const [product, setProducts] = useState([]);
    const { id } = useParams();

    useEffect(() => {
        fetch(`http://localhost:8080/products/${id}`)
            .then(res => {
            console.log("Response status:", res.status);
            return res.json();
            })
            .then(data => {
            console.log("Fetched products:", data);
            setProducts(data);
            })
            .catch(err => console.error("Fetch error:", err));
        }, [id]);
    return (
        <Card className="text-center p-4 m-4 items-center border-2 cursor-pointer">
        <img
          className="w-32 h-42 mx-auto mb-4"
          src={"http://localhost:8080" + product.imgPath}
          alt={product.productName}
        />

        <div className="font-semibold text-lg">{product.productName}</div>

        <div className="text-sm text-gray-500 mb-2">{product.seriesName}</div>

        <div className="text-blue-600 font-bold">
          ${Number(product.price).toFixed(2)}
        </div>

        {product.productType === "PACK" && (
          <button
            className="mt-4 w-full bg-gray-200 text-black py-2 rounded-lg hover:bg-black hover:text-white"
          >
            Buy Pack
          </button>
        )}
      </Card>
        
    )
}
export default ListingPage;
