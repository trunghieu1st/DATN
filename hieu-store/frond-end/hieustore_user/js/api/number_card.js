import { apiPaths } from "./url.js"
var accessToken = localStorage.getItem('accessToken');
if (!accessToken) {
    //alert('Bạn chưa đăng nhập!');
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function () {
    // Sử dụng productId trong URL của yêu cầu fetch
    const response = await fetch(`http://localhost:8080/api/v1/cart/all`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });
    if (response.ok) {
        const data = await response.json();
        var number_card = document.getElementById('number-card');
        number_card.textContent = data.data.items.length;
    } else {
        console.error('Failed to fetch product information');
    }
});