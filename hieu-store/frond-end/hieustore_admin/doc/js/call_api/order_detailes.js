const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');
if (!accessToken) {
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function () {
    const url = 'http://localhost:8080/api/v1/order/all';
    const response = await fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });
    if (response.ok) {
        const data = await response.json();
        const orders = data.data.items;
        orders.forEach(async order => {
            const link = 'http://localhost:8080/api/v1/order-detail/all/' + `${order.id}`;
            const response2 = await fetch(link, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + accessToken
                }
            });
            if (response2.ok) {
                const data2 = await response2.json();
                const order_details = data2.data;
                let table = document.getElementById('orderDetailBody');
                // Tạo HTML cho mỗi nhóm hàng với rowspan
                let rowsHtml = '';
                let i = 0;
                
                order_details.forEach(od => {
                    rowsHtml += `
                            <tr>                                
                                <td><id="${od.id}">${++i}</td>
                                <td>${od.orderId}</td>
                                <td>${od.productOptionDto.productName}</td>
                                <td>${od.productOptionDto.ram}</td>
                                <td>${od.productOptionDto.storageCapacity}</td>
                                <td>${od.productOptionDto.color}</td>                               
                                <td>${formatNumber(od.productOptionDto.price)}</td>
                                <td>${od.productOptionDto.quantity}</td>
                                <td>${formatNumber(od.productOptionDto.quantity * od.productOptionDto.price)}</td>
                                <td>${od.productOptionDto.status}</td>                       
                                <td>${od.productOptionDto.image == null ? '' : `<img src="${od.productOptionDto.image}" width="100">`}</td>                          
                            </tr>
                        `;
                });
            

        table.innerHTML += rowsHtml; // Thêm tất cả hàng vào bảng
    }

});

    } else {
    console.error('Failed to fetch products');
}
const editButtons = document.querySelectorAll('.edit'); // Chọn tất cả các nút "Sửa"
// Thêm sự kiện click cho từng nút "Sửa"
editButtons.forEach(editButton => {
    editButton.addEventListener('click', function () {
        // Lấy ra dữ liệu của dòng tương ứng
        const row = this.closest('tr');
        const id = row.querySelector('td:nth-child(2)').innerText.trim(); // ID
        const username = row.querySelector('td:nth-child(3)').innerText.trim(); // Username
        const fullName = row.querySelector('td:nth-child(4)').innerText.trim(); // Họ và tên
        const gender = row.querySelector('td:nth-child(5)').innerText.trim(); // Giới tính
        const birthday = row.querySelector('td:nth-child(6)').innerText.trim(); // Ngày sinh
        const phone = row.querySelector('td:nth-child(7)').innerText.trim(); // Số điện thoại
        const email = row.querySelector('td:nth-child(8)').innerText.trim(); // Email
        const roleName = row.querySelector('td:nth-child(10)').innerText.trim(); // Role
        // Đổ dữ liệu vào các trường nhập trong modal popup
        document.querySelector('#ModalUP input[type="text"][name="id"]').value = id;
        document.querySelector('#ModalUP input[type="text"][name="username"]').value = username;
        document.querySelector('#ModalUP input[type="text"][name="fullName"]').value = fullName;
        document.querySelector('#ModalUP select[name="gender"]').value = gender == '' ? '' : (gender == 'Nam' ? 1 : 0);
        document.querySelector('#ModalUP input[type="date"][name="birthday"]').value = DateTime.fromFormat(birthday, 'dd/MM/yyyy').toFormat('yyyy-MM-dd');;
        document.querySelector('#ModalUP input[type="text"][name="phone"]').value = phone;
        document.querySelector('#ModalUP input[type="email"][name="email"]').value = email;
        document.querySelector('#ModalUP select[name="roleName"]').value = roleName == "ADMIN" ? 1 : (roleName == 'USER' ? 2 : 3);
    });
});
document.getElementById('btSave').addEventListener('click', async function () {
    // Hiển thị cảnh báo sử dụng SweetAlert
    swal({
        title: "Xác nhận",
        text: "Bạn có muốn lưu thông tin người dùng không?",
        icon: "warning",
        buttons: ["Hủy", "Lưu"],
        dangerMode: false,
    })
        .then(async (willSave) => {
            // Nếu người dùng nhấn nút "Lưu"
            if (willSave) {
                // Lấy dữ liệu từ các trường nhập trong modal popup
                const id = document.querySelector('#id').value;
                const username = document.querySelector('#username').value;
                const fullName = document.querySelector('#fullName').value;
                const gender = document.querySelector('#exampleSelect1').value;
                const birthday = document.querySelector('#birthday').value;
                const phone = document.querySelector('#phone').value;
                const email = document.querySelector('#email').value;
                const roleName = document.querySelector('#roleName').value;
                // Tạo đối tượng user từ dữ liệu thu thập được
                const user = {
                    id: id,
                    username: username,
                    fullName: fullName,
                    gender: gender,
                    birthday: birthday,
                    phone: phone,
                    email: email,
                    roleName: roleName
                };
                try {
                    // Gọi API để cập nhật thông tin người dùng
                    const response = await fetch('http://localhost:8080/api/v1/user', {
                        method: 'PATCH',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + accessToken // accessToken là biến chứa token xác thực
                        },
                        body: JSON.stringify(user)
                    });
                    if (response.ok) {
                        // Nếu cập nhật thành công, làm điều gì đó, ví dụ: thông báo hoặc làm mới danh sách người dùng
                        alert('Cập nhật thông tin người dùng thành công');
                        // Đoạn code để làm mới danh sách người dùng hoặc thực hiện các thao tác khác sau khi cập nhật thành công
                    } else {
                        // Nếu gặp lỗi khi gọi API
                        swal("Không thể thay đổi thông tin người dùng", {
                            icon: "info",
                            buttons: false,
                            timer: 1500,
                        });
                    }
                } catch (error) {
                    // Nếu xảy ra lỗi trong quá trình gọi API
                    console.error('Error:', error);
                    alert('Có lỗi xảy ra, vui lòng thử lại sau');
                }
            } else {
                // Người dùng nhấn nút "Hủy" => không làm gì cả
                swal("Bạn đã hủy thao tác", {
                    icon: "info",
                    buttons: false,
                    timer: 1500,
                });
            }
        });
});
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
function formatNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}
function toDate(dateString) {
    // Tách ngày, tháng và năm từ chuỗi đầu vào
    const parts = dateString.split('/');
    const day = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10) - 1; // Giảm đi 1 vì tháng bắt đầu từ 0
    const year = parseInt(parts[2], 10);
    // Tạo đối tượng Date từ các phần tử đã tách
    const date = new Date(year, month, day);
    return date;
}

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
