import ProductList from "./ProductList";
import { Button } from "@/components/ui/button"
import { Link } from "react-router-dom";

function Home() {
   const latestCardList = [
    { id: 'e1', image: '/images/umbreon.jpg', type: "card", cardName: 'Umbreon EX', setName: 'Eevee Grove', price: 99.90},
    { id: 'e2', image: '/images/umbreon.jpg', type: "card", cardName: 'Umbreon', setName: 'Eevee Grove', price: 99.00},
    { id: 'e3', image: '/images/pikachu.jpg', type: "card", cardName: 'Pikachu', setName: 'Surging Sparks', price: 79.00},
    { id: 'e4', image: '/images/pikachu.jpg', type: "card", cardName: 'Pikachu EX', setName: 'Surging Sparks', price: 79.90},
    { id: 'e5', image: '/images/bulbasaur.jpg', type: "card", cardName: 'Bulbasaur AR', setName: 'Mega Evolution', price: 69.00},
  ];
  const packsList = [
    { id: 'e1', image: '/images/surging.png', type: "pack", name: 'Scarlet & Violet - Surging Sparks', price: 6.90},
    { id: 'e2', image: '/images/stellar.png', type: "pack", name: 'Scarlet & Violet - Stellar Crown', price: 8.00},
    { id: 'e3', image: '/images/destined.png', type: "pack", name: 'Scarlet & Violet - Destined Rivals', price: 9.00},
  ];
  const deckList = [
    { id: 'e1', image: '/images/corvi.jpg', type: "deck", name: 'V Battle Deck - Corviknight', price: 25.00},
    { id: 'e2', image: '/images/corvi.jpg', type: "deck", name: 'V Battle Deck - Corviknight', price: 25.00},
    { id: 'e3', image: '/images/corvi.jpg', type: "deck", name: 'V Battle Deck - Corviknight', price: 25.00},
  ];

  return (
    <div>
      {/* Banner */}
      <div className="w-full h-60 flex">
        <img
          src="/images/eevee.jpg"
          alt="new set"
          className="w-3/4 h-full object-cover"
        />
        <div className="bg-gray-300 w-1/4 h-full flex flex-col justify-center items-center text-center">
          <p className="text-xl font-semibold">Eevee Grove Now Available!</p>
            <Link to="/cards">
                <Button className="w-40 mt-4 bg-white hover:bg-black text-black hover:text-white">Shop now</Button>
            </Link>
        </div>
      </div>
      
      {/* Latest listings */}
      <h1 className="m-4 p-2">Latest Card Listings</h1>
      <div className="shadow pb-8">
        <div className="flex justify-center">
          <ProductList items={latestCardList} />
        </div>
        <div className="flex justify-center">
            <Link to="/cards">
                <Button className="mt-4 w-80 bg-black text-white py-2 rounded-lg hover:bg-gray-400 
                hover:text-black">Browse more cards</Button>
            </Link>
        </div>
      </div>

      {/* Booster Packs */}
      <div className="bg-gray-300 pb-8">
        <h1 className="p-8 text-center">Booster Packs</h1>
        <div className="flex justify-center">
          <ProductList items={packsList} />
        </div>
        <div className="flex justify-center">
            <Link to="/packs">
                <Button className="mt-8 w-80 bg-black text-white py-2 rounded-lg hover:bg-gray-400 
                hover:text-black">See more booster packs</Button>
            </Link>
        </div>
      </div>

      {/* Battle Decks */}
      <div>
        <h1 className="m-4 p-2 pt-4 pb-8">Battle Decks</h1>
        <div className="flex justify-center">
          <ProductList items={deckList} />
        </div>
        <div className="flex justify-center"><Button className="m-6 w-1/5 bg-black text-white py-2 
        rounded-lg hover:bg-gray-400 hover:text-black">View more decks</Button></div>
      </div>

      {/* {Sell cards} */}
      <div className="flex bg-gray-300 p-6">
        <div className="w-1/2 flex flex-col justify-center item-center text-center">
          <h1 className="">Profit off your cards</h1>
          <p>Got Pokémon cards collecting dust? Turn them into cash on our trusted 
            marketplace! We're secure, transparent, and built for collectors — list 
            your cards in minutes, reach thousands of buyers, and get paid fast. Sell smarter, earn more.</p>
        </div>
        <div className="w-1/2 flex justify-center items-center"><Button className="m-6 w-60 bg-black
         text-white rounded-lg hover:bg-gray-400 hover:text-black">Submit your listing now!</Button></div>
      </div>
    </div>
    
  )
}

export default Home
