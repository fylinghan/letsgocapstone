import { Routes, Route, Navigate, Outlet } from "react-router";
import { useState } from "react";
import Home from "./pages/Home";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Cards from "./pages/Cards";
import Packs from "./pages/Packs";
import Decks from "./pages/Decks";
import Navbar from "@/components/ui/Navbar";
import Footer from "@/components/ui/Footer";
import Cart from "./pages/Cart"
import Checkout from "./pages/Checkout"
import ListingPage from "./pages/ListingPage";
import UserPage from "./pages/UserPage";
import UserListing from "./pages/UserListing";

function App() {
  const [user, setUser] = useState(null);
  
  return (
    <div>
      <Navbar user={user} setUser={setUser} className="flex-start"/>
      <Routes>
        <Route path="/" element ={<Home />} />
        <Route path="/login" element ={<Login setUser={setUser} />} />
        <Route path="/register" element ={<Register setUser={setUser} />} />
        <Route path="/cards" element ={<Cards />} />
        <Route path="/packs" element ={<Packs />} />
        <Route path="/decks" element ={<Decks />} />
        <Route path="/cart" element ={<Cart />} />
        <Route path="/checkout" element ={<Checkout />} />
        <Route path="/products/:id" element={<ListingPage />} />
        <Route path="/user/:user" element={<UserPage />} />
        <Route path="/listitem" element={<UserListing />} />
      </Routes>
      <Footer/>
    </div>
    
  )
}

export default App
