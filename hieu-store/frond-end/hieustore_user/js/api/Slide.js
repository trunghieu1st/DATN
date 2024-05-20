import { apiPaths } from "./url.js"
async function getAllSlides() {
    const url = apiPaths.getAllSlides;
    try {
        const response = await fetch(url, {
            method: 'GET',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            }
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        else {
            updateSlides(response.data.items);
        }
    } catch (error) {
        console.error('Error:', error);
        throw error; // Đẩy lỗi để bắt ở phía trên
    }
}
function updateSlides(slides) {
    var ulElement = $('#bxslider-home4');
    // Xóa tất cả các mục hiện tại trong danh sách
    ulElement.empty();
    // Lặp qua mảng slides và thêm chúng vào danh sách
    $.each(slides, function (index, slide) {
        var liElement = $('<li>');
        var imgElement = $('<img>').attr('src', slide.avatar).attr('alt', 'Slide');
        var divCaptionGroup = $('<div>').addClass('caption-group');
        var h2Element = $('<h2>').addClass('caption title').text(slide.productName);
        var h4Element = $('<h4>').addClass('caption subtitle').text(slide.description);
        var aElement = $('<a>').addClass('caption button-radius').attr('href', '#').text('Shop now');
        // Thêm các phần tử vào liElement
        divCaptionGroup.append(h2Element, h4Element, aElement);
        liElement.append(imgElement, divCaptionGroup);
        // Thêm liElement vào ulElement
        ulElement.append(liElement);
    });
    // Kiểm tra xem bxSlider đã được khởi tạo chưa
    if (ulElement.hasClass('bxslider')) {
        // Nếu đã khởi tạo, cập nhật bxSlider
        ulElement.reloadSlider();
    } else {
        // Nếu chưa khởi tạo, khởi tạo bxSlider
        ulElement.bxSlider({
            // Cấu hình bxSlider nếu cần
        });
    }
}
// Gọi hàm loadSlides khi trang web được tải
document.addEventListener("DOMContentLoaded", function () {
    // Mã JavaScript của bạn ở đây sẽ được thực thi khi trang đã được tải xong.
    getAllSlides();
});
