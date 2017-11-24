$().ready( function () {
    $('#registerForm').validate({
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
            username : {
                required : true
            },
            email : {
                required : true,
                email : true
            },
            password : {
                required : true,
                minlength : 8
            },
            password_again : {
                required : true,
                equalTo : '#password'

            },
            street : {
                minlength : 5
            },
            number : {
                digits : true
            },
            colony : {
                minlength: 5
            },
            zip : {
                digits : true
            },
            state : {
                minlength: 5
            },
            city : {
                minlength: 5
            },
            country : {},
            reference : {}
        },
        messages : {
            username : {
                required : 'Usuario requerido'
            },
            email : {
                required : 'Correo electronico requerido',
                email : true
            },
            password : {
                required : 'Contraseña requerida',
                minlength : 'Longitud minima 8 caracteres'
            },
            password_again : {
                required : 'Campo requerido',
                equalTo : 'No coiciden las contraseñas'

            },
            street : {
                minlength : 'Minimo 5 caracteres'
            },
            number : {
                digits : 'No valido'
            },
            colony : {
                minlength: 'Minimo 5 caracteres'
            },
            zip : {
                digits : 'No valido'
            },
            state : {
                minlength: 'Minimo 5 caracteres'
            },
            city : {
                minlength: 'Minimo 5 caracteres'
            },
            country : {
                minlength: 'Minimo 5 caracteres'
            },
            reference : {}
        },
        submitHandler : function () {
            $('#registerForm').addClass('was-validated');
            document.getElementById('registerForm').submit();
        }
    });

    $().ready( function () {
        $('#updateForm').validate({
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
                username : {
                    required : true
                },
                email : {
                    required : true,
                    email : true
                },
                street : {
                    minlength : 5
                },
                number : {
                    digits : true
                },
                colony : {
                    minlength: 5
                },
                zip : {
                    digits : true
                },
                state : {
                    minlength: 5
                },
                city : {
                    minlength: 5
                },
                country : {},
                reference : {}
            },
            messages : {
                username : {
                    required : 'Usuario requerido'
                },
                email : {
                    required : 'Correo electronico requerido',
                    email : true
                },
                street : {
                    minlength : 'Minimo 5 caracteres'
                },
                number : {
                    digits : 'No valido'
                },
                colony : {
                    minlength: 'Minimo 5 caracteres'
                },
                zip : {
                    digits : 'No valido'
                },
                state : {
                    minlength: 'Minimo 5 caracteres'
                },
                city : {
                    minlength: 'Minimo 5 caracteres'
                },
                country : {},
                reference : {}
            },
            submitHandler : function () {
                $('#updateForm').addClass('was-validated');
                document.getElementById('updateForm').submit();
            }
        });
    });
});