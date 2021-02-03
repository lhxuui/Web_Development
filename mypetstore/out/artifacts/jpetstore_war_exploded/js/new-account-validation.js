$(document).ready(function () {
    $('#username').on('blur',function (e) {
        $('#namemsg').hide();
        if($('#username').val() === null || $('#username').val() === ""){
            $('#namemsg').show();
            $('#namemsg').html("Username Required").css('color','red');
        }
            else {
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
                    $('#namemsg').html('Username already exist!');
                }
                else{
                    //$('#msg').className = 'usernameNotExist';
                    $('#namemsg').html('Username Available!');
                }
                $('#namemsg').show();
                console.log(msg);
            },
            error: function(){
                alert("error");
            }
            });
        }
    });

    $('#password').on('blur', function () {
        $('#pwdmsg').hide();
        if($('#password').val() === null || $('#password').val() === "" ) {
            $('#pwdmsg').html("Password Required").css('color','red');
            $('#pwdmsg').show();
        }
        if($('#repeat').val() !== $('#password').val()){
            $('#repeatmsg').html("Two passwords incoherent!").css('color','red');
            $('#repeatmsg').show();
        }
    });

    $('#repeat').on('blur',function () {
        $('#repeatmsg').hide();
        if($('#repeat').val() === null || $('#repeat').val() === "" ) {
            $('#repeatmsg').html("Password Required").css('color','red');
            $('#repeatmsg').show();
        }
        if($('#repeat').val() !== $('#password').val()){
            $('#repeatmsg').html("Two passwords incoherent!").css('color','red');
            $('#repeatmsg').show();
        }
    })
});



