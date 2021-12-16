$('select[id^=select-status-]').change(function () {
    let id = $(this).attr('id').slice(14);
    let orderStatus = $(this).val();

    const data = {id, orderStatus};

    $.ajax({
        type: 'POST',
        url: '/phoneshop-web/setStatus',
        data: JSON.stringify(data),
        contentType: 'application/json'
    })
})