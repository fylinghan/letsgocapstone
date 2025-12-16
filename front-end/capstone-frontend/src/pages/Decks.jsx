import MarketPage from "./MarketPage";

function Decks() {
    return (
        <MarketPage
            title="Battle Decks"
            endpoint="http://localhost:8080/products/decks"
            banner="/images/deckbanner.png"
        />
    );

}
export default Decks