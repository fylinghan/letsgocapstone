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
        <div className="flex justify-center">
            <Card className="w-[80%] h-96 flex gap-4 items-center p-6 m-8 border-2">
            <img
            className="h-full mx-auto m-4"
            src={"http://localhost:8080" + product.imgPath}
            alt={product.productName}
            />
            <div className="w-1/2 ml-0 pr-8 flex flex-col justify-left">
                <div className="font-semibold text-5xl mt-4">{product.productName}</div>

                <div className="text-sm text-gray-500 my-2">{product.seriesName}</div>

                <p className="text-blue-600 font-bold text-3xl">${Number(product.price).toFixed(2)}</p>

                <p className="mt-4 mb-2"> Umbreon EX from Eevee Grove set, near mint with no observable damage. Great for binder collections!</p>

                <button
                    className="my-2 w-24 bg-gray-200 text-black py-2 rounded-lg hover:bg-black hover:text-white">
                    Add to cart
                </button>
                </div>
            </Card>
      </div>
        
    )
}
export default ListingPage;
