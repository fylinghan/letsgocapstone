import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  function handleSubmit(event) {
    event.preventDefault();   // Prevent page reload on submit

    if (!email || !password) {
      alert("Please fill in both fields");
      return;
    }

    alert(`Logged in with email: ${email}`);
  }

  return (
    <form
      onSubmit={handleSubmit}
      className="max-w-sm mx-auto mt-10 flex flex-col gap-4"
    >
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

      <Button type="submit" className="w-full">
        Login
      </Button>
    </form>
  );
}
