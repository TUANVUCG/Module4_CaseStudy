// Product
$(document).ready(function () {
    $.ajax({
        type: "get",
        url: "/shopee/product",
        success: function (data) {
            getListPagination(data)
        }

    })
})

function getPagination(pageSize) {
    return `<li class="pagination-item" id="pagination-${pageSize}">
                            <span class="pagination-item__link" onclick="changePagination(${pageSize})">
                                ${pageSize}
                            </span>
                        </li>`
}

function findProductByCategory(category) {
    $.ajax({
        type: "get",
        url: "/shopee/product/category/" + category,
        success: function (data) {
            getListPagination(data)
        }

    })
}


function changePagination(page) {
    let p = page - 1
    $.ajax({
        type: "get",
        url: "/shopee/product/page/" + p,
        success: function (data) {
            getListPagination(data, page)
        }
    })
}

function getListPagination(data, page) {
    let content = "";
    let pagination = `<li class="pagination-item">
                            <a href="" class="pagination-item__link">
                                <i class="pagination-item__icon fas fa-chevron-left"></i>
                            </a>
                        </li>`;
    for (let i = 0; i < data.content.length; i++) {
        content += getProduct(data.content[i]);
    }
    for (let i = 0; i < data.totalPages; i++) {
        pagination += getPagination(i + 1);
    }
    document.getElementById('list-product').innerHTML = content;
    document.getElementById('pagination').innerHTML = pagination + `<li class="pagination-item">
                            <a href="" class="pagination-item__link">
                                <i class="pagination-item__icon fas fa-chevron-right"></i>
                            </a>
                        </li>`;
    document.getElementById("pagination-" + page).classList.add('pagination-item--active')
}

function getProduct(product) {
    return `<div class="col l-2-4 m-4 c-6">
                        <a class="home-product-item" id="${product.id}" href="/shopee/view/${product.id}">
                                    <div class="home-product-item__img"
                                         style="background-image: url(${product.img});">
                                    </div>

                                    <h4 class="home-product-item__name">
                                         ${product.name}
                                    </h4>

                                    <div class="home-product-item__price">
                                        <span class="home-product-item__price-old">${product.sellPrice}đ</span>
                                        <span class="home-product-item__price-current">${product.realPrice}đ</span>
                                    </div>

                                    <div class="home-product-item__action">
                                            <span class="home-product-item__like home-product-item__like--liked">
                                                <i class="home-product-item__like-icon-empty far fa-heart"></i>
                                                <i class="home-product-item__like-icon-fill fas fa-heart"></i>
                                            </span>

                                        <div class="home-product-item__rating">
                                            <i class="home-product-item__star-gold fas fa-star"></i>
                                            <i class="home-product-item__star-gold fas fa-star"></i>
                                            <i class="home-product-item__star-gold fas fa-star"></i>
                                            <i class="home-product-item__star-gold fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                        </div>

                                        <span class="home-product-item__sold">${product.sold} đã bán</span>

                                    </div>

                                    <div class="home-product-item__origin">
                                        <span class="home-product-item__brand">Hà Nội</span>
                                        <span class="home-product-item__origin-name">Việt Nam</span>
                                    </div>

                                    <div class="home-product-item__favourite">
                                        <i class="fas fa-check"></i>
                                        <span>Yêu Thích</span>
                                    </div>

                                    <div class="home-product-item__sale-off">
                                        <span class="home-product-item__sale-off-percent">${product.sale}%</span>
                                        <span class="home-product-item__sale-off-label">Giảm</span>
                                    </div>
                                    </a>
                            </div>`;
}


// Items
$(document).ready(function () {
    $.ajax({
        type: "get",
        url: "/shopee/cart",
        success: function (data) {
            let content = `<h4 class="header__cart-heading">
                                Sản phẩm đã thêm
                            </h4> <ul class="header__cart-list-item">`;
            if (data.length == 0) {

                content = `<img src="../img/no__cart.jpg" alt=""
                    class="header__cart-no-cart-img">
    
                    <span class="header__cart-list-no-cart-msg">Chưa có sản phẩm</span>`
                document.getElementById('list-items').innerHTML = content;
                document.getElementById('quantity-items').innerText = 0;

            } else {
                for (let i = 0; i < data.length; i++) {
                    content += getItemsCart(data[i]);
                }
                document.getElementById('list-items').innerHTML = content + `</ul>
                    <a href="/shopee/order" class="header__cart-view-cart btn btn--primary">Xem giỏ hàng</a>`;
                document.getElementById('quantity-items').innerText = data.length;
            }


        }
    })
});

function getListItems() {
    $.ajax({
        type: "get",
        url: "/shopee/cart",
        success: function (data) {

            let content = `<h4 class="header__cart-heading">
                                Sản phẩm đã thêm
                            </h4>
                            <ul class="header__cart-list-item">`;
            for (let i = 0; i < data.length; i++) {
                content += getItemsCart(data[i]);

                document.getElementById('list-items').innerHTML = content + `</ul><a href="/shopee/order" class="header__cart-view-cart btn btn--primary">Xem giỏ hàng</a>`;
                document.getElementById('quantity-items').innerText = data.length;
            }

        }
    })
}

function getItemsCart(items) {
    return `
                                  <li class="header__cart-item">
                                    <img src="${items.product.img}" alt="" class="header__cart-img">

                                    <div class="header__cart-item-info">

                                        <div class="header__cart-item-head">

                                            <h5 class="header__cart-item-name">${items.product.name}</h5>
                                            <div class="header__cart-item-price-wrap">
                                                <span class="header__cart-item-price">${items.product.sellPrice}</span>
                                                <span class="header__cart-item-multiply">x</span>
                                                <span class="header__cart-item-qnt">${items.quantity}</span>
                                            </div>

                                        </div>

                                        <div class="header__cart-item-body">
                                                <span class="header__cart-item-description">
                                                    Phân loại: ${items.product.category.name}
                                                </span>
                                            <span class="header__cart-item-remote" id="${items.id}" onclick="deleteItems(this.id)">
                                                    Xóa
                                                </span>
                                        </div>

                                    </div>
                                </li>`
}

function deleteItems(id) {
    $.ajax({
        type: "delete",
        url: "/shopee/delete-items/" + id,

        success: getListItems
    })
}

// Button view
// $(document).ready(function () {
//     //-- Click on detail
//     $("ul.menu-items > li").on("click", function () {
//         $("ul.menu-items > li").removeClass("active");
//         $(this).addClass("active");
//     })

//     $(".attr,.attr2").on("click", function () {
//         var clase = $(this).attr("class");

//         $("." + clase).removeClass("active");
//         $(this).addClass("active");
//     })

// })

// function reduceQuantity() {
//     let now = $(".section > div > input").val();
//     if ($.isNumeric(now)) {
//         if (parseInt(now) - 1 > 0) {
//             now--;
//         }
//         $(".section > div > input").val(now);
//     } else {
//         $(".section > div > input").val("1");
//     }
// }

// function increaseQuantity() {
//     let now = $(".section > div > input").val();
//     if ($.isNumeric(now)) {
//         $(".section > div > input").val(parseInt(now) + 1);
//     } else {
//         $(".section > div > input").val("1");
//     }
// }

// Category
$(document).ready(function () {
    $.ajax({
        type: "get",
        url: "/shopee/category",
        success: function (data) {
            let content = "";

            for (let i = 0; i < data.length; i++) {
                content += getListCategory(data[i]);

            }
            document.getElementById('list-category').innerHTML = content;
        }
    })
});

function getListCategory(category) {
    return `<li class="category-item category-item--active" >
        <span class="category-item__link" onclick="findProductByCategory('${category.name}')">
            ${category.name}
        </span>
    </li>`
}

function showAllProductDesc() {
    $.ajax({
        type: "get",
        url: "/products/desc",
        success: function (data) {
            let content = "";
            for(let i = 0; i < data.content.length; i++){
                content += getProduct(data.content[i]);
            }
            document.getElementById('list-product').innerHTML = content;
        }
    })
}
