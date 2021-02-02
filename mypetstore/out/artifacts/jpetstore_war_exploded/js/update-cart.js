$(document).ready(function () {
    $('#quantity').on('blur', function (e) {
        if($('#quantity').val() === null || $('#quantity').val() === "" || isNaN(parseInt($('#quantity').val())) ){

        }
        else{
            $.ajax({
            url: 'UpdateCartServlet',
            type: 'post',
            data: {
            quantity: $('#quantity').val(),
            },
            success: function (msg) {
                let array = msg.split(',');
                $('#total').innerText = array[1];
                $('#quantity').innerText = array[0];
                $('#subtotal').innerText = array[2];
            }
        })
        }
    });
});