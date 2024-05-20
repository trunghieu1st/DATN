package com.example.techstore.constant;

public class UrlConstant {

    public static class Auth {
        private static final String PRE_FIX = "/auth";
        public static final String LOGIN = PRE_FIX + "/login";
        public static final String LOGOUT = PRE_FIX + "/logout";
        public static final String SEND_VERIFY = PRE_FIX + "/send";
        public static final String VERIFY = PRE_FIX + "/verify";

        private Auth() {
        }
    }

    public static class User {
        private static final String PRE_FIX = "/user";
        public static final String GET_BY_ID = PRE_FIX + "/{userId}";
        public static final String GET_CURRENT_USER = PRE_FIX + "/my";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX;
        public static final String CHANGE_PASSWORD = PRE_FIX + "/change/password";
        public static final String CREATE_NEW_PASSWORD = PRE_FIX + "/create/new-password";
        public static final String DELETE = PRE_FIX + "/{userId}";

        private User() {
        }
    }

    public static class Address {
        private static final String PRE_FIX = "/address";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String GET_ALL_BY_USE_ID = PRE_FIX + "/all/{userId}";
        public static final String GET_DEFAULT = PRE_FIX + "/default";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE_BY_ID = PRE_FIX + "/{id}";
        public static final String CHANGE_DEFAULT_BY_ID = PRE_FIX + "/default/{id}";
        public static final String DELETE_BY_ID = PRE_FIX + "/{id}";

        public Address() {
        }
    }

    public static class Category {
        private static final String PRE_FIX = "/category";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        private Category() {
        }
    }

    public static class News {
        private static final String PRE_FIX = "/news";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_BY_STATUS = PRE_FIX + "/status";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        private News() {
        }
    }

    public static class Product {
        private static final String PRE_FIX = "/product";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String SEARCH = PRE_FIX + "/search";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        // OPTION
        public static final String GET_OPTION_BY_ID = PRE_FIX + "/option/{id}";
        public static final String GET_ALL_OPTION = PRE_FIX + "/option/all/{productId}";
        public static final String GET_ALL_PRODUCT = PRE_FIX + "/option/allProduct";
        public static final String CREATE_OPTION = PRE_FIX + "/option/create";
        public static final String UPDATE_OPTION = PRE_FIX + "/option/{id}";
        public static final String DELETE_OPTION = PRE_FIX + "/option/{id}";

        private Product() {
        }
    }

    public static class Cart {
        private static final String PRE_FIX = "/cart";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String GET_NUMBER_OF_ITEM = PRE_FIX + "/items";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        private Cart() {
        }
    }

    public static class Slide {
        private static final String PRE_FIX = "/slide";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_BY_STATUS = PRE_FIX + "/status";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        private Slide() {
        }
    }

    public static class DiscountCode {
        private static final String PRE_FIX = "/discount-code";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        private DiscountCode() {
        }
    }

    public static class UserDiscount {
        private static final String PRE_FIX = "/user-discount";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String GET_BY_USER_ID = PRE_FIX + "/all/me";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String ADD = PRE_FIX + "/add";
        public static final String DELETE = PRE_FIX + "/{id}";

        private UserDiscount() {
        }
    }

    public static class Order {
        private static final String PRE_FIX = "/order";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String GET_MY_ALL = PRE_FIX + "/all/my";
        public static final String CREATE = PRE_FIX + "/create";

        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        private Order() {
        }
    }

    public static class OrderDetail {
        private static final String PRE_FIX = "/order-detail";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all/{orderId}";

        private OrderDetail() {
        }
    }

    public static class Review {
        private static final String PRE_FIX = "/review";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX + "/create";

        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        private Review() {
        }
    }

    public static class Room {
        private static final String PRE_FIX = "/room";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        private Room() {
        }
    }

    public static class Message {
        private static final String PRE_FIX = "/message";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        private Message() {
        }
    }

    public static class File {
        private static final String PRE_FIX = "/file";
        public static final String GET_BY_ID = PRE_FIX + "/{id}";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX + "/{id}";
        public static final String DELETE = PRE_FIX + "/{id}";

        private File() {
        }
    }

}
