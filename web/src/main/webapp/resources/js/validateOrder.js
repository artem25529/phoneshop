function isValidate() {
    $('span').each(function () {
        $(this).css('display', 'none');
    });

    let firstName = $('#firstName').val();
    let lastName = $('#lastName').val();
    let address = $('#address').val();
    let phone = $('#phone').val();

    const errors = new Map();

    if (!firstName.match(/\w{2,10}/)) {
        errors.set('firstName', 'invalid name');
    }

    if (!lastName.match(/\w{2,10}/)) {
        errors.set('lastName', 'invalid last name');
    }

    if (!address.match(/\w{2,10}/)) {
        errors.set('address', 'invalid address');
    }

    if (!phone.match(/\+?\d{2,3}\s\(\d{2}\)\s\d{3}-\d{2}-\d{2}/)) {
        errors.set('phone', 'invalid phone');
    }

    if (errors.size > 0) {
        errors.forEach((value, key) => {
            $(`span[id=${key}Error]`).css('display', 'block').css('color', 'red');
        });

        $('input').each(function () {
            $(this).text($(this).val());
        })
        return false;
    }
}