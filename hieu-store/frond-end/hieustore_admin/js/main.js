function validate() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password-field").value;

    // Đầu tiên kiểm tra xem các trường có trống không
    if (!username || !password) {
        swal({
            title: "",
            text: "Vui lòng nhập đủ thông tin đăng nhập!",
            icon: "error",
            close: true,
            button: "Thử lại",
        });
        return false;
    }

    login(username, password).then(response => {
        if (response.status === "SUCCESS" && response.data && response.data.accessToken) {
            // Kiểm tra quyền admin
            const isAdmin = response.data.authorities.some(auth => auth.authority === 'ROLE_ADMIN');
            if (!isAdmin) {
                swal({
                    title: "",
                    text: "Bạn không đủ quyền truy cập!",
                    icon: "error",
                    close: true,
                    button: "Thử lại",
                });
                return false;
            }

            // Lưu accessToken và chuyển hướng người dùng
            localStorage.setItem('accessToken', response.data.accessToken);
            window.location = "doc/user.html";
        } else {
            swal({
                title: "",
                text: "Sai thông tin đăng nhập hãy kiểm tra lại...",
                icon: "error",
                close: true,
                button: "Thử lại",
            });
        }
    }).catch(error => {
        console.error('Error:', error);
        swal({
            title: "",
            text: "Lỗi kết nối đến máy chủ hoặc sai tài khoản/mật khẩu",
            icon: "error",
            close: true,
            button: "Thử lại",
        });
    });

    return false; // Để ngăn chặn hành động submit mặc định của form
}
function login(username, password) {
    const url = 'http://localhost:8080/api/v1/auth/login';
    const data = {
        account: username,
        password: password
    };

    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .catch(error => {
            console.error('Error:', error);
            throw error; // Đẩy lỗi để bắt ở phía trên
        });
}