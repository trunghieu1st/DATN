var accessToken = localStorage.getItem('accessToken');
if (!accessToken) {
    console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function () {
    const response2 = await fetch('http://localhost:8080/api/v1/user/my', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });
    if (response2.ok) {
        const dt = await response2.json();
        document.getElementById('name').value = dt.data.fullName;
        document.getElementById('phone').value = dt.data.phone;
        document.getElementById('email').value = dt.data.email;
        document.getElementById('address').value = localStorage.getItem('address');

    }
    function formatNumber(number) {
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }
    const response = await fetch('http://localhost:8080/api/v1/cart/all', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    });
    if (response.ok) {
        const data = await response.json();
        const cards = data.data.items;
        const shipIdea = document.getElementById('itemCard');
        var sumTotal = 0;
        cards.forEach(card => {
            var productHTML = `
                <tr class="cart_item" style="border: 1 !important;">
                    <td class="product-total"> 
                        ${card.productOptionDto.productName} (
                        ${card.productOptionDto.ram}G RAM 
                        ${card.productOptionDto.storageCapacity}G Memory 
                        ${card.productOptionDto.color})                     
                    </td>
                    <td class="product-total">                         
                       ${card.quantity}
                    </td>
                    <td class="product-total">                         
                        ${formatNumber(card.productOptionDto.price)} VNĐ
                    </td>
                    <td class="product-total">
                      ${formatNumber(card.quantity * card.productOptionDto.price)} VNĐ 
                    </td>
                   
                </tr>`;
            sumTotal += card.quantity * card.productOptionDto.price;
            shipIdea.insertAdjacentHTML('afterbegin', productHTML);
        });
        document.getElementById('sum-total').textContent = formatNumber(sumTotal);
        document.getElementById('amount').textContent = formatNumber(sumTotal + sumTotal / 1000);
        document.getElementById('ship-total').textContent = formatNumber(sumTotal / 1000);
    } else {
        console.error('Failed to fetch products');
    }

    document.getElementById('order').addEventListener('click', async function () {
        const res = await fetch('http://localhost:8080/api/v1/cart/all', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + accessToken
            }
        });
        const data3 = await res.json();
        const cards3 = data3.data.items;
        const orderProductRequestDtos = [];
        cards3.forEach(card => {
            const productOptionId = card.productOptionDto.id;
            const quantity = card.quantity;

            // Thêm đối tượng mới vào mảng orderProductRequestDtos
            orderProductRequestDtos.push({ productOptionId: productOptionId, quantity: quantity});
        });
        console.log(orderProductRequestDtos);
        const response3 = await fetch('http://localhost:8080/api/v1/order/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + accessToken
            },
            body: JSON.stringify({
                addressId: localStorage.getItem('addressId'),
                orderProductRequestDtos: orderProductRequestDtos,
                note: document.getElementById('note').value == '' ? "Không có ghi chú" : document.getElementById('note').value,
                shippingFee: 10000,
                paymentStatus: false
            })
        });

        if (response3.ok) {
            window.location.href = 'thanks.html';
        }
    });

});
