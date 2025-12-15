import { Card } from "@/components/ui/card";

function ProductListItem({ product }) {
  const displayName = product.productName;
  const displaySet  = product.seriesName;

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
      <Card className={`h-full text-center p-4 m-4 items-center border-2 ${borderColor}`}>
        <img
          className="w-32 h-42 mx-auto mb-4"
          src={"http://localhost:8080" + product.imgPath}
          alt={displayName}
        />

        <div className="font-semibold text-lg">{displayName}</div>

        {displaySet && (
          <div className="text-sm text-gray-500 mb-2">{displaySet}</div>
        )}

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
    </li>
  );
}

export default ProductListItem;
