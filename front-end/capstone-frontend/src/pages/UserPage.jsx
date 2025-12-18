import { useState, useEffect, Link } from "react";
import { useParams } from "react-router-dom";
import { Card } from "@/components/ui/card"
import { getCookie } from "@/lib/cookies";
import ProductList from "./ProductList";

function UserPage() {
    const { user } = useParams();
    const [userInfo, setUserInfo] = useState(null);

    useEffect(() => {
            fetch(`http://localhost:8080/user/${user}`)
                .then(res => {
                console.log("Response status:", res.status);
                return res.json();
                })
                .then(data => {
                console.log("Fetched products:", data);
                setUserInfo(data);
                })
                .catch(err => console.error("Fetch error:", err));
            }, [user]);

    if (!userInfo) {
        return <div className="text-center mt-10">Loading...</div>;
    }

    const loggedInEmail = getCookie("user");
    const isSameUser = loggedInEmail === userInfo.email;

    return (
        <div className="flex justify-center">
            <Card className="w-[80%] flex flex-col items-center p-4 m-8 border-2">
                <div className="flex flex-col w-full items-center">
                    <div className="flex flex-col justify-around items-center m-8">
                        <img className="h-32 mx-auto"
                            src="/images/usericon.png"
                            alt={userInfo.email.split("@")[0]}
                            />
                        
                        <p>{userInfo.email}</p>
                        {isSameUser && <p className="mt-10 font-bold text-3xl text-blue-400">Orders</p>}
                    </div>
                    <div className="flex flex-col text-center">
                        <p className="text-3xl font-bold m-4">User Listings</p>
                        <ProductList items={userInfo.cards} showBuyButton={false}></ProductList>
                    </div>
                </div>
            </Card>
        </div>
    )
}
export default UserPage;