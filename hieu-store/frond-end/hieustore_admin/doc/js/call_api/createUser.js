document.getElementById("saveUserButton").addEventListener("click", async function () {
    const fullName = document.getElementById("fullName").value;
    const email = document.getElementById("email").value;
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const phone = document.getElementById("phone").value;

    const userData = {
        fullName,
        email,
        username,
        password,
        phone
    };

    try {
        const response = await fetch('http://localhost:8080/api/v1/user/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        });

        if (response.ok) {
            alert("Người dùng được tạo thành công!");
            // Thêm hành động sau khi tạo thành công, ví dụ: chuyển hướng người dùng
        } else {
            const errorData = await response.json();
            alert(`Lỗi tạo người dùng: ${errorData.message}`);
        }
    } catch (error) {
        console.error('Lỗi khi gọi API:', error);
        alert('Lỗi khi gọi API, xem console để biết chi tiết.');
    }
});