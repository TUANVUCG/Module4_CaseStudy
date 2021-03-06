$(document).ready(function(){
    $.ajax({
        type: "post",
        url: location.pathname,
        success: function (data) {
            let content = `<div className="col-4 l-2 m-0 c-0"></div>`
            content += getProductById(data);
            document.getElementById("detail-product").innerHTML = content;
        }

    })
})

function getProductById(product) {
    return `<div class="row">
        <div style="margin-top:50px">
        <div class="col-xs-4 item-photo">
            <img style="max-width:100%;" src="${product.img}" />
        </div>
        <div class="col-xs-5" style="border:0px solid gray">

        <h3 id="product-name">${product.name}</h3>
        <h5 style="color:#337ab7"><a href="#">${product.category.name}</a> <small style="color:#337ab7"></small></h5>
        <div style="display: flex">
        <h6 class="title-price" style="text-decoration: line-through; padding:0 10px">${product.sellPrice}đ</h6>
        <h3 style="margin-top:0px; color: var(--primary-color)" id="product-price">${product.realPrice}đ</h3>
        <span style="color: var(--primary-color); padding: 0 10px">Giảm${product.sale}%</span>
        </div>
        <div class="section">
        <h6 class="title-attr" style="margin-top:15px;" ><small></small></h6>
        <div>
        <div class="attr" style="width:25px;background:#5a5a5a;"></div>
        <div class="attr" style="width:25px;background:white;"></div>
        </div>
        </div>
        <div class="section" style="padding-bottom:5px;">
        <h6 class="title-attr" id="quantity" style="color: var(--primary-color)">Còn ${product.quantity} sản phẩm</h6>
        <div>
        <div class="attr2"></div>
        <div class="attr2"></div>
        </div>
        </div>
        <div class="section" style="padding-bottom:20px;">
        <h6 class="title-attr">Đã bán: ${product.sold}</h6>
        <div>
        <div style="display: flex">
        <div class="btn--small btn" onclick="reduceQuantity()"><span class="glyphicon glyphicon-minus"></span></div>
        <input value="1" id="product-quantity" style="border:1px solid var(--primary-color); width: 40px;outline: var(--primary-color); text-align: center"/>
        <div class="btn--small btn" onclick="increaseQuantity()"><span class="glyphicon glyphicon-plus"></span></div></div>
        </div>
        </div>


        <div class="section" style="padding-bottom:20px;">
        <button class="btn btn--big btn--primary" id="add-items" onclick=addItems()><span style="margin-right:20px" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Thêm vào giỏ hàng</button>
        <button class="btn btn--big btn--primary"><span style="margin-right:20px" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Mua ngay</button>
        </div>
        </div>

        <div class="col-xs-9" style="margin-top: 80px">
        <ul class="menu-items">
        <li class="active" style="list-style: none;font-size: 2rem">Chi tiết sản phẩm</li>
        </ul>
        <div style="width:100%;border-top:1px solid silver">
        <p style="padding:15px; font-size: 1.4rem">
        ${product.description}
        </p>
        </div>
        </div>
         </div>
        </div>`
}


// $(document).ready(function () {
//     $.ajax({
//         type: "get",
//         url: "/shopee/cart",
//         success: function (data) {
//             let content = `<h4 class="header__cart-heading">
//                                 Sản phẩm đã thêm
//                             </h4> <ul class="header__cart-list-item">`;
//             for (let i = 0; i < data.length; i++) {
//                 content += getItemsCart(data[i]);
//             }
//             document.getElementById('list-items').innerHTML = content + `</ul>
// <a href="#" class="header__cart-view-cart btn btn--primary">Xem giỏ hàng</a>`;
//             document.getElementById('quantity-items').innerText = data.length;
//         }
//     })
// });

$(document).ready(function () {
    $.ajax({
        type: "get",
        url: "/shopee/cart",
        success: function (data) {
            getListItems(data)
        }
    })
});
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
                                </li>

                            `
}
function deleteItems(id){
    $.ajax({
        type: "delete",
        url: "/shopee/delete-items/"+ id,
        success: getListItems
    })
}

function addItems(){
    let quantity = $('#product-quantity').val()
    let product = {}
    $.ajax({
        type: "post",
        url: location.pathname,
        success: function(data){
            product = data
            let newItems = {
                quantity : quantity,
                product : product,
                cart : {
                    id: 1
                },
            }
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "POST",
                data: JSON.stringify(newItems),
                url: "/shopee/add-items",

                success: function(){
                    Swal.fire({
                        icon: 'success',
                        title: 'Thêm vào giỏ hànhg',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    getListItems()
                }
            })
        }

    });

}
function getListItems(){
    $.ajax({
        type: "get",
        url: "/shopee/cart",
        success: function (data) {
            let content = '';
            if(data.length == 0){

                content = `<img src="/../img/no__cart.jpg" alt=""
                    class="header__cart-no-cart-img">
    
                    <span class="header__cart-list-no-cart-msg">Chưa có sản phẩm</span>`
                document.getElementById('list-items').innerHTML = content;
                document.getElementById('quantity-items').innerText = 0;

            } else{
                content = `<h4 class="header__cart-heading">
                Sản phẩm đã thêm
            </h4>
            <ul class="header__cart-list-item">`
                for (let i = 0; i < data.length; i++) {
                    content += getItemsCart(data[i]);

                    document.getElementById('list-items').innerHTML = content + `</ul><a href="/shopee/order" class="header__cart-view-cart btn btn--primary">Xem giỏ hàng</a>`;
                    document.getElementById('quantity-items').innerText = data.length;
                }


            }

        }
    })
}

// Button view
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
    let now = $("#product-quantity").val();
    if ($.isNumeric(now)) {
        if (parseInt(now) - 1 > 0) {
            now--;
        }
        $("#product-quantity").val(now);
    } else {
        $("#product-quantity").val("1");
    }
}

function increaseQuantity() {
    $.ajax({
        type: "post",
        url: location.pathname,
        success: function (data) {

            product = data.quantity;
            let now = $("#product-quantity").val();
            if ($.isNumeric(now)) {
                if(now < product){
                    $("#product-quantity").val(parseInt(now) + 1);
                }

            } else {
                $("#product-quantity").val("1");
            }
        }

    })

}