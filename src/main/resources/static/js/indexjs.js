function getSmartphone(smartphone) {

}

function successHandler() {
    $.ajax({
        type: "GET",
        //tên API
        url: "/shopee",
        //xử lý khi thành công
        success: function (data) {

        }
    });
}
function getProduct(){
    return `<div class="col l-2-4 m-4 c-6">

                                <a class="home-product-item" href="/product/{${product.id}}">

                                    <div class="home-product-item__img"
                                         style="background-image: url(../img/sanpham1.JPG);">
                                    </div>

                                    <h4 class="home-product-item__name">
                                         ${product.name}
                                    </h4>

                                    <div class="home-product-item__price">
                                        <span class="home-product-item__price-old">${product.salePrice}</span>
                                        <span class="home-product-item__price-current">${product.salePrice}</span>
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

                                        <span class="home-product-item__sold">88 đã bán</span>

                                    </div>

                                    <div class="home-product-item__origin">
                                        <span class="home-product-item__brand">Miền Bắc</span>
                                        <span class="home-product-item__origin-name">Việt Nam</span>
                                    </div>

                                    <div class="home-product-item__favourite">
                                        <i class="fas fa-check"></i>
                                        <span>Yêu Thích</span>
                                    </div>

                                    <div class="home-product-item__sale-off">
                                        <span class="home-product-item__sale-off-percent">20%</span>
                                        <span class="home-product-item__sale-off-label">Giảm</span>
                                    </div>


                                </a>



                            </div>`;
}
$(document).ready(function (products) {
    let content = ''
    for (let i = 0; i < products.length; i++) {
        content += getSmartphone(products[i]);
    }
    document.getElementById('list-product').innerHTML = content;
        event.preventDefault();
})