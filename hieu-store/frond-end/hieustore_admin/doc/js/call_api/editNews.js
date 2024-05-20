const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function (e) {
    e.preventDefault();
    const id = document.querySelector('#newId');
    const title = document.querySelector('#title');
    const content = document.querySelector('#content');
    const status = document.querySelector('#status');
    const summary = document.querySelector('#summary');
    const categoryId = document.querySelector('#categoryId');
    const avatar = document.getElementById('avatar');
    const avatarFileInput = document.getElementById('avatarFileInput');

    avatarFileInput.addEventListener('change', function () {
        const file = avatarFileInput.files[0]; // Lấy file đầu tiên từ danh sách các files đã chọn
        const filePath = URL.createObjectURL(file); // Tạo đường dẫn tạm thời cho tệp
        avatar.src = filePath;
        console.log(filePath);
    });

    document.getElementById('btSave').addEventListener('click', async function () {
        const inputCategoryId = categoryId.id;
        const inputTitle = title.value;
        const inputStatus = status.value;
        const inputSummary= summary.value;
        const inputContent = content.value;       
        const file = avatarFileInput.files[0];
        const formData = new FormData();
        formData.append('title', inputTitle);
        formData.append('status', inputStatus);
        formData.append('summary', inputSummary);
        formData.append('content', inputContent);
        formData.append('categoryId', inputCategoryId);
        if (file) {
            formData.append('avatar', file);
        }
        updateNews(formData);
    });

});
async function updateNews(formData) {
    const response = await fetch(`http://localhost:8080/api/v1/news/${document.querySelector('#newId').value}`, {
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

