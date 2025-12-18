import { useState } from "react";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Card } from "@/components/ui/card";
import { getCookie } from "@/lib/cookies";

function UserListing() {
  const [productName, setProductName] = useState("");
  const [seriesName, setSeriesName] = useState("");
  const [price, setPrice] = useState("");

  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);

  const submit = async () => {
    try {
      setLoading(true);
      
      const userEmail = getCookie("user");
      const formData = new FormData();
      formData.append("file", file);

      const uploadRes = await fetch(
        "http://localhost:8080/products/list/card/upload",
        {
          method: "POST",
          body: formData,
        }
      );

      const imgPath = await uploadRes.text();

      const productDTO = {
        productName,
        seriesName,
        price: Number(price),
        imgPath,
        userEmail,
      };

      await fetch("http://localhost:8080/products/list/card", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(productDTO),
      });

      alert("Card listed!");
    } catch (err) {
      console.error(err);
      alert("Failed to list card");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex gap-10 justify-center items-center">
        <h1 className="">List your item!</h1>
        <Card className="flex flex-col items-center p-6 space-y-4 m-20">
            <Input
                type="file"
                accept="image/*"
                onChange={(e) => setFile(e.target.files[0])}
            />

            <Input
                placeholder="Item name"
                value={productName}
                onChange={(e) => setProductName(e.target.value)}
            />

            <Input
                placeholder="Series name"
                value={seriesName}
                onChange={(e) => setSeriesName(e.target.value)}
            />

            <Input
                type="number"
                step="0.01"
                min="0"
                placeholder="Price"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
            />

            <Button disabled={!file || loading} onClick={submit}>
                {loading ? "Listing..." : "List Card"}
            </Button>
        </Card>
    </div>
  );
}
export default UserListing;
