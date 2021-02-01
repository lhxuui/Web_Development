$(document).ready(function () {
    $('#quantity').on('blur', function () {
        if($('#quantiy').val() === null || $('#quantity').val() !== "" || !isNaN(parseInt($.('#quantity')).val()) ){
            $('#quantity').history.go(-1);
        }
        $.ajax({
            url: 'UpdateCartServlet',
            type: 'post',
            data: {
            quantity: this.val(),
            },
            success: function (msg) {
                $('#quantity')
            }
        })
    });
});