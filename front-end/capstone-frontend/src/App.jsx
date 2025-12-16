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
import ListingPage from "./pages/ListingPage";

function App() {
  const [user, setUser] = useState(null);
  
  return (
    <div>
      <Navbar user={user} setUser={setUser}/>
      <Routes>
        <Route path="/" element ={<Home />} />
        <Route path="/login" element ={<Login setUser={setUser} />} />
        <Route path="/register" element ={<Register setUser={setUser} />} />
        <Route path="/cards" element ={<Cards />} />
        <Route path="/packs" element ={<Packs />} />
        <Route path="/decks" element ={<Decks />} />
        <Route path="/products/:id" element={<ListingPage />} />
      </Routes>
      
      <Footer/>
    </div>
    
  )
}

export default App
