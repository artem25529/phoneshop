$("button[id^='button-']").click(function (e) {
    e.preventDefault();
    const id = $(this).attr('id').slice(7);
    let quantity = $('#input-' + id).val();
    let data = {id, quantity};

    $.ajax({
        url: '/phoneshop-web/ajaxCart/remove',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        dataType: 'json',

        success: function(data) {
            $("#cart-items-quantity").text(`${data.totalQuantity} items`);
            $("#total-price").text(`$ ${data.totalPrice}`);
            $('table').find($(`tr[id=${id}]`)).remove();
        },
    });
});