import { Routes, Route, Navigate, Outlet } from "react-router";
import { useState } from "react";
import Home from "./pages/Home";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Cards from "./pages/Cards";
import Packs from "./pages/Packs";
import Decks from "./pages/Decks";
import ProductList from "./pages/ProductList";
import Navbar from "@/components/ui/Navbar";
import Footer from "@/components/ui/Footer";

function App() {
  const [user, setUser] = useState(null);
  const latestCardList = [
    { id: 'e1', image: '/images/umbreon.jpg', type: "card", cardName: 'Umbreon EX', setName: 'Eevee Grove', price: 99.90, createdAt: "2023-12-02"},
    { id: 'e2', image: '/images/umbreon.jpg', type: "card", cardName: 'Umbreon', setName: 'Eevee Grove', price: 99.00 , createdAt: "2023-12-09"},
    { id: 'e3', image: '/images/pikachu.jpg', type: "card", cardName: 'Pikachu', setName: 'Surging Sparks', price: 79.00 , createdAt: "2023-12-10"},
    { id: 'e4', image: '/images/pikachu.jpg', type: "card", cardName: 'Pikachu EX', setName: 'Surging Sparks', price: 79.90 , createdAt: "2023-12-19"},
    { id: 'e5', image: '/images/bulbasaur.jpg', type: "card", cardName: 'Bulbasaur AR', setName: 'Mega Evolution', price: 69.00 , createdAt: "2023-01-20"},
    { id: 'e6', image: '/images/umbreon.jpg', type: "card", cardName: 'Umbreon EX', setName: 'Eevee Grove', price: 99.90 , createdAt: "2023-02-20"},
    { id: 'e7', image: '/images/umbreon.jpg', type: "card", cardName: 'Umbreon', setName: 'Eevee Grove', price: 99.00, createdAt: "2023-03-20"},
    { id: 'e8', image: '/images/pikachu.jpg', type: "card", cardName: 'Pikachu', setName: 'Surging Sparks', price: 79.00, createdAt: "2023-04-20"},
    { id: 'e9', image: '/images/pikachu.jpg', type: "card", cardName: 'Pikachu EX', setName: 'Surging Sparks', price: 79.90, createdAt: "2023-05-20"},
    { id: 'e10', image: '/images/bulbasaur.jpg', type: "card", cardName: 'Bulbasaur AR', setName: 'Mega Evolution', price: 69.00, createdAt: "2023-06-20"},
    { id: 'e11', image: '/images/umbreon.jpg', type: "card", cardName: 'Umbreon EX', setName: 'Eevee Grove', price: 99.90, createdAt: "2023-07-20"},
    { id: 'e12', image: '/images/umbreon.jpg', type: "card", cardName: 'Umbreon', setName: 'Eevee Grove', price: 99.00, createdAt: "2023-08-20"},
    { id: 'e13', image: '/images/pikachu.jpg', type: "card", cardName: 'Pikachu', setName: 'Surging Sparks', price: 79.00, createdAt: "2023-09-20"},
    { id: 'e14', image: '/images/pikachu.jpg', type: "card", cardName: 'Pikachu EX', setName: 'Surging Sparks', price: 79.90, createdAt: "2023-11-20"},
    { id: 'e15', image: '/images/bulbasaur.jpg', type: "card", cardName: 'Bulbasaur AR', setName: 'Mega Evolution', price: 69.00, createdAt: "2023-10-20"},
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
      <Navbar user={user} setUser={setUser}/>
      <Routes>
        <Route path="/" element ={<Home latestCardList={latestCardList} packsList={packsList} deckList={deckList}/>} />
        <Route path="/login" element ={<Login setUser={setUser} />} />
        <Route path="/register" element ={<Register setUser={setUser} />} />
        <Route path="/cards" element ={<Cards products={latestCardList} />} />
        <Route path="/packs" element ={<Packs />} />
        <Route path="/decks" element ={<Decks />} />
      </Routes>
      
      <Footer/>
    </div>
    
  )
}

export default App
