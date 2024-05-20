const baseApiUrl = "http://localhost:8080/api/v1";
export const apiPaths = {
    //Slide
    getAllSlides: `${baseApiUrl}/slide/all`,
    createSlide: `${baseApiUrl}/slide/create`,
    deleteSlide: `${baseApiUrl}/slide`,
    //Category
    getAllCategories: `${baseApiUrl}/category/all`,

    //Product Option
    getAllProductOption: `${baseApiUrl}/product/option/allProduct`,
    createCart: `${baseApiUrl}/cart/create`,
    login: `${baseApiUrl}/auth/login`,
    myOder: `${baseApiUrl}/order/all/my`,
    myOderDetail: `${baseApiUrl}/order-detail/all`,
    addToCart: `${baseApiUrl}/cart/create`,
    getAllCart: `${baseApiUrl}/cart/all`,
    // Thêm các đường dẫn API khác vào đây
};

//Hướng dẫn khia báo tron gteejp js: import { apiPaths } from "./url.js"