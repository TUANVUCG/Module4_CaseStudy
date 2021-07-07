$(document).ready(function () {
    $.ajax({
        type: "get",
        url: "/shopee/product",
        success: function (data) {
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += getProduct(data[i]);
            }
            document.getElementById('list-product').innerHTML = content;
        }
    })
});
$(document).ready(function () {
    $.ajax({
        type: "get",
        url: "/shopee/cart",
        success: function (data) {
            let content = `<h4 class="header__cart-heading">
                                Sản phẩm đã thêm
                            </h4> <ul class="header__cart-list-item">`;
            for (let i = 0; i < data.length; i++) {
                content += getItemsCart(data[i]);
            }
            document.getElementById('list-items').innerHTML = content + `</ul>
<a href="#" class="header__cart-view-cart btn btn--primary">Xem giỏ hàng</a>`;
            document.getElementById('quantity-items').innerText = data.length;
        }
    })
});

function getListItems(){
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
            }
            document.getElementById('list-items').innerHTML = content + `</ul><a href="#" class="header__cart-view-cart btn btn--primary">Xem giỏ hàng</a>`;
            document.getElementById('quantity-items').innerText = data.length;
        }
    })
}

$(document).ready(function () {
    //-- Click on detail
    $("ul.menu-items > li").on("click", function () {
        $("ul.menu-items > li").removeClass("active");
        $(this).addClass("active");
    })

    $(".attr,.attr2").on("click", function () {
        var clase = $(this).attr("class");

        $("." + clase).removeClass("active");
        $(this).addClass("active");
    })

})

function reduceQuantity() {
    let now = $(".section > div > input").val();
    if ($.isNumeric(now)) {
        if (parseInt(now) - 1 > 0) {
            now--;
        }
        $(".section > div > input").val(now);
    } else {
        $(".section > div > input").val("1");
    }
}

function increaseQuantity() {
    let now = $(".section > div > input").val();
    if ($.isNumeric(now)) {
        $(".section > div > input").val(parseInt(now) + 1);
    } else {
        $(".section > div > input").val("1");
    }
}

function getProduct(product) {
    return `<div class="col l-2-4 m-4 c-6">
                        <div class="home-product-item" id="${product.id}" onclick="getDetailProduct(this.id)">
                                    <div class="home-product-item__img"
                                         style="background-image: url(https://minhcaumart.vn/media/com_eshop/products/Sua-Tuoi-Tiet-Trung-Nguyen-Chat-Vinamilk-Khong-duong--1000ml-.jpg);">
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
                                    </div>
                            </div>`;
}

function getItemsCart(items) {
    return `


                                <li class="header__cart-item">
                                    <img src="https://minhcaumart.vn/media/com_eshop/products/Sua-Tuoi-Tiet-Trung-Nguyen-Chat-Vinamilk-Khong-duong--1000ml-.jpg" alt="" class="header__cart-img">

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
                                </li>

                            `
}

function getDetailProduct(id) {
    $.ajax({
        type: "get",
        url: "/shopee/product/" + id,
        success: function (data) {
            let content = `<div className="col-4 l-2 m-0 c-0"></div>`

            content += getProductById(data);
            document.getElementById("main-content").innerHTML = content;
            document.getElementById("bootstrap-css").href = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
        }

    })
}

function deleteItems(id){
    $.ajax({
        type: "delete",
        url: "/shopee/delete-items/"+ id,

        success: getListItems
    })
}

function getProductById(product) {
    return `<div class="row">
        <div class="col-xs-4 item-photo">
            <img style="max-width:100%;" src="https://minhcaumart.vn/media/com_eshop/products/Sua-Tuoi-Tiet-Trung-Nguyen-Chat-Vinamilk-Khong-duong--1000ml-.jpg" />
        </div>
        <div class="col-xs-5" style="border:0px solid gray">

        <h3>${product.name}</h3>
        <h5 style="color:#337ab7"><a href="#">${product.category.name}</a> <small style="color:#337ab7"></small></h5>


        <h6 class="title-price" style="text-decoration: line-through">${product.sellPrice}</h6>
        <h3 style="margin-top:0px; color: var(--primary-color)">${product.realPrice} <span>${product.sale}%</span></h3>



        <div class="section">
        <h6 class="title-attr" style="margin-top:15px;" ><small>COLOR</small></h6>
        <div>
        <div class="attr" style="width:25px;background:#5a5a5a;"></div>
        <div class="attr" style="width:25px;background:white;"></div>
        </div>
        </div>
        <div class="section" style="padding-bottom:5px;">
        <h6 class="title-attr"><small>CAPACIDAD</small></h6>
        <div>
        <div class="attr2">16 GB</div>
        <div class="attr2">32 GB</div>
        </div>
        </div>
        <div class="section" style="padding-bottom:20px;">
        <h6 class="title-attr"><small>CANTIDAD</small></h6>
        <div>
        <div class="btn-minus" onclick="reduceQuantity()"><span class="glyphicon glyphicon-minus"></span></div>
        <input value="1" />
        <div class="btn-plus" onclick="increaseQuantity()"><span class="glyphicon glyphicon-plus"></span></div>
        </div>
        </div>


        <div class="section" style="padding-bottom:20px;">
        <button class="btn btn--big btn--primary" id="add-items"><span style="margin-right:20px" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Thêm vào giỏ hàng</button>
        <button class="btn btn--big btn--primary"><span style="margin-right:20px" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Mua ngay</button>
        </div>
        </div>

        <div class="col-xs-9">
        <ul class="menu-items">
        <li class="active">Chi tiết sản phẩm</li>
        </ul>
        <div style="width:100%;border-top:1px solid silver">
        <p style="padding:15px;">
        <small>
        ${product.description}
        </small>
        </p>
        </div>
        </div>
        </div>`
}