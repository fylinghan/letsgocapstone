import { useState } from "react";
import Martlogo from "/images/sgcardmart.png";
import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  const [count, setCount] = useState(0);

  return (
    <>
      <nav>
        <div className="leftGroup">
          <Link to="/"><img className="logo" src={Martlogo} alt="website logo" /></Link>
          <ul className="leftSide">
            <Link to="/cards">Cards</Link>
            <Link to="/packs">Booster Packs</Link>
            <Link to="/decks">Battle Decks</Link>
          </ul>
        </div>

        <ul className="rightSide">
          <li>
            <input type="text" placeholder="Search..."/>
          </li>
          <Link to="/register">Register</Link>
          <Link to="/login">Login</Link>
        </ul>
      </nav>
    </>
  );
}

export default Navbar;
