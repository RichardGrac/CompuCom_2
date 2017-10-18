$(document).ready(function() {
    $("#formato-alta-usuario").bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'username': {
                validators: {
                    notEmpty: {
                        message: 'Por favor crea un nombre de usuario'
                    }
                }
            },
            'password': {
                validators: {
                    stringLength: {
                        min: 8,
                        max: 15,
                        message:'Minimo 8 caracteres y máximo 15'
                    },
                    notEmpty: {
                        message: 'Por favor completa contraseña'
                    },
                    identical:{
                        field: 'password2',
                        message: 'La contraseña y la confirmación no son iguales'
                    },
                    different:{
                        field: 'username',
                        message: 'El nombre de usuario y contraseña deben ser diferentes'
                    }
                }
            },
            'password2': {
                validators: {
                    stringLength: {
                        min: 8,
                        max: 15,
                        message: 'Minimo 8 caracteres y máximo 15'
                    },
                    notEmpty: {
                        message: 'Por favor completa contraseña'
                    },
                    identical: {
                        field: 'password',
                        message: 'La contraseña y la confirmación no son iguales'
                    },
                    different: {
                        field: 'username',
                        message: 'El nombre de usuario y contraseña deben ser diferentes'
                    }
                }
            },
            'role': {
                validators: {
                    stringLength: {
                        max: 8,
                    },
                    notEmpty: {
                        message: 'Por favor selecciona el tipo de usuario que será.'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function(e) {
            $('#success_message').slideDown({ opacity: "show" }, "slow") // Do something ...
            $('#formato-alta-usuario').data('bootstrapValidator').resetForm();

            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');
        });
});

