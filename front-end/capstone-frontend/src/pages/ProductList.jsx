import ProductListItem from './ProductListItem.jsx';
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card"

function ProductList({items, align = "start"}) {
  const justifyClass= 
  align === "center" ? "justify-center" : "justify-start";

  return (
    <Card className="w-[90%] border-0 shadow-none">
      <ul className={`flex flex-wrap ${justifyClass} gap-4`}>
        {items.map((p) => (
          <ProductListItem key={p.productID} product={p} />
        ))}
      </ul>
    </Card>
  );
}

export default ProductList;
