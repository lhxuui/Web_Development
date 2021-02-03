// CURSOR
let cursor = $(".cursor"),
    follower = $(".cursor-follower");

let posX = 0,
    posY = 0;

let mouseX = 0,
    mouseY = 0;

TweenMax.to({}, 0.016, {
    repeat: -1,
    onRepeat: function () {
        posX += (mouseX - posX) / 9;
        posY += (mouseY - posY) / 9;

        TweenMax.set(follower, {
            css: {
                left: posX - 0,
                top: posY - 0
            }
        });

        TweenMax.set(cursor, {
            css: {
                left: mouseX,
                top: mouseY
            }
        });
    }
});

$(document).on("mousemove", function (e) {
    mouseX = e.clientX;
    mouseY = e.clientY;
});

//transform into a circle
$("a").on("mouseenter", function () {
    cursor.addClass("active");
    follower.addClass("active");
});
$("a").on("mouseleave", function () {
    cursor.removeClass("active");
    follower.removeClass("active");
});