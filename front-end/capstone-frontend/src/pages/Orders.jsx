import { useEffect, useState } from "react";
import { Card } from "@/components/ui/card";
import { getCookie } from "@/lib/cookies";

function Orders() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchOrder = async () => {
      try {
        const res = await fetch(
          `http://localhost:8080/order/user?email=${getCookie("user")}`
        );
        if (!res.ok) throw new Error("No orders currently.");
        const data = await res.json();
        setOrders(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchOrder();
  }, []);

  if (loading) return <p className="text-center mt-10">Loading orders...</p>;
  if (error)
    return <p className="text-center mt-10 text-red-500">{error}</p>;

  const calculateOrderTotal = (orderedItems) => {
    return orderedItems.reduce((sum, item) => {
      return sum + item.quantity * item.product.price;
    }, 0);
  };

  return (
    <div className="max-w-5xl mx-auto px-4 py-8 space-y-6">
      <h1 className="text-2xl font-bold">My Orders</h1>

      {orders.length === 0 ? (
        <p className="text-muted-foreground">No orders currently.</p>
      ) : (
        orders.map(order => (
          <Card
            key={order.orderId}
            className="p-6 space-y-4"
          >
            {/* Order Header */}
            <div className="flex justify-between items-center border-b pb-2">
              <div>
                <p className="font-semibold">
                  Order #{order.orderId}
                </p>
                <p className="text-sm text-muted-foreground">
                  {order.orderDate}
                </p>
              </div>

              <span className="text-sm font-medium px-3 py-1 rounded-full bg-gray-100">
                {order.shippingStatus}
              </span>
            </div>

            {/* Order Items */}
            <div className="space-y-3">
              {order.orderedItems.map(item => (
                <div
                  key={item.orderItemId}
                  className="flex gap-4 items-center border rounded-md p-3"
                >
                  <img
                    src={"http://localhost:8080" + item.product.imgPath}
                    alt={item.product.productName}
                    className="w-20"
                  />

                  <div className="flex-1">
                    <p className="font-medium">
                      {item.product.productName}
                    </p>
                    <p className="text-sm text-muted-foreground">
                      {item.product.seriesName}
                    </p>
                  </div>

                  <div className="text-sm text-right">
                    <p>Qty: {item.quantity}</p>
                    <p className="font-semibold">
                      ${item.product.price.toFixed(2)}
                    </p>
                  </div>
                </div>
              ))}
            </div>
            <div className="flex justify-end border-t pt-4">
              <p className="text-lg font-bold">
                Total: ${calculateOrderTotal(order.orderedItems).toFixed(2)}
              </p>
            </div>
          </Card>
        ))
      )}
    </div>
  );
}

export default Orders;
