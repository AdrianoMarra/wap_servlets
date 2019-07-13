(function(){
    "use strict";

    $(function () {
        $("#theFixed").css("top", Math.max(0, 700 - $(this).scrollTop()));
        $(window).scroll(function() {
            $("#theFixed").css("top", Math.max(0, 700 - $(this).scrollTop()));
        });
    });
})();

