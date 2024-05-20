const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
    console.error('Access token not found');
}

document.addEventListener('DOMContentLoaded', async function () {
    const response = await fetch('http://localhost:8080/api/v1/news/all', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });

    if (response.ok) {
        const data = await response.json();
        const news = data.data.items;

        let table = document.getElementById('newBody');
        let rowsHtml = ''; // Chuỗi để giữ HTML của tất cả các hàng
        let i = 0;
        news.forEach(n => {
            rowsHtml += `
                <tr>
                    <td><id = ${n.id}>${++i}</td>
                    <td hidden>${n.id}</td>
                    <td>${n.title}</td>                               
                    <td>${n.summary}</td>   
                    <td>${n.content}</td>      
                    <td>${n.status == true ? 'Bật' : 'Tắt'}</td>          
                    <td>${n.categoryName}</td>    
                    <td>${n.avatar == null ? '' : `<img src="${n.avatar}" width="100">`}</td>    
                    <td hidden>${n.categoryId}</td>    
                    <td>
                        <button class="btn btn-primary btn-sm trash" type="button" title="Xóa"
                            id = ${n.id}><i class="fas fa-trash-alt"></i> 
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
        editButton.addEventListener('click', async function () {
            // Lấy ra dữ liệu của dòng tương ứng
            const row = this.closest('tr');
            const id = row.querySelector('td:nth-child(2)').innerText.trim(); // ID     
            
            const title = row.querySelector('td:nth-child(3)').innerText.trim();         
            const summary = row.querySelector('td:nth-child(4)').innerText.trim();
            const content = row.querySelector('td:nth-child(5)').innerText.trim(); // Họ và tên                
            const status = row.querySelector('td:nth-child(6)').innerText.trim();
            const categoryName = row.querySelector('td:nth-child(7)').innerText.trim();   
            const imgSrc = row.querySelector('td:nth-child(8) img').getAttribute('src');
            
            const categoryId = row.querySelector('td:nth-child(9)').innerText.trim();

            // Đổ dữ liệu vào các trường nhập trong modal popup
            document.querySelector('#ModalUP input[type="text"][name="newId"]').value = id;
            document.querySelector('#ModalUP textarea[name="content"]').value = content;
            document.querySelector('#ModalUP img[name="avatar"]').src = imgSrc;
            document.querySelector('#ModalUP textarea[name="title"]').value = title;
            document.querySelector('#ModalUP textarea[name="summary"]').value = summary;
            document.querySelector('#ModalUP select[name="status"]').value = (status == 'true') ? '1' : '0';
            document.querySelector('#ModalUP input[name="categoryName"]').value = categoryName;
            document.querySelector('#ModalUP input[name="categoryName"]').setAttribute('id', categoryId);

        });
    });
    document.querySelectorAll('.trash').forEach(button => {
        button.addEventListener('click', function () {
            const newsId = this.getAttribute('id'); // Lấy ID người dùng từ thuộc tính id của nút
            swal({
                title: "Bạn có chắc không?",
                text: "Một khi bạn xóa, bạn sẽ không thể khôi phục lại thông tin này!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        deleteNews(newsId);
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

async function deleteNews(newsId) {
    const response = await fetch(`http://localhost:8080/api/v1/news/${newsId}`, {
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
            text: "Xóa tin tức thất bại.",
            icon: "error",
            button: true, // Hiện nút để người dùng có thể đóng thông báo thủ công
            timer: 3000, // Thời gian hiển thị là 3 giây
            closeOnClickOutside: false, // Không cho phép đóng khi click bên ngoài
            closeOnEsc: false // Không cho phép đóng khi nhấn Esc
        });
    }
}
