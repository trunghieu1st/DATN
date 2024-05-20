// const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
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
  let option = document.getElementById('productId');
  if (response2.ok) {
    const dt = await response2.json();
    const its = dt.data.items;
    its.forEach(it => {
      option.innerHTML += `<option value = "${it.id}">${it.name}</option>`;
    })
  }

  const ram = document.getElementById('ram');
  const storageCapacity = document.getElementById('storageCapacity');
  const color = document.getElementById('color');
  const price = document.getElementById('price');
  const quantity = document.getElementById('quantity');
  const status = document.getElementById('status');
  const avatar = document.getElementById('avatar');
  const avatarFileInput = document.getElementById('avatarInput');
  const productId = document.getElementById('productId');

  avatarFileInput.addEventListener('change', function () {
    const file = avatarFileInput.files[0]; // Lấy file đầu tiên từ danh sách các files đã chọn
    const filePath = URL.createObjectURL(file); // Tạo đường dẫn tạm thời cho tệp
    avatar.setAttribute('src', filePath);
    console.log(filePath);
  });

  document.getElementById('saveProductButton').addEventListener('click', async function () {
    const formData = new FormData();
    formData.append('ram', ram.value);
    formData.append('storageCapacity', storageCapacity.value);
    formData.append('color', color.value);
    formData.append('price', price.value);
    formData.append('quantity', quantity.value);
    formData.append('status', status.value);
    formData.append('productId', productId.value);
    const file = avatarFileInput.files[0];
    if (file) {
      formData.append('image', file);
    }

    fetch('http://localhost:8080/api/v1/product/option/create', {
      method: 'POST',
      headers: {
        'Authorization': 'Bearer ' + accessToken,
      },
      body: formData,
    })
      .then(response => {
        if (response.ok) {
          return response.json();
        }
        throw new Error('Network response was not ok.');
      })
      .then(data => {
        console.log(data);
        alert('Sản phẩm đã được tạo thành công!');
        // Có thể thực hiện thêm các hành động, như làm mới trang hoặc cập nhật UI
      })
      .catch(error => {
        console.error('Có lỗi xảy ra:', error);
        alert('Có lỗi xảy ra khi tạo sản phẩm.');
      });
  });
});

