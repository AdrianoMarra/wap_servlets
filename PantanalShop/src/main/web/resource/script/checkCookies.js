(function(){
    "use strict";

    //Check for cookies and try to login
    $(function () {

        let cookies = document.cookie.split("; ");
        if(cookies.length == 3){
            const user = cookies[1].split("=")[1];
            const key = cookies[2].split("=")[1];
            $.post('/auth', {"user": user, "key": key})
                .done(logging)
                .fail(ajaxFailure)
                .always(() => {
                    $("#loader").hide();
                });
            $("#loader").fadeIn(300);
        }

        function logging(){
            console.log("Logged successfully!");
        }
        function ajaxFailure() {
            console.error("Login Fail!");
        }

    });

})();

