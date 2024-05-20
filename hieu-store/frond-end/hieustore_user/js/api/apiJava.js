import { apiPaths } from "./url.js";

// Đăng nhập
async function login(username, password) {
    const url = apiPaths.login;
    const data = {
        account: username,
        password: password
    };
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        });
        if (!response.ok) {
            alert("Lỗi kết nối đến cơ sở dữ liệu");
            throw new Error('Network response was not ok');            
        }
        return await response.json();
    } catch (error) {
        console.error('Error:', error);
        throw error; // Đẩy lỗi để bắt ở phía trên
    }
}

// Thêm sản phẩm vào giỏ hàng
async function addToCart(accessToken,productOptionId,quantity) {
    const quantity = 1; // Số lượng mặc định là 1
    const url = apiPaths.addToCart;
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + accessToken
            },
            body: JSON.stringify({
                productOptionId: productOptionId,
                quantity: quantity
            })
        });
        if (response.ok) {
            console.log('Product added to cart successfully');
            alert('Sản phẩm đã được thêm vào giỏ hàng!');
        } else {
            alert("Thêm thất bại");
            console.error('Failed to add product to cart');
        }
    } catch (error) {
        alert("Lỗi kết nối đến cơ sở dữ liệu");
        console.error('Error adding product to cart:', error);
    }
}