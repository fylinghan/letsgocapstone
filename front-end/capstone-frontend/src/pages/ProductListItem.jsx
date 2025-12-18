import { Card } from "@/components/ui/card";
import { useNavigate } from "react-router-dom";

function ProductListItem({ product, showBuyButton = true }) {
  const displayName = product.productName;
  const displaySet  = product.seriesName;
  const navigate = useNavigate();

  let borderColor = "";
  switch (product.productType) {
    case "CARD":
      borderColor = "border-gray-200";
      break;
    case "PACK":
      borderColor = "border-blue-400";
      break;
    case "DECK":
      borderColor = "border-purple-400";
      break;
    default:
      borderColor = "border-gray-300";
  }

  return (
    <li className="w-56">
      <Card
        className={`h-80 text-center p-4 m-4 border-2 cursor-pointer box-border flex flex-col`}
        onClick={() => navigate(`/products/${product.productID}`)}
      >
        <div className="h-[60%] m-4">
          <img
            className="w-32 h-full mx-auto mb-4"
            src={"http://localhost:8080" + product.imgPath}
            alt={displayName}
          />
        </div>

        <div className="font-semibold text-lg">{displayName}</div>

        {displaySet && (
          <div className="text-sm text-gray-500 mb-2">{displaySet}</div>
        )}

        <div className="text-blue-600 font-bold">
          ${Number(product.price).toFixed(2)}
        </div>

        {product.productType === "PACK" && showBuyButton && (
          <button
            className="mt-auto w-full bg-gray-200 text-black py-2 rounded-lg hover:bg-black hover:text-white"
            onClick={(e) => {
              e.stopPropagation(); // prevent card's onClick when clicking buy
              // your buy logic here
            }}
          >
            Buy Pack
          </button>
        )}
      </Card>
    </li>

  );
}

export default ProductListItem;
