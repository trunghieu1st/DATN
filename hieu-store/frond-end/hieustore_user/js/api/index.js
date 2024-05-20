import { apiPaths } from "./url.js"
// Gọi API để lấy dữ liệu
const url = apiPaths.getAllSlides;
fetch(url)
    .then(response => response.json())
    .then(data => {
        const productList = document.getElementById('bxslider-home4');
        const products = data.data.items; // Giả sử dữ liệu trả về là một mảng các sản phẩm từ API
        // Tạo các phần tử HTML tương ứng cho mỗi sản phẩm và thêm vào trong danh sách
        products.forEach(product => {
            productList.innerHTML += ` <li><img style="width:100%; height:400px;" src="${product.avatar}" alt="Slide">
            <div class="caption-group">
                <h4 class="caption subtitle"></h4>
                <a class="caption button-radius" href="shop.html"><span class="icon"></span>Shop now</a>
            </div>
        </li>  `
        });
        // Khởi tạo slider
        $(document).ready(function () {
            $('#bxslider-home4').bxSlider({
                auto: true, // Tự động chuyển slide
                //autoControls: true, // Hiển thị nút chuyển slide
            });
        });
    })
    .catch(error => console.error('Error:', error));
