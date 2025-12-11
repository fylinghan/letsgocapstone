import { useState } from "react"
import LatestCardList from "./LatestCardList";
import { Button } from "@/components/ui/button"
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card"
import Navbar from "@/components/ui/Navbar";
import Footer from "@/components/ui/Footer";

function App() {
  const [count, setCount] = useState(0);
   const latestCardList = [
    { id: 'e1', image: '/images/umbreon.jpg', cardName: 'Umbreon EX', setName: 'Eevee Grove', price: 99.90},
    { id: 'e2', image: '/images/umbreon.jpg', cardName: 'Umbreon', setName: 'Eevee Grove', price: 99.00},
    { id: 'e3', image: '/images/pikachu.jpg', cardName: 'Pikachu', setName: 'Surging Sparks', price: 79.00},
    { id: 'e4', image: '/images/pikachu.jpg', cardName: 'Pikachu EX', setName: 'Surging Sparks', price: 79.90},
    { id: 'e5', image: '/images/bulbasaur.jpg', cardName: 'Bulbasaur AR', setName: 'Mega Evolution', price: 69.00},
  ];

  return (
    <div>
      <Navbar/>
      <h1 className="p-4">Latest Card Listings</h1>
      <div className="flex justify-center">
        <LatestCardList items={latestCardList} />
      </div>
      <div className="flex justify-center"><Button className="w-80 m-4 bg-gray-200 hover:bg-black text-black hover:text-white">Browse more cards</Button></div>
      <Footer/>
    </div>
    
  )
}

export default App
