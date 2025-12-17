import Martlogo from "/images/sgcardmart.png";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "./button";

function Navbar({user, setUser}) {
  const navigate = useNavigate();

  function handleLogout() {
    setUser(null);
    document.cookie = `user=; max-age=0; path=/`;
    navigate("/");
  }

  return (
    <>
      <nav className="sticky z-50 top-0 left-0 right-0 flex flex-wrap justify-between px-4 items-center bg-white text-black text-xl font-poppins shadow-lg">
          <div className="flex items-center text-center">
            <Link to="/"><img className="w-40 p-2 mr-8" src={Martlogo} alt="website logo" /></Link>
            <Link to="/cards" className="hover:bg-gray-300 p-4 rounded">Cards</Link>
            <Link to="/packs" className="hover:bg-gray-300 p-4 rounded">Booster Packs</Link>
            <Link to="/decks" className="hover:bg-gray-300 p-4 rounded">Battle Decks</Link>
          </div>
          <div className="flex items-center">
            <input type="text" placeholder="Search..." className="w-80 m-4 pl-4 border border-gray-300 rounded"/>
            <Link to="/cart"><img src="public\images\shopping-twotone.svg"  alt="cart icon"/></Link>
            {!user ? (
              <div>
                <Link to="/register" className="w-1/5 m-2 hover:bg-gray-300 p-4 rounded">Register</Link>
                <Link to="/login" className="w-1/5 hover:bg-gray-300 p-4 rounded">Login</Link>
            </div>)
            : (
              <span className="text-gray-700 flex items-center">
                Hello, {user}
                <Button onClick={handleLogout} className="m-2 hover:bg-gray-400 hover:text-white">Log out</Button>
              </span>
            )
            }
           </div>
      </nav>
    </>
  );
}

export default Navbar;
