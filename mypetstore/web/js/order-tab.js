$(document).ready(function () {
    let tabs = $( "#tabs" );
    tabs.tabs();
    $('#newOrderForm').on('submit', function (e) {
        e.preventDefault();
            let maxid = 0;
            $('#tabs ul li').each(function () {
                let value = parseInt($(this).attr('id'));
                maxid = (value > maxid) ? value : maxid;
            });
            let tabNameExists = false;
            $('#tabs ul li a').each(function (i) {
                if ($.trim(this.text.toLowerCase()) === "confirmorder") {
                    tabNameExists = true;
                }
            });
            if(!tabNameExists){
                let newid = maxid + 1;
                $.ajax({
                    type: "get",
                    url: "confirmOrderForm",
                    dataType: 'html',
                    data: $('#newOrderForm').serialize(),
                    success: function (data) {
                        let $newtabs = $("<div id='tabs-" + newid + "'></div>");
                        let $a = $("<li id='" + newid + "'><a href= '#tabs-"+ newid +"'>ConfirmOrder</a></li>");
                        $('li:last').after($a);
                        tabs.append($newtabs);
                        $newtabs.html(data);
                        tabs.tabs("refresh");
                        console.log(data);
                    },
                    error: function () {
                        console.log('error');
                    }
                })
            }
    });

    $('#shippingForm').on('submit', function (e) {
        e.preventDefault();
            let maxid = 0;
            $('#tabs ul li').each(function () {
                let value = parseInt($(this).attr('id'));
                maxid = (value > maxid) ? value : maxid;
            });
            let tabNameExists = false;
            $('#tabs ul li a').each(function (i) {
                if ($.trim(this.text.toLowerCase()) === "order form") {
                    tabNameExists = true;
                }
            });
            if(!tabNameExists){
                let newid = maxid + 1;
                $.ajax({
                    type: "get",
                    url: "confirmOrderForm",
                    dataType: 'html',
                    data: $('#shippingForm').serialize(),
                    success: function (data) {
                        let $newtabs = $("<div id='tabs-" + newid + "'></div>");
                        let $a = $("<li id='" + newid + "'><a href= '#tabs-" + newid + "'>Order Form</a></li>");
                        $('li:last').after($a);
                        tabs.append($newtabs);
                        $newtabs.html(data);
                        tabs.tabs("refresh");
                        console.log(data);
                    },
                    error: function () {
                        console.log('error');
                    }
                })
            }
    });
});