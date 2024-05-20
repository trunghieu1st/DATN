var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
    console.error('Access token not found');
}

document.getElementById("btSave").addEventListener("click", async function () {
  const name = document.getElementById("name").value;
  const avatar = document.getElementById("avatarFileInput").files[0]; // Lấy file đầu tiên
  const description = document.getElementById("description").value;

  const formData = new FormData();
  formData.append("name", name);
  formData.append("avatar", avatar);
  formData.append("description", description);

  try {
    const response = await fetch('http://localhost:8080/api/v1/category/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + accessToken
      },
      body: formData, // Gửi formData; không cần thiết định rõ Content-Type vì FormData tự động làm việc đó
    });

    if (response.ok) {
      alert("Danh mục được tạo thành công!");
      // Thêm hành động sau khi tạo thành công, ví dụ: chuyển hướng người dùng
    } else {
      const errorData = await response.json();
      alert(`Lỗi tạo danh mục: ${errorData.message}`);
    }
  } catch (error) {
    console.error('Lỗi khi gọi API:', error);
    alert('Lỗi khi gọi API, xem console để biết chi tiết.');
  }
});