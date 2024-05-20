import { apiPaths } from "./url.js"
var productId = localStorage.getItem('productId');
if (!productId) {
    console.error('Product ID not found in LocalStorage');
}
// Lấy thông tin cá nhân từ API khi trang được tải
document.addEventListener('DOMContentLoaded', async function () {
    // Sử dụng productId trong URL của yêu cầu fetch
    const response = await fetch(`http://localhost:8080/api/v1/product/option/${productId}`, {
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
        const count = document.getElementById('maxCount');
        const productData = await response.json();
        // Xử lý dữ liệu sản phẩm được trả về
        const product = productData.data;

        const response2 = await fetch(`http://localhost:8080/api/v1/product/${product.productId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + accessToken
            }
        });

        if (response2.ok) {
            const producyInfo = await response2.json();
            const data = producyInfo.data;
            count.setAttribute('max', product.quantity)
            // Tiếp tục xử lý dữ liệu như bạn muốn
            const year = product.createdDate;
            const productNames = document.querySelectorAll('#product-name');
            productNames.forEach(element => {
                element.innerHTML = '<strong>' + product.productName + '</strong>';
            });
            const productImages = document.querySelectorAll('#product-image');
            productImages.forEach(element => {
                element.src = product.image;
            });
            const productPrices = document.querySelectorAll('#product-price');
            productPrices.forEach(element => {
                element.innerHTML = '<strong>' + 'Giá: ' + formatNumber(product.price) + ' VNĐ' + '</strong>';
            });
            document.getElementById('product-description').innerHTML =
                `<div>                  
                    <p><strong>Ram:</strong> ${product.ram} GB</p> 
                    <p><strong>Bộ nhớ trong:</strong> ${product.storageCapacity} GB</p> 
                    <p><strong>Màu sắc:</strong> ${product.color}</p> 
                    <p><strong>Số lượng còn lại:</strong> ${product.quantity}</p> 
                    <div><p><strong>Công nghệ màn hình:</strong> ${data.screenTechnology}</p>
                    <div><p><strong>Độ phân giản màn hình:</strong> ${data.screenResolution}</p>
                    <div><p><strong>Độ dày:</strong> ${data.widescreen}</p>
                    <div><p><strong>Tần số quét:</strong> ${data.scanFrequency}</p>
                    <div><p><strong>Camera trước:</strong> ${data.frontCamera}</p>
                    <div><p><strong>Camera sau:</strong> ${data.rearCamera}</p>
                    <div><p><strong>Hệ điều hành:</strong> ${data.operationSystem}</p>
                    <div><p><strong>Chip:</strong> ${data.cpu}</p>
                    <div><p><strong>Thời gian ra mắt:</strong> ${year}</p>
                </div>`;
        }
        else {
            console.error('Failed to fetch product information');
        }

    } else {
        console.error('Failed to fetch product information');
    }
});