import { useState } from "react";
import ProductList from "./ProductList";


function Cards({products}) {
    const [sortBy, setSortBy] = useState("latest");
    const [filterSet, setFilterSet] = useState("all");

    const filteredProducts = products.filter((product) => {
        if (filterSet === "all") return true;
        return product.setName === filterSet;
    });
    
    const sortedProducts = [...filteredProducts].sort((a, b) => {
        if (sortBy === "priceLow") return a.price - b.price;
        if (sortBy === "priceHigh") return b.price - a.price;
        if (sortBy === "latest")
            return new Date(b.createdAt) - new Date(a.createdAt);
        return 0;
    });

    return (
      <div>
        {/* Banner */}
        <div className="w-full h-60 flex items-center bg-cover bg-center"
          style={{ backgroundImage: "url('/images/cardbanner.jpg')" }}>
          <div className="left-0 top-0 h-full w-full bg-white/20 backdrop-blur flex flex-col justify-center pl-20">
            <p className="font-bold text-5xl">Card Listings</p>
          </div>
        </div>

        {/* Listing header*/}
        <div className="flex flex-wrap justify-between items-center">
          <div className="flex items-center py-8 px-4">
            <p className="w-40 font-bold text-2xl">Filter</p>
            <input type="text" placeholder="Search..." className="w-80 mx-20 pl-4 border border-gray-300 rounded"/>
          </div>
          <div className="mr-20 ">
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
        </div>

        <div className="flex">
            {/* Filter */}
            <div className="w-40 pl-4">
                <select
                    value={filterSet}
                    onChange={(e) => setFilterSet(e.target.value)}
                    className="border rounded p-2"
                    >
                    <option value="all">All Sets</option>
                    <option value="Eevee Grove">Eevee Grove</option>
                    <option value="Surging Sparks">Surging Sparks</option>
                    <option value="Mega Evolution">Mega Evolution</option>
                </select>
            </div>

            {/* Listings */}
            <div className="pl-20 w-full">
                <ProductList items={sortedProducts} />
            </div>
        </div>

      </div>
    )
}
export default Cards