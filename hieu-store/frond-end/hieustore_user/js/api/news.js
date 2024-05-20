import { apiPaths } from "./url.js"
// Lấy thông tin cá nhân từ API khi trang được tải
document.addEventListener('DOMContentLoaded', async function () {

    // Sử dụng productId trong URL của yêu cầu fetch
    const response = await fetch(`http://localhost:8080/api/v1/news/all`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',     
        }
    });
   
    if (response.ok) {

        const newsShow = document.querySelector('.news-container');

        const data = await response.json();
        const news = data.data.items;

        news.forEach(n => {
            var newsHTML = `
                    <div class="news-items">
                        <img src="${n.avatar}" alt="Ảnh tin tức">
                        <div class="news-content">
                            <h2 class="news-title"><a>${n.title}</a></h2>
                            <p class="news-description">${n.content}</p>
                            <p class="news-description">${n.summary}.</p>
                        </div>
                    </div>
                `;
            newsShow.insertAdjacentHTML('beforeend', newsHTML);
        });
    } else {
        console.error('Failed to fetch product information');
    }
});

function formatNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}