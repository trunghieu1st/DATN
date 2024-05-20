import { apiPaths } from "./url.js";
var accessToken = localStorage.getItem('accessToken');
if (!accessToken) {
    console.error('Access token not found');
}

document.addEventListener('DOMContentLoaded', async function () {
    const url = apiPaths.getAllCategories;
    const response = await fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    });


    if (response.ok) {
        const data = await response.json();
        const categories = data.data;
        const categoriesLi = document.getElementById('dropdown-menu');

        categories.forEach(product => {
            var categoryHTML = `
                <li><a class="li_category" id = "${product.id}" onclick="category_show('${product.id}')">${product.name}</a></li>
             `;
            categoriesLi.insertAdjacentHTML('beforeend', categoryHTML);
        });

    }

});

function formatNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

async function category_show(id) {
    // Lấy id của thẻ <li> được click          
    var url2 = 'http://localhost:8080/api/v1/product/all' + '?categoryId=' + id;
    const response2 = await fetch(url2, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });

    if (response2.ok) {
        const data = await response2.json();
        const products = data.data.items;
        const productCarousel = document.getElementById('shop-page');

        products.forEach(product => {
            var productHTML = `
                            <div class="col-md-3 col-sm-6">
                            <div class="single-shop-product">
                                <div class="product-upper">
                                    <img src="${product.avatar}" alt="">
                                </div>
                                <br/>
                                <div style="margin-left: 20px;">
                                    <h4><a href="./single-product.html" class="product-info" id="${product.id}">${product.name}</a></h4>
                                    <div class="product-carousel-price">
                                        <ins>Số lượng còn: ${product.name}</ins> </br>
                                        <ins>Màu sắc: ${product.name}</ins> </br>                             
                                        <ins>RAM: ${product.name}</ins> <ins>Bộ nhớ: ${product.storageCanamepacity}</ins> </br>
                                        <ins>Giá: ${formatNumber(product.name)} VNĐ</ins>
                                    </div>  
                                    
                                    <div class="product-option-shop" style="margin-left: 20px;">
                                        <a id="${product.id}" class="add_to_cart_button" data-quantity="1" onclick="addToCart('${product.id}')"
                                        data-product_sku="" data-product_id="70">Thêm vào giỏ hàng</a>
                                    </div>   
                                </div>
                                                    
                            </div>
                        </div>
                    `;

            productCarousel.insertAdjacentHTML('beforeend', productHTML);
        });
        //  CHi tiết sản phẩm 
        // const productLinks = document.querySelectorAll('.product-info');
        // productLinks.forEach(link => {
        //     link.addEventListener('click', function (event) {
        //         //event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a
        //         console.error('aaa');
        //         const productId = link.id; // Lấy id của thẻ a được click
        //         localStorage.setItem('productId', productId);
        //         window.location.href = url; // Chuyển hướng đến trang mới
        //         event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a
        //     });
        // });
    } else {
        console.error('Failed to fetch products');
    }

}
