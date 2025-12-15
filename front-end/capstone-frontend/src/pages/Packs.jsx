import MarketPage from "./MarketPage";

function Packs() {
    return (
        <MarketPage
            title="Booster Packs"
            endpoint="api/products/packs"
            banner="/images/cardbanner.png"
        />
    );
}
export default Packs