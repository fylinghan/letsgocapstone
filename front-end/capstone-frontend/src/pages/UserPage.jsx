import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Card } from "@/components/ui/card"
import { getCookie } from "@/lib/cookies";

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

    const loggedInEmail = getCookie("userEmail");
    const isSameUser = loggedInEmail === userInfo.email;

    return (
        <div className="flex justify-center">
            <Card className="w-[80%] h-96 flex flex-col gap-4 items-center p-6 m-8 border-2">
                <div>
                    <div>
                        <img className="h-32 mx-auto"
                            src="/images/usericon.png"
                            alt={userInfo.email.split("@")[0]}
                            />
                        
                        <p>{userInfo.email}</p>
                    </div>
                    <div>
                        <p>Listings</p>
                        {isSameUser && <p>Orders</p>}
                    </div>
                </div>
            </Card>
        </div>
    )
}
export default UserPage;