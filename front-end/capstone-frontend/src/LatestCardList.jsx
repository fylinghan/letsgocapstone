import LatestCardListItem from './LatestCardListItem.jsx';
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card"

function LatestCardList(props) {
  return (
    <Card className="w-[90%] border-0 shadow-none">
      <ul className="flex flex-wrap justify-around">
        {props.items.map((latestCardList) => (
          <LatestCardListItem
            key={latestCardList.id}
            cardName={latestCardList.cardName}
            setName={latestCardList.setName}
            price={latestCardList.price}
            image={latestCardList.image}
          />
        ))}
      </ul>
    </Card>
  );
}

export default LatestCardList;
