$(document).ready(function () {
    $("input#searchText").autocomplete({
        source:function(request, response){
            $.ajax({
                url:'SearchProductResultServlet',
                dataType : "json",
                data: request,
                type:"get",
                scroll:true,
                success: function (data) {
                    if (typeof Array.prototype.forEach != 'function') {
                        Array.prototype.forEach = function(callback){
                            for (let i = 0; i < this.length; i++){
                                callback.apply(this, [this[i], i, this]);
                            }
                        };
                    }

                    let values = data;
                    let newArray = new Array(values.length);
                    let i = 0;
                    values.forEach(function (entry) {
                        let newObject = {
                            label: entry.name
                        };
                        newArray[i] = newObject;
                        i++;
                    });

                    response(newArray);
                }
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus);
        },
        minLength:1
    })
});