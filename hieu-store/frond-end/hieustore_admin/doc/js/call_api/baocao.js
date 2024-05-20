const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
    console.error('Access token not found');
}

document.addEventListener('DOMContentLoaded', async function () {
    ////////////////////////////////////Tổng khách hàng
    const res01 = await fetch('http://localhost:8080/api/v1/user/all' + '?pageNum=1&pageSize=100', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });
    if (res01.ok) {
        const data01 = await res01.json();
        const users = data01.data.items;
        document.getElementById('countUser').innerText = users.length + ' khách hàng';
        const userTable = document.getElementById('userTable');
        let rowsHtml = '';
        for(let i=0;i<=4;i++){
            rowsHtml += `
            <tr>
                <td>${users[i].id}</td>
                <td>${users[i].fullName == null ? 'Chưa cập nhật' : users[i].fullName }</td>
                <td>${users[i].birthday == null ? 'Chưa cập nhật' : users[i].birthday}</td>
                <td>${users[i].gender == null ? 'Chưa cập nhật' : users[i].gender}</td>
                <td>${users[i].phone}</td>
                <td>${users[i].email}</td>
             </tr>
        `;    
        }
        userTable.innerHTML += rowsHtml;

    } else {
        console.error('Failed to fetch products');
    }


    //////////////////////////////////Tổng sản phẩm
    const res02 = await fetch('http://localhost:8080/api/v1/product/option/allProduct', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });
    if (res02.ok) {
        const data02 = await res02.json();
        const products = data02.data;
        let sumProduct = 0;
        // Đếm xem có hết hàng không
        let outOfStock = 0;
        products.forEach(pro => {
            sumProduct += pro.quantity;
            if (pro.quantity == 0) {
                outOfStock++;
            }
        });
        document.getElementById('countProduct').innerText = sumProduct + ' sản phẩm';

        //////////////////////////////////Hết hàng
        document.getElementById('outOfStock').innerText = outOfStock + ' sản phẩm';

    } else {
        console.error('Failed to fetch products');
    }

    //////////////////////////////////Tổng đơn hàng
    const res03 = await fetch('http://localhost:8080/api/v1/order/all', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });
    if (res03.ok) {
        const data03 = await res03.json();
        const orders = data03.data.items;
        document.getElementById('countOder').innerText = orders.length + ' đơn hàng';

        let sumPrice = 0; //Tổng thu nhập
        let cancelled = 0; //Đơn hủy
        const orderTable = document.getElementById('sumOrder');
        let rowsHtml = '';
        orders.forEach(pro => {
            sumPrice += pro.totalPrice;
            if(pro.statusName == 'Cancelled'){
                cancelled++;
            }
   
            rowsHtml += `
                <tr>
                    <td>${pro.id}</td>
                    <td>${pro.customerName}</td>
                    <td>${pro.address}</td>
                    <td>${formatNumber(pro.totalPrice)} đ</td>
                 </tr>
            `;              
        });
        rowsHtml += `<tr>
                    <th colspan="3">Tổng cộng:</th>
                    <td> ${formatNumber(sumPrice)} đ</td>
                </tr>`;
        orderTable.innerHTML += rowsHtml;
        document.getElementById('sumPrice').innerText = formatNumber(sumPrice) + ' đ';

        //Đơn bị hủy
        document.getElementById('cancelled').innerText = cancelled + ' đơn hàng';

    } else {
        console.error('Failed to fetch products');
    }

     //////////////////////////////////Khách hàng mới
    //  const res04 = await fetch('http://localhost:8080/api/v1/order/all', {
    //     method: 'GET',
    //     headers: {
    //         'Content-Type': 'application/json',
    //         'Authorization': 'Bearer ' + accessToken
    //     }
    // });
    // if (res04.ok) {
    //     const data04 = await res04.json();
    //     const users = data04.data.items;
    //     document.getElementById('countOder').innerText = orders.length + ' đơn hàng';

    //     let sumPrice = 0; //Tổng thu nhập
    //     let cancelled = 0; //Đơn hủy
    //     const orderTable = document.getElementById('sumOrder');
    //     let rowsHtml = '';
    //     orders.forEach(pro => {
    //         sumPrice += pro.totalPrice;
    //         if(pro.statusName == 'Cancelled'){
    //             cancelled++;
    //         }
   
    //         rowsHtml += `
    //             <tr>
    //                 <td>${pro.id}</td>
    //                 <td>${pro.customerName}</td>
    //                 <td>${pro.address}</td>
    //                 <td>${formatNumber(pro.totalPrice)} đ</td>
    //              </tr>
    //         `;              
    //     });
    //     rowsHtml += `<tr>
    //                 <th colspan="3">Tổng cộng:</th>
    //                 <td> ${formatNumber(sumPrice)} đ</td>
    //             </tr>`;
    //     orderTable.innerHTML += rowsHtml;
    //     document.getElementById('sumPrice').innerText = formatNumber(sumPrice) + ' đ';

    //     //Đơn bị hủy
    //     document.getElementById('cancelled').innerText = cancelled + ' đơn hàng';

    // } else {
    //     console.error('Failed to fetch products');
    // }


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

