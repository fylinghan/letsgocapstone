import ProductListItem from './ProductListItem.jsx';
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card"

function ProductList(props) {
  return (
    <Card className="w-[90%] border-0 shadow-none">
      <ul className="flex flex-wrap gap-4">
        {props.items.map((p) => (
          <ProductListItem key={p.id} product={p} />
        ))}
      </ul>
    </Card>
  );
}

export default ProductList;
