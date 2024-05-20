const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function (e) {
    e.preventDefault();
    const id = document.querySelector('#categoryId');
    const name = document.querySelector('#name');
    const description = document.querySelector('#description');
    const avatar = document.getElementById('avatar');
    const avatarFileInput = document.getElementById('avatarFileInput');
    avatarFileInput.addEventListener('change', function () {
        const file = avatarFileInput.files[0]; // Lấy file đầu tiên từ danh sách các files đã chọn
        const filePath = URL.createObjectURL(file); // Tạo đường dẫn tạm thời cho tệp
        avatar.src = filePath;
        console.log(filePath);
    });
    document.getElementById('btSave').addEventListener('click', async function (e) {
        e.preventDefault();
        const inputName = name.value;
        const inputTdescription = description.value;
        const file = avatarFileInput.files[0];
        const formData = new FormData();
        formData.append('name', inputName);
        formData.append('description', inputTdescription);
        if (file) {
            formData.append('avatar', file);
        }       
        updateCategory(formData);
    });

});
async function updateCategory(formData) {
    const response = await fetch(`http://localhost:8080/api/v1/category/${document.querySelector('#categoryId').value}`, {
        method: 'PATCH',
        headers: {
            'Authorization': 'Bearer ' + accessToken
        },
        body: formData
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

