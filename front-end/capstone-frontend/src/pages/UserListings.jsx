import ProductList from "./ProductList"

function UserListings({user}) {
    return (
        <div className="flex justify-center">
            <ProductList items={user.cards} align="center"/>
        </div>
    )
}
export default UserListings;