import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Link, useNavigate } from "react-router-dom";
import { getCookie } from "../lib/cookies";


function Login({setUser}) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  function handleSubmit(event) {
    event.preventDefault();   // Prevent page reload on submit

    if (!email || !password) {
      alert("Please fill in both fields");
      return;
    }

    fetch("http://localhost:8080/login",{
      method:"POST",
      headers:{ 
        "Content-Type": "application/json"
      },
      body: JSON.stringify(
        {
          email:email,
          password:password
        })
    }).then(
      async response => {
        if(!response.ok){
          const errorMessage = await response.text();
          alert(errorMessage);
          throw new Error(errorMessage);
        }
      }
    ).then(data =>{
      alert(`Logged in with email: ${email}`);
      const username = email.split("@")[0];
      document.cookie = `user=${email}; max-age=3600; path=/`;
      setUser(getCookie("user").split("@")[0]);
      navigate(history.back());
    }).catch(err => {
      console.error("Login error:", err.message);
    });
  }

  return (
    <form
      onSubmit={handleSubmit}
      className="max-w-sm mx-auto m-20 flex flex-col gap-4">

      <h1>Login</h1>

      <Input
        type="email"
        placeholder="Enter your email"
        value={email}                     // Controlled component
        onChange={(e) => setEmail(e.target.value)} // Update state on input change
        required                         // HTML5 validation attribute
      />

      <Input
        type="password"
        placeholder="Enter your password"
        value={password}                 // Controlled component
        onChange={(e) => setPassword(e.target.value)} // Update state on input change
        required                       // HTML5 validation attribute
      />

      <Button type="submit" className="w-full mt-2 hover:bg-gray-400 hover:text-black">
        Login
      </Button>
      <Link to="/register" className="text-center">New User? Sign up here</Link>
    </form>
  );
}
export default Login