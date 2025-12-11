import { Card } from "@/components/ui/card";

function ProductListItem({ product }) {
  // Normalize naming so all types still work
  const displayName = product.cardName || product.name;
  const displaySet  = product.setName || product.series || "";

  // Type-based styles
  let borderColor = "";
  switch (product.type) {
    case "card":
      borderColor = "border-yellow-400"; // golden vibe
      break;
    case "pack":
      borderColor = "border-blue-400";
      break;
    case "battle deck":
      borderColor = "border-purple-400";
      break;
    default:
      borderColor = "border-gray-300";
  }

  return (
    <li>
      <Card className={`text-center p-4 m-4 items-center border-2 ${borderColor}`}>
        <img
          className="w-32 h-42 mx-auto mb-4"
          src={product.image}
          alt={displayName}
        />

        <div className="font-semibold text-lg">{displayName}</div>

        {displaySet && (
          <div className="text-sm text-gray-500 mb-2">{displaySet}</div>
        )}

        <div className="text-blue-600 font-bold">
          ${Number(product.price).toFixed(2)}
        </div>
        {product.type === "pack" && (
          <button
            className="mt-4 w-full bg-black text-white py-2 rounded-lg hover:bg-gray-400 hover:text-black"
          >
            Buy Pack
          </button>
        )}
      </Card>
    </li>
  );
}

export default ProductListItem;
