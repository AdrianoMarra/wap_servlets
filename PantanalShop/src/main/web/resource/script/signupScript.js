(function(){
    "use strict";

    let init = function () {
        $("#loader").hide();
        validateRegisterForm();
        $('#registration-form').submit(function() {
                                        $("#loader").fadeIn(300);
                                    });
    }


    let validateRegisterForm = function() {

            $('#password, #confirmPassword').on('keyup', function () {
                if ($('#password').val() === $('#confirmPassword').val()) {
                    $('#matching').removeClass('text-danger').addClass('text-success').html('Matching');
                    $('#registerbtn').prop('disabled', false);

                }
                else{
                    $('#matching').html('Not Matching');
                    $('#registerbtn').prop('disabled', true);
                }
            });

            if(($('#fullName').val().length || $('#email').val().length|| $('#address').val().length|| $('#password').val().length) == 0){
                $('#registerbtn').prop('disabled', true);
            }
            else{
                $('#registerbtn').prop('disabled', false);
            }
    }


    $(init);

})();
