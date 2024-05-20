import { apiPaths } from "./url.js"
var accessToken = localStorage.getItem('accessToken');
if (!accessToken) {
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function () {
    const url = apiPaths.getAllCart;
    const response = await fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });
    function formatNumber(number) {
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }
    if (response.ok) {
        const data = await response.json();
        const products = data.data.items;
        const productCarousel = document.getElementById('product-cart');
        document.querySelectorAll('sanpham-moixem').values = localStorage.getItem('productId');
        products.forEach(product => {
            var productHTML = `
                <tr class="cart_item">
                    <td class="product-remove">
                    <input type="button" value="x" title="Remove this item" class="remove_product" id="${product.id}"
                         onclick="confirmRemove('${product.id}')">
                    </td>
                    <td class="product-thumbnail">
                        <a href="single-product.html"><img width="145" height="145"
                                alt="poster_1_up" class="shop_thumbnail"
                                src="${product.productOptionDto.image}"></a>
                    </td>
                    <td class="product-name">
                        <a href="single-product.html">${product.productOptionDto.productName}</a>
                    </td>
                    <td class="product-price">
                        <span class="amount">${formatNumber(product.productOptionDto.price)} VNĐ</span>
                    </td>
                    <td class="product-quantity">
                        <div class="quantity buttons_added">
                         
                            <input type="number" size="4" class="input-text qty text"
                                title="Qty" value="${product.quantity}" min="0" step="1">                           
                        </div>
                    </td>
                    <td class="product-subtotal">
                        <span class="amount">${formatNumber(product.quantity * product.productOptionDto.price)} VNĐ<span>
                    </td>
                </tr>`;
            productCarousel.insertAdjacentHTML('afterbegin', productHTML);
        });
    } else {
        console.error('Failed to fetch products');
    }
});
