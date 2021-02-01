$(document).ready(function () {
    $('#username').on('blur',function (e) {
        $('#msg').hide();
        if($('#username').val() === null || $('#username').val() === ""){
            $('#msg').show();
            $('#msg').html("Username Required").css('color','red');
        } else {
            $.ajax({
            url: 'CheckUsernameServlet',
            dataType: "html",
            data: $('#signupForm').serialize(),
            cache:false,
            type : "POST",
            success: function(msg){
                let parseJson = jQuery.parseJSON(msg);
                if(parseJson.message === 'exist'){
                    //$('#msg').className = 'usernameExist';
                    $('#msg').html('Username already exist!');
                }
                else{
                    //$('#msg').className = 'usernameNotExist';
                    $('#msg').html('Username Available!');
                }
                $('#msg').show();
                console.log(msg);
            },
            error: function(){
                alert("error");
            }
            });
        }
    });
});



