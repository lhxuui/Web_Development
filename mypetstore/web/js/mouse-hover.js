$(document).ready(function () {
    $('#estoremap area').mouseover(function () {
        $.ajax({
            url:'CategoryShowServlet',
            type: 'post',
            dataType:'html',
            data: {categoryId: $(this).attr('alt'),},
            success: function (msg) {
                console.log('success');
                let inform = document.getElementById("inform");
                inform.innerText = msg;
                inform.style.display = "block";
            },
            error: function () {
                console.log('failed');
            }
        })
        })
        .on('mouseout',function (e) {
            let informDiv = document.getElementById('inform');
            let x=e.clientX;
            let y=e.clientY;
            let divx1 = informDiv.offsetLeft;
            let divy1 = informDiv.offsetTop;
            let divx2 = informDiv.offsetLeft + informDiv.offsetWidth;
            let divy2 = informDiv.offsetTop + informDiv.offsetHeight;
            if( x < divx1 || x > divx2 || y < divy1 || y > divy2){
                document.getElementById('inform').style.display='none';
        }
    })
});