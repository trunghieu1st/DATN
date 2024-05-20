const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function () {
    document.querySelectorAll('.trash').forEach(button => {
        button.addEventListener('click', function () {
            const userID = this.getAttribute('id'); // Lấy ID người dùng từ thuộc tính id của nút
            swal({
                title: "Bạn có chắc không?",
                text: "Một khi bạn xóa, bạn sẽ không thể khôi phục lại thông tin này!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        deleteUser(userID);
                    }
                });
        });
    });

});
async function deleteUser(userID) {
    const response = await fetch(`http://localhost:8080/api/v1/user/${userID}`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Bearer ' + accessToken
        },
    });
    if (response.ok) {
        swal({
            title: "",
            text: "Xóa thành công",
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
            title: "Thất bại!",
            text: "Xóa người dùng thất bại.",
            icon: "error",
            button: true, // Hiện nút để người dùng có thể đóng thông báo thủ công
            timer: 3000, // Thời gian hiển thị là 3 giây
            closeOnClickOutside: false, // Không cho phép đóng khi click bên ngoài
            closeOnEsc: false // Không cho phép đóng khi nhấn Esc
        });
    }
}
