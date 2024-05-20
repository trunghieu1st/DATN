const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');
if (!accessToken) {
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function () {
    const orderId = localStorage.getItem('orderId');
    console.log(orderId);
    document.querySelector('.tile-title').innerHTML += ' ' +orderId;
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
                let sum =0;
                order_details.forEach(od => {
                    rowsHtml += `
                            <tr>                                
                                <td><id="${od.id}">${++i}</td>
                                <td hidden>${od.orderId}</td>
                                <td>${od.productOptionDto.productName}</td>
                                <td>${od.productOptionDto.ram}</td>
                                <td>${od.productOptionDto.storageCapacity}</td>
                                <td>${od.productOptionDto.color}</td>                               
                                <td>${formatNumber(od.productOptionDto.price)}</td>
                                <td>${od.quantity}</td>
                                <td>${formatNumber(od.quantity * od.price)}</td>
                                <td>${od.productOptionDto.image == null ? '' : `<img src="${od.productOptionDto.image}" width="100">`}</td>                          
                            </tr>
                        `;

                    sum+= od.quantity * od.productOptionDto.price;
                });


                table.innerHTML += rowsHtml; // Thêm tất cả hàng vào bảng
                document.querySelector('#orderId').value = orderId;
                document.querySelector('#sumPrice').value = formatNumber(sum) + ' VNĐ';
                
            }

        });

    } else {
        console.error('Failed to fetch products');
    }
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
