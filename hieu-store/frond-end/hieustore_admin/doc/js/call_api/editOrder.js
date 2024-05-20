const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function (e) {
    e.preventDefault();   
    const status = document.getElementById('status');
    document.getElementById('btSave').addEventListener('click', async function () {

        const data = {
            'statusId' : status.value
        };
        updateProduct(data);
    });

});
async function updateProduct(data) {
    const response = await fetch(`http://localhost:8080/api/v1/order/${document.querySelector('#orderId').value}`, {
        method: 'PATCH',
        headers: {
            'Authorization': 'Bearer ' + accessToken,
            'Content-Type': 'application/json' // Chỉ định kiểu dữ liệu là JSON
        },
        body: JSON.stringify(data)
    });
    if (response.ok) {
        swal({
            title: "",
            text: "Cập nhật thành công",
            icon: "success",
            button: false, // Ẩn nút
            timer: 3000, // Thời gian hiển thị là 3 giây
            closeOnClickOutside: false, // Không cho phép đóng khi click bên ngoài
            closeOnEsc: false // Không cho phép đóng khi nhấn Esc
        }).then(() => {
            // Callback này sẽ được gọi sau khi SweetAlert tự đóng
            window.location.reload();
        });
    } else {
        swal({
            title: "Thất bại!Kiểm tra lại dữ liệu nhập",
            text: "Cập nhật thất bại, vui lòng thử lại.",
            icon: "error",
            button: true, // Hiện nút để người dùng có thể đóng thông báo thủ công
            timer: 3000, // Thời gian hiển thị là 3 giây
            closeOnClickOutside: false, // Không cho phép đóng khi click bên ngoài
            closeOnEsc: false // Không cho phép đóng khi nhấn Esc
        });
    }
}

