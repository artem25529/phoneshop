$("button[id^='add-to-cart-']").click(function () {
    let id = $(this).attr("id").slice(12);
    let quantity = $("#item-quantity-" + id).val();
    let data = {id, quantity};

    $.ajax({
        url: "/phoneshop-web/ajaxCart",
        type: "POST",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        success: function(data) {
            $("#cart-items-quantity").text(`${data.totalQuantity} items`);
            $("#total-price").text(`$ ${data.totalPrice}`);
            $("div[id^='item-quantity-error-']").text("");
        },
        error: function (data) {
            $(`#item-quantity-error-${id}`).text(data.responseText);
        }
    });
});

$(function () {
    $.get("/phoneshop-web/ajaxCart", function (data) {
        $('#cart-items-quantity').text(`${data.totalQuantity} items`);
        $('#total-price').text(`$ ${data.totalPrice}`);
    });
});