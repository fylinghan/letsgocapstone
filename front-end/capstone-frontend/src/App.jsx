import { Routes, Route, Navigate, Outlet } from "react-router";
import Home from "./pages/Home";
import Register from "./pages/Register";
import Navbar from "@/components/ui/Navbar";
import Footer from "@/components/ui/Footer";

function App() {
  return (
    <div>
      <Navbar/>
      <Routes>
        <Route path="/" element ={<Home />} />
        {/* <Route path="/login" element ={<Login />} /> */}
        <Route path="/register" element ={<Register />} />
        {/* <Route path="/cards" element ={<Cards />} /> */}
        {/* <Route path="/packs" element ={<Packs />} /> */}
        {/* <Route path="/decks" element ={<Decks />} /> */}
      </Routes>
      
      <Footer/>
    </div>
    
  )
}

export default App
