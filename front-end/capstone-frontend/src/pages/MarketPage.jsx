import { useState, useEffect} from "react";
import ProductList from "./ProductList";

function MarketPage({endpoint, title, banner}) {
  const [sortBy, setSortBy] = useState("latest");
  const [filterSet, setFilterSet] = useState("all");
  const [products, setProducts] = useState([]);

  useEffect(() => {
  fetch(endpoint)
    .then(res => {
      console.log("Response status:", res.status);
      return res.json();
    })
    .then(data => {
      console.log("Fetched products:", data);
      setProducts(data);
    })
    .catch(err => console.error("Fetch error:", err));
}, [endpoint]);


  const filteredProducts = products.filter((product) => {
    if (filterSet === "all") return true;
    return product.seriesName === filterSet;
  });

  const sortedProducts = [...filteredProducts].sort((a, b) => {
    if (sortBy === "priceLow") return a.price - b.price;
    if (sortBy === "priceHigh") return b.price - a.price;
    if (sortBy === "latest") {
      return new Date(b.dateAdded) - new Date(a.dateAdded);
    }
    return 0;
  });

  return (
    <div>
      {/* Banner */}
      <div
        className="w-full h-60 flex items-center bg-cover bg-center"
        style={{ backgroundImage: "url('" + banner + "')" }}
      >
        <div className="h-full w-full backdrop-blur bg-white/30 flex items-center pl-20">
          <p className="font-bold text-5xl">{title}</p>
        </div>
      </div>

      {/* Main layout */}
      <div className="flex gap-8 px-6 py-8">
        
        {/* LEFT: Filter column */}
        <div className="w-60 shrink-0">
          <p className="font-bold text-2xl mb-4">Filter</p>

          <select
            value={filterSet}
            onChange={(e) => setFilterSet(e.target.value)}
            className="border rounded p-2 w-3/4"
          >
            <option value="all">All Sets</option>
            <option value="Eevee Grove">Eevee Grove</option>
            <option value="Surging Sparks">Surging Sparks</option>
            <option value="Mega Evolution">Mega Evolution</option>
          </select>
        </div>

        {/* RIGHT: Search + Sort + Listings */}
        <div className="flex-1">
          
          {/* Search & Sort row */}
          <div className="flex flex-wrap justify-between items-center mb-6 gap-4">
            <input
              type="text"
              placeholder="Search..."
              className="w-80 pl-4 border border-gray-300 rounded"
            />

            <select
              value={sortBy}
              onChange={(e) => setSortBy(e.target.value)}
              className="border rounded px-3 py-2"
            >
              <option value="latest">Latest</option>
              <option value="priceLow">Price: Low → High</option>
              <option value="priceHigh">Price: High → Low</option>
            </select>
          </div>

          {/* Listings */}
          <ProductList items={sortedProducts}  align="center"/>
        </div>
      </div>
    </div>
  );
}

export default MarketPage;
