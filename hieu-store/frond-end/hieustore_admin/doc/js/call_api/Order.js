const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
    console.error('Access token not found');
}

document.addEventListener('DOMContentLoaded', async function () {
    const response = await fetch('http://localhost:8080/api/v1/order/all', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });

    if (response.ok) {
        const data = await response.json();
        const orders = data.data.items;

        let table = document.getElementById('orderbody');
        let rowsHtml = ''; // Chuỗi để giữ HTML của tất cả các hàng
        let i = 0;
        orders.forEach(order => {
            rowsHtml += `
                <tr>
                    <td><id = ${order.id}>${++i}</td>
                    <td hidden>${order.id}</td>
                    <td>${order.customerName}</td>
                    <td>${order.phone}</td>
                    <td>${order.address}</td>
                    <td>${order.note == null ? '' : order.note}</td>
                    <td>${formatNumber(order.shippingFee)}</td>
                    <td>${formatNumber(order.originalPrice)}</td>
                    <td>${order.nulmoneyDiscountCodeIdl == null ? 'Không' : formatNumber(order.moneyDiscountCodeId)}</td>
                    <td>${formatNumber(order.totalPrice)}</td>
                    <td>${order.paymentStatus == true ? 'Đã thanh toán' : 'Thanh toán khi nhận hàng'}</td>
                    <td>${(order.statusName == 'Cancelled') ? 'Đã hủy' :
                    ((order.statusName == 'Pending') ? 'Chờ xác nhận' :
                        ((order.statusName == 'Delivering') ? 'Đang giao hàng' :
                            ((order.statusName == 'Watting') ? 'Đang chuẩn bị hàng' : 'Đang chuẩn bị hàng')))}</td>
                    <td>${order.lastModifiedDate == null ? '' : order.lastModifiedDate}</td>
                    <td>
                        <button class="btn btn-primary btn-sm trash" type="button" title="Xóa"
                            id = ${order.id}><i class="fas fa-trash-alt"></i>  </button>  
                        <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="${order.id}" data-toggle="modal"
                            data-target="#ModalUP"><i class="fas fa-edit"></i>    </button>  
                        <button class="btn btn-primary btn-sm detail" type="button" title="Chi tiết" id="${order.id}"> 
                            <a href="orderDetail2.html"> <i class="fas fa-info-circle"> </a></i>    </button>      
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

            // Đổ dữ liệu vào các trường nhập trong modal popup
            document.querySelector('#ModalUP input[type="text"][name="orderId"]').value = id;
        });
    });

    document.querySelectorAll('.detail').forEach(button => {
        button.addEventListener('click', function () {
            const orderId = this.getAttribute('id'); // Lấy ID người dùng từ thuộc tính id của nút
            localStorage.setItem('orderId',orderId);
        });
    });

    document.querySelectorAll('.trash').forEach(button => {
        button.addEventListener('click', function () {
            const orderId = this.getAttribute('id'); // Lấy ID người dùng từ thuộc tính id của nút
            swal({
                title: "Bạn có chắc không?",
                text: "Một khi bạn xóa, bạn sẽ không thể khôi phục lại thông tin này!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        deleteProductOption(orderId);
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


async function deleteProductOption(orderId) {
    const response = await fetch(`http://localhost:8080/api/v1/order/${orderId}`, {
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
            text: "Xóa đơn hàng thất bại.",
            icon: "error",
            button: true, // Hiện nút để người dùng có thể đóng thông báo thủ công
            timer: 3000, // Thời gian hiển thị là 3 giây
            closeOnClickOutside: false, // Không cho phép đóng khi click bên ngoài
            closeOnEsc: false // Không cho phép đóng khi nhấn Esc
        });
    }
}
