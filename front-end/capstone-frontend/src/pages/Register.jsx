import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Link, useNavigate } from "react-router-dom";

function Register({setUser}) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirm, setConfirm] = useState("");
  const navigate = useNavigate();

  function handleSubmit(event) {
    event.preventDefault();   // Prevent page reload on submit

    if (!email || !password || !confirm) {
      alert("Please fill in all fields");
      return;
    }
    if (password !== confirm) {
        alert("Passwords do not match. Please re-enter.")
        return;
    }

    alert(`Account created: ${email}`);
    const username = email.split("@")[0];
    setUser({ username });

    navigate("/");
  }

  return (
    <form
      onSubmit={handleSubmit}
      className="max-w-sm mx-auto m-20 flex flex-col gap-4">

      <h1>Register Account</h1>

      <Input
        type="email"
        placeholder="Enter your email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required                         // HTML5 validation attribute
      />

      <Input
        type="password"
        placeholder="Enter your password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        required                       // HTML5 validation attribute
      />
      <Input
        type="password"
        placeholder="Re-enter your password"
        value={confirm}
        onChange={(e) => setConfirm(e.target.value)}
        required                       // HTML5 validation attribute
      />

      <Button type="submit" className="w-full mt-2 hover:bg-gray-400 hover:text-black">
        Register
      </Button>
      <Link to="/Login" className="text-center">Have an account? Sign in here</Link>
    </form>
  );
}
export default Register