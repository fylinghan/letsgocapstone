import MarketPage from "./MarketPage";

function Cards() {
    return (
        <MarketPage
            title="Card Listings"
            endpoint="http://localhost:8080/products/cards"
            banner="/images/cardbanner.jpg"
        />
    );

}
export default Cards