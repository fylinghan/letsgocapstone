import { useState } from "react";
import Martlogo from "/images/sgcardmart.png";
import { Link } from "react-router-dom";
import { SocialIcon } from "react-social-icons";

function Footer() {
  const [count, setCount] = useState(0);

  return (
    <>
      <footer className="flex justify-between py-8 pr-24 pl-16 bg-gray-500">
        <div className="flex flex-col justify-end items-center">
          <img className="w-40 p-2" src={Martlogo} alt="website logo" />
          <div className="flex gap-4"> 
            <SocialIcon url="www.facebook.com" style={{ height: 35, width: 35}} />
            <SocialIcon url="www.instagram.com" style={{ height: 35, width: 35 }} />
          </div>
        </div>

        <div className="flex justify-between gap-8 text-white font-poppins items-center">
            <ul>
                <li className="font-bold text-xl pb-2">Help</li>
                <li><Link to="/faq">FAQ</Link></li>
                <li><Link to="contact">Contact us</Link></li>
            </ul>

            <ul>
                <li className="font-bold text-xl pb-2">Other</li>
                <li>Privacy policy</li>
                <li>Terms and conditions</li>
            </ul>
        </div>
      </footer>
    </>
  );
}

export default Footer;
