$(document).ready(function () {
    $.ajax({
        type: "get",
        url: "/shopee/cart",
        success: function (data) {
            showListItems(data)

        }
    })
})

function getItemsFromCart(items, totalPriceProduct) {
    return `
          <tbody>
          <tr>
            <td><img src="${items.product.img}" style="width: 70px; height: 70px"/> </td>
            <td>${items.product.name}</td>
            <td>${items.product.realPrice}đ</td>
            <td style="text-align: center">${items.quantity}</td>
            <td class="text-right">${totalPriceProduct}đ</td>
            <td class="text-right" onclick="deleteItems(${items.id})"><button class="btn btn-sm btn-danger btn--primary"><i class="fa fa-trash"></i> </button> </td>
          </tr>`
}

function deleteItems(id) {
    $.ajax({
        type: "delete",
        url: "/shopee/delete-items/" + id,
        success: function () {
            $.ajax({
                type: "get",
                url: "/shopee/cart",
                success: function (data) {
                    showListItems(data)
                }
            })
        }
    })
}

function showListItems(data) {
    let totalPriceProduct = 0;
    let totalPrice = 0;
    let content = `<thead>
          <tr>
            <th scope="col"> </th>
            <th scope="col">Sản phẩm</th>
            <th scope="col">Giá tiền</th>
            <th scope="col" class="text-center">Số lượng</th>
            <th scope="col" class="text-right">Tổng tiền</th>
            <th> </th>
          </tr>
          </thead>`;
    for (let i = 0; i < data.length; i++) {
        totalPriceProduct += data[i].product.realPrice * data[i].quantity
        totalPrice += totalPriceProduct
        content += getItemsFromCart(data[i], totalPriceProduct, totalPrice)
    }
    document.getElementById("order").innerHTML = content + `<tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>Tổng tiền hàng</td>
            <td class="text-right">${totalPrice}đ</td>
          </tr>
          <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>Phí vận chuyển</td>
            <td class="text-right"></td>
          </tr>
          <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><strong>Tổng thanh toán</strong></td>
            <td class="text-right" id="total-order"><strong>${totalPrice}đ</strong></td>
          </tr>
          </tbody>`

}


function addOrder() {
    let today = new Date();
    let month = today.getMonth() + 1
    let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds() + "-" + today.getDate() + "/" + month + "/" + today.getFullYear();
    let newOrder = {
        day : time,
        status: 1,
        user : {
            id :1
        }
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newOrder),
        url: "/shopee/order",

        success: function (data1) {
            $.ajax({
                type: "get",
                url: "/shopee/cart",
                success: function (data) {
                    for (let i = 0; i < data.length; i++) {
                        let product = {}
                        let itemOrder = {
                            quantity : data[i].quantity,
                            product : data[i].product
                        }
                        $.ajax({
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            type: "POST",
                            data: JSON.stringify(itemOrder),
                            url: "/shopee/item-order",
                            success: function (data3) {
                                let orderDetail = {
                                    orders : data1,
                                    itemsOrder : data3
                                }
                                $.ajax({
                                    headers: {
                                        'Accept': 'application/json',
                                        'Content-Type': 'application/json'
                                    },
                                    type: "POST",
                                    data: JSON.stringify(orderDetail),
                                    url: "/shopee/order-detail",
                                    success: function () {

                                    }
                                })
                            }
                        })
                    }
                }
            })
        }
    })
}




