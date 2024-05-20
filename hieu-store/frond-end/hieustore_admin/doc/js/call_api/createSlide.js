var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
  console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function () {
  const response2 = await fetch('http://localhost:8080/api/v1/product/all', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + accessToken
    }
  });
  if (response2.ok) {
    const data2 = await response2.json();
    const products = data2.data.items;

    let table = document.getElementById('productId');
    let rowsHtml = ''; // Chuỗi để giữ HTML của tất cả các hàng
    products.forEach(product => {
      rowsHtml += `
               <option value="${product.id}">${product.name}</option>
          `;
    });
    table.innerHTML += rowsHtml; // Thêm tất cả hàng vào bảng
  } else {
    console.error('Failed to fetch products');
  } 

  document.getElementById("saveSlideButton").addEventListener("click", async function () {
    const position = document.getElementById("position").value;
    const status = document.getElementById("status").value;
    const productId = document.getElementById("productId").value;
    const description = document.getElementById("description").value;
    const avatar = document.getElementById("avatarFileInput").files[0];

    const formData = new FormData();
    formData.append("position", position);
    formData.append("status", status);
    formData.append("productId", productId);
    formData.append("avatar", avatar);
    formData.append("description", description);
    try {
      const response = await fetch('http://localhost:8080/api/v1/slide/create', {
        method: 'POST',
        headers: {
          'Authorization': 'Bearer ' + accessToken,
        },
        body: formData,
      });
      if (response.ok) {
        swal({
          title: "",
          text: "Tạo slide thành công!",
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
        const errorData = await response.json();
        swal({
          title: "Thất bại!",
          text: "Tạo slide thất bại thất bại:" + errorData.message,
          icon: "error",
          button: true, // Hiện nút để người dùng có thể đóng thông báo thủ công
          timer: 3000, // Thời gian hiển thị là 3 giây
          closeOnClickOutside: false, // Không cho phép đóng khi click bên ngoài
          closeOnEsc: false // Không cho phép đóng khi nhấn Esc
        });
      }
    } catch (error) {
      console.error('Lỗi khi gọi API:', error);
      alert('Lỗi khi gọi API, xem console để biết chi tiết.');
    }
  });
});



