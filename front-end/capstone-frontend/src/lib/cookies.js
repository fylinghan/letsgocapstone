export function getCookie(name) {
  // Split document.cookie into individual "name=value" pairs
  const cookies = document.cookie.split("; ");

  // Loop through each cookie
  for (let cookie of cookies) {
    // Each cookie is "name=value"
    const [key, value] = cookie.split("=");
    if (key === name) {
      return value; // Return the value if the name matches
    }
  }

  // If not found, return null
  return null;
}