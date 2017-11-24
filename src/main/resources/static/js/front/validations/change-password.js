$().ready( function () {
    $('#passwordForm').validate({
        debug: true,
        errorClass: 'is-invalid',
        validClass: 'is-valid',
        highlight: function(element, errorClass, validClass) {
            $(element).addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).removeClass(errorClass).addClass(validClass);
        },
        errorPlacement: function(error, element) {
            error.insertAfter(element);
            error.addClass('invalid-feedback');
        },
        rules:{
            password : {
                required : true,
                minlength : 8
            },
            password_again : {
                required : true,
                equalTo : '#password'
            }
        },
        messages : {
            password : {
                required : 'Contraseña requerida',
                minlength : 'Longitud minima 8 caracteres'
            },
            password_again : {
                required : 'Campo requerido',
                equalTo : 'No coiciden las contraseñas'

            }
        },
        submitHandler : function () {
            $('#passwordForm').addClass('was-validated');
            document.getElementById('passwordForm').submit();
        }
    });
});