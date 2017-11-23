$().ready( function () {
    $('#cardForm').validate({
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
            error.addClass('invalid-feedback');  // add a class to the wrapper
        },
        rules:{
            cardType : {
                required : true
            },
            cc : {
                required : true,
                minlength : 16
            },
            month : {
                required : true
            },
            year : {
                required : true
            },
            name : {
                required : true ,
                minlength: 10
            },
            cvc : {
                required : true ,
                digits : true
            }
        },
        messages : {
            cardType : {
                required : 'Tipo de tarjeta requerido'
            },
            cc : {
                required : 'Numero de tarjeta requerido',
                minlength : 'Numero no valido'
            },
            month : {
                required : 'Seleccione un mes'
            },
            year : {
                required : 'Seleccione un año'
            },
            name : {
                required : 'Complete el nombre del titular' ,
                minlength: 'Mínimo 10 caracteres'
            },
            cvc : {
                required : 'Codigo requerido' ,
                digits : 'Codigo no valido'
            }
        },
        submitHandler : function () {
            $('#cardForm').each(function () {
                $(this).find(':input').prop('disabled', true);
            });
            $('#verify1').removeClass('btn-secondary').addClass('btn-success').html('VERIFICADO');
            $('#collapseTwo').prop('disabled', true);
            $('#btn_continue').prop('disabled', false);
        }
    });

    $('#paypalForm').validate({
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
            error.addClass('invalid-feedback');  // add a class to the wrapper
        },
        rules:{
            email : {
                required : true,
                email: true
            },
            password : {
                required: true
            }
        },
        messages : {
            email : {
                required : 'Campo requerido',
                email: 'Formato no valido'
            },
            password : {
                required: 'Campo requerido'
            }
        },
        submitHandler : function () {
            $('#paypalForm').each(function () {
                $(this).find(':input').prop('disabled', true);
            });
            $('#paypal-button').removeClass('btn-secondary').addClass('btn-success').html('VERIFICADO');
            $('#collapseOne').prop('disabled', true);
            $('#btn_continue').prop('disabled', false);
        }
    });
});