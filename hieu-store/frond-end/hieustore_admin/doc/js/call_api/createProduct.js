// const { DateTime } = luxon;
// Lấy thông tin cá nhân từ API khi trang được tải
var accessToken = localStorage.getItem('accessToken');

if (!accessToken) {
  console.error('Access token not found');
}
document.addEventListener('DOMContentLoaded', async function () {
  const response2 = await fetch('http://localhost:8080/api/v1/category/all', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + accessToken
    }
  });
  let option = document.getElementById('categoryId');
  if (response2.ok) {
    const dt = await response2.json();
    const its = dt.data;
    its.forEach(it => {
      option.innerHTML += `<option value = "${it.id}">${it.name}</option>`;
    })
  }
  document.getElementById('saveProductButton').addEventListener('click', async function () {

    const name = document.getElementById('name');
    const screenTechnology = document.getElementById('screenTechnology');
    const screenResolution = document.getElementById('screenResolution');
    const widescreen = document.getElementById('widescreen');
    const scanFrequency = document.getElementById('scanFrequency');
    const rearCamera = document.getElementById('rearCamera');
    const frontCamera = document.getElementById('frontCamera');
    const operationSystem = document.getElementById('operationSystem');
    const cpu = document.getElementById('cpu');
    const cpuSpeed = document.getElementById('cpuSpeed');
    const graphicChip = document.getElementById('graphicChip');
    const mobileNetwork = document.getElementById('mobileNetwork');
    const sim = document.getElementById('sim');
    const wifi = document.getElementById('wifi');
    const gps = document.getElementById('gps');
    const bluetooth = document.getElementById('bluetooth');
    const headphoneJack = document.getElementById('headphoneJack');
    const chargingPort = document.getElementById('chargingPort');
    const batteryCapacity = document.getElementById('batteryCapacity');
    const batteryType = document.getElementById('batteryType');
    const chargingSupport = document.getElementById('chargingSupport');
    const material = document.getElementById('material');
    const weight = document.getElementById('weight');
    const size = document.getElementById('size');
    const launchDate = document.getElementById('launchDate');
    const supplier = document.getElementById('supplier');
    const avatarFileInput = document.getElementById('avatar');
    const categoryId = document.getElementById('categoryId');
    const file = avatarFileInput.files[0];

    const formData = new FormData();
    formData.append('name', name.value);
    formData.append('screenTechnology', screenTechnology.value);
    formData.append('screenResolution', screenResolution.value);
    formData.append('widescreen', widescreen.value);
    formData.append('scanFrequency', scanFrequency.value);
    formData.append('rearCamera', rearCamera.value);
    formData.append('frontCamera', frontCamera.value);
    formData.append('operationSystem', operationSystem.value);
    formData.append('cpu', cpu.value);
    formData.append('cpuSpeed', cpuSpeed.value);
    formData.append('graphicChip', graphicChip.value);
    formData.append('mobileNetwork', mobileNetwork.value);
    formData.append('sim', sim.value);
    formData.append('wifi', wifi.value);
    formData.append('gps', gps.value);
    formData.append('bluetooth', bluetooth.value);
    formData.append('headphoneJack', headphoneJack.value);
    formData.append('chargingPort', chargingPort.value);
    formData.append('batteryCapacity', batteryCapacity.value);
    formData.append('batteryType', batteryType.value);
    formData.append('chargingSupport', chargingSupport.value);
    formData.append('material', material.value);
    formData.append('weight', weight.value);
    formData.append('size', size.value);
    formData.append('launchDate', launchDate.value);
    formData.append('supplier', supplier.value);
    formData.append('categoryId', categoryId.value);
    if (file) {
      formData.append('avatar', file);
    }

    fetch('http://localhost:8080/api/v1/product/create', {
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

