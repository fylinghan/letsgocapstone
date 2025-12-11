import { useState } from "react";
import Martlogo from "/images/sgcardmart.png";
import { Link } from "react-router-dom";
import "./Footer.css";
import { SocialIcon } from "react-social-icons";

function Footer() {
  const [count, setCount] = useState(0);

  return (
    <>
      <footer>
        <div className="footerLeftGroup">
          <img className="logo" src={Martlogo} alt="website logo" />
          <div className="social-icon"> 
            <SocialIcon url="www.facebook.com" style={{ height: 35, width: 35}} />
            <SocialIcon url="www.instagram.com" style={{ height: 35, width: 35 }} />
          </div>
        </div>

        <div className="footerRightGroup">
            <ul>
                <li className="menuHead">Help</li>
                <li>FAQ</li>
                <li>Customer service</li>
                <li>How to guides</li>
                <li>Contact us</li>
            </ul>

            <ul>
                <li className="menuHead">Other</li>
                <li>Privacy policy</li>
                <li>Terms and conditions</li>
            </ul>
        </div>
      </footer>
    </>
  );
}

export default Footer;
