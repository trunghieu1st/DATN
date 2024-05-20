import { apiPaths } from "./url.js"
var accessToken = localStorage.getItem('accessToken');
if (!accessToken) {
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function () {
    const response = await fetch('http://localhost:8080/api/v1/product/option/allProduct', {
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
        const products = data.data;
        const productCarousel = document.getElementById('shop-page');
        products.forEach(product => {
            var productHTML = `
                    <div class="col-md-3 col-sm-6">
                    <div class="single-shop-product">
                        <div class="product-upper">
                            <img src="${product.image}" alt="">
                        </div>
                        <br/>
                        <div style="margin-left: 20px;">
                            <h4><a href="./single-product.html" class="product-info" id="${product.id}">${product.productName}</a></h4>
                            <div class="product-carousel-price">                         
                                <ins style="margin-left: 25px;">Màu sắc: ${product.color}</ins> </br>                             
                                <ins style="margin-left: 25px;">RAM: ${product.ram}</ins> 
                                <ins>Bộ nhớ: ${product.storageCapacity}</ins> </br>
                                <ins style="margin-left: 25px;">Giá: ${formatNumber(product.price)} VNĐ</ins>  </br>  
                                <ins style="margin-left: 25px;">Số lượng còn: ${product.quantity}</ins> 
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
        const productLinks = document.querySelectorAll('.product-info');
        productLinks.forEach(link => {
            link.addEventListener('click', function (event) {
                //event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a
                console.error('aaa');
                const productId = link.id; // Lấy id của thẻ a được click
                localStorage.setItem('productId', productId);
                window.location.href = url; // Chuyển hướng đến trang mới
                event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a
            });
        });
    } else {
        console.error('Failed to fetch products');
    }

    document.getElementById('search').addEventListener('click', async function () {
        const keyword = document.getElementById('keyword').value;
        if (keyword == "" || keyword == null) {
            console.log("Vui lòng nhập đủ thông tin")
        }
        const response = await fetch('http://localhost:8080/api/v1/product/search?keyword=' + keyword, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + accessToken
            }
        });
        if (response.ok) {
            const data = await response.json();
            const products = data.data.items;
            const productCarousel = document.getElementById('shop-page');
            productCarousel.innerHTML = "";
            products.forEach(product => {
                product.productOptionSimpleDtos.forEach(pro => {
                    var productHTML = `
                    <div class="col-md-3 col-sm-6">
                    <div class="single-shop-product">
                        <div class="product-upper">
                            <img src="${pro.image}" alt="">
                        </div>
                        <br/>
                        <div style="margin-left: 20px;">
                            <h4><a href="./single-product.html" class="product-info" id="${pro.id}">${product.name}</a></h4>
                            <div class="product-carousel-price">                         
                                <ins style="margin-left: 25px;">Màu sắc: ${pro.color}</ins> </br>                             
                                <ins style="margin-left: 25px;">RAM: ${pro.ram}</ins> 
                                <ins>Bộ nhớ: ${pro.storageCapacity}</ins> </br>
                                <ins style="margin-left: 25px;">Giá: ${formatNumber(pro.price)} VNĐ</ins>  </br>  
                                <ins style="margin-left: 25px;">Số lượng còn: ${pro.quantity}</ins> 
                            </div>  
                            
                            <div class="product-option-shop" style="margin-left: 20px;">
                                <a id="${pro.id}" class="add_to_cart_button" data-quantity="1" onclick="addToCart('${pro.id}')"
                                data-product_sku="" data-product_id="70">Thêm vào giỏ hàng</a>
                            </div>   
                        </div>
                                            
                    </div>
                </div>
            `;
                    productCarousel.insertAdjacentHTML('beforeend', productHTML);
                });

            });
            //  CHi tiết sản phẩm 
            const productLinks = document.querySelectorAll('.product-info');
            productLinks.forEach(link => {
                link.addEventListener('click', function (event) {
                    //event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a
                    console.error('aaa');
                    const productId = link.id; // Lấy id của thẻ a được click
                    localStorage.setItem('productId', productId);
                    window.location.href = url; // Chuyển hướng đến trang mới
                    event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a
                });
            });
        } else {
            console.error('Failed to fetch products');
        }
    });
});