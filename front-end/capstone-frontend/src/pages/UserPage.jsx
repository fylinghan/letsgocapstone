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
            <Card className="w-full flex flex-col items-center py-4 m-8 border-2">
                <div className="flex flex-col w-full items-center">
                    <div className="flex flex-col justify-around items-center m-2">
                        <img className="h-32 mx-auto"
                            src="/images/usericon.png"
                            alt={userInfo.email.split("@")[0]}
                            />
                        
                        <p>{userInfo.email}</p>
                        {isSameUser && <p className="mt-10 font-bold text-3xl text-blue-400">Orders</p>}
                    </div>
                    <div className="w-full flex flex-col text-center p-8">
                        <p className="text-3xl font-bold m-4">User Listings</p>
                        <div className="flex items-center justify-center">
                        <ProductList items={userInfo.cards} align="center" showBuyButton={false}></ProductList>
                        </div>
                    </div>
                </div>
            </Card>
        </div>
    )
}
export default UserPage;