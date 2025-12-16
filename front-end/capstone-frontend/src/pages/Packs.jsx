import MarketPage from "./MarketPage";

function Packs() {
    return (
        <MarketPage
            title="Booster Packs"
            endpoint="http://localhost:8080/products/packs"
            banner="/images/packbanner.jpg"
        />
    );
}
export default Packs