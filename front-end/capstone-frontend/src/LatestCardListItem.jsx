import { Card } from "@/components/ui/card";

function LatestCardListItem(props) {
  return (
    <li>
      <Card className="text-center p-4 m-4 items-center border-2 p-8">
        <img
          className="w-32 h-42 mx-auto mb-4"
          src={props.image}
          alt={props.cardName}
        />
        <div className="font-semibold text-lg">{props.cardName}</div>
        <div className="text-sm text-gray-500 mb-2">{props.setName}</div>
        <div className="text-blue-600 font-bold">${Number(props.price).toFixed(2)}</div>
      </Card>
    </li>
  );
}

export default LatestCardListItem;
