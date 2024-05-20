import { apiPaths } from "./url.js"
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');
if (!accessToken) {
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function () {
    const url = apiPaths.getAllProductOption;
    const response = await fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    });
    function formatNumber(number) {
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }
    if (response.ok) {
        const data = await response.json();
        const products = data.data;
        const productCarousel = document.getElementById('latest-product');
        products.forEach(product => {
            var productHTML = `
                <div class="single-product">
                                    
                    <div class="product-f-image">
                        <img src="${product.image}" width ="265px" height="100px" alt="">
                        <div class="product-hover">
                            <a id="${product.id}" onclick="addToCart('${product.id}')" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i>Thêm vào giỏ hàng</a>
                            <a href="single-product.html" id="${product.id}" onclick="seeDetails('${product.id}')"  class="view-details-link"><i
                                    class="fa fa-link"></i>Xem chi tiết</a>
                        </div>
                    </div>
                    <h2><a onclick="seeDetails('${product.id}')" href="single-product.html">${product.productName} </a></h2>
                    <div class="product-carousel-price">
                        <ins>RAM: ${product.ram}GB</ins> <ins>Bộ nhớ trong: ${product.storageCapacity}GB</ins> </br>
                        <ins>Màu sắc: ${product.color}</ins> </br>
                        <ins>Giá: ${formatNumber(product.price)} VNĐ</ins> 
                    </div>
                </div>
            `;
            productCarousel.insertAdjacentHTML('beforeend', productHTML);
        });
    } else {
        console.error('Failed to fetch products');
    }
});