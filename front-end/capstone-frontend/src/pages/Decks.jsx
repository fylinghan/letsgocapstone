import MarketPage from "./MarketPage";

function Decks() {
    return (
        <MarketPage
            title="Battle Decks"
            endpoint="api/products/decks"
            banner="/images/deckbanner.jpg"
        />
    );

}
export default Decks