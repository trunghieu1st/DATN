var accessToken = localStorage.getItem('accessToken');
if (!accessToken) {
    console.error('Access token not found');
}
async function addToCart(productId) {
    const quantity = 1; // Số lượng mặc định là 1
    try {
        const response = await fetch('http://localhost:8080/api/v1/cart/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + accessToken
            },
            body: JSON.stringify({
                productOptionId: productId,
                quantity: quantity
            })
        });
        if (response.ok) {
            console.log('Product added to cart successfully');
            alert('Sản phẩm đã được thêm vào giỏ hàng!');
        } else {
            console.error('Failed to add product to cart');
        }
    } catch (error) {
        console.error('Error adding product to cart:', error);
    }
}