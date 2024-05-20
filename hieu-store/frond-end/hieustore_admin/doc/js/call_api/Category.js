const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
    console.error('Access token not found');
}

document.addEventListener('DOMContentLoaded', async function () {
    const response = await fetch('http://localhost:8080/api/v1/category/all', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });

    if (response.ok) {
        const data = await response.json();
        const categories = data.data;

        let table = document.getElementById('categoryTable');
        let rowsHtml = ''; // Chuỗi để giữ HTML của tất cả các hàng
        let i = 1;
        categories.forEach(category => {
            rowsHtml += `
                <tr>
                    <td id = ${category.id}>${i++}</td>
                    <td hidden>${category.id}</td>
                    <td>${category.name}</td>                                
                    <td>${category.description == null ? '' : category.description}</td>
                    <td>${category.createdDate == null ? '' : category.createdDate}</td>
                    <td>${category.lastModifiedDate == null ? '' : category.lastModifiedDate}</td>
                    <td>${category.avatar == null ? '' : `<img src="${category.avatar}" width="100">`}</td>    
                    <td>
                        <button class="btn btn-primary btn-sm trash" type="button" title="Xóa"
                            id = ${category.id}><i class="fas fa-trash-alt"></i> 
                        </button>
                        <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp" data-toggle="modal"
                            data-target="#ModalUP"><i class="fas fa-edit"></i>
                        </button>                                       
                    </td>
                </tr>
            `;
        });

        table.innerHTML += rowsHtml; // Thêm tất cả hàng vào bảng


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
            const name = row.querySelector('td:nth-child(3)').innerText.trim(); // Username
            const description = row.querySelector('td:nth-child(4)').innerText.trim(); // Họ và tên      
            const imgSrc = row.querySelector('td:nth-child(7) img').getAttribute('src');
            // Đổ dữ liệu vào các trường nhập trong modal popup
            document.querySelector('#ModalUP input[type="text"][name="categoryId"]').value = id;
            document.querySelector('#ModalUP input[type="text"][name="name"]').value = name;
            document.querySelector('#ModalUP img[name="avatar"]').src = imgSrc;
            document.querySelector('#ModalUP textarea[name="description"]').value = description;
        });
    });
    document.querySelectorAll('.trash').forEach(button => {
        button.addEventListener('click', function () {
            const categoryId = this.getAttribute('id'); // Lấy ID người dùng từ thuộc tính id của nút
            swal({
                title: "Bạn có chắc không?",
                text: "Một khi bạn xóa, bạn sẽ không thể khôi phục lại thông tin này!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        deleteCategory(categoryId);
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

async function deleteCategory(categoryId) {
    const response = await fetch(`http://localhost:8080/api/v1/category/${categoryId}`, {
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
            text: "Xóa danh mục thất bại.",
            icon: "error",
            button: true, // Hiện nút để người dùng có thể đóng thông báo thủ công
            timer: 3000, // Thời gian hiển thị là 3 giây
            closeOnClickOutside: false, // Không cho phép đóng khi click bên ngoài
            closeOnEsc: false // Không cho phép đóng khi nhấn Esc
        });
    }
}

