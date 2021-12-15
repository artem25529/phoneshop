$(window).ready(function () {
    $('span[id$="quantity.errors"]').each(function () {
        let message = $(this).text();
        if (message.includes('NumberFormatException')) {
            $(this).text('Wrong format');
        }
    })
})