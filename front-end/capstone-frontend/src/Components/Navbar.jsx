import { useState } from "react";
import Martlogo from "../assets/sgcardmart.png";
import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  const [count, setCount] = useState(0);

  return (
    <>
      <nav>
        <div className="leftGroup">
          <img className="logo" src={Martlogo} alt="website logo" />
          <ul className="leftSide">
            <li>Cards</li>
            <li>Booster Packs</li>
            <li>Battle Decks</li>
          </ul>
        </div>

        <ul className="rightSide">
          <li>
            <input type="text" placeholder="Search..."/>
          </li>
          <li>Register/Login</li>
        </ul>
      </nav>
    </>
  );
}

export default Navbar;
