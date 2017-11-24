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
                        message: 'Por favor completa tú nombre'
                    }
                }
            },
            'password': {
                validators: {
                    stringLength: {
                        message:'Minimo 8 caracteres'
                    },
                    notEmpty: {
                        message: 'Por favor completa contraseña'
                    }
                }
            },
            'password_again': {
                validators: {
                    stringLength: {
                        min: 8,
                        message: 'Minimo 8 caracteres'
                    },
                    notEmpty: {
                        message: 'Por favor completa contraseña'
                    },
                    identical: {
                        field: 'password',
                        message: 'La contraseña y la confirmación no son iguales'
                    }
                }
            },
            'email': {
                validators: {
                    notEmpty: {
                        message: 'Correo electronico requerido'
                    },
                    emailAddress: {
                        message: 'Ingrese un correo electronico valido'
                    }
                }
            },
            'roles': {
                validators: {
                    stringLength: {
                        max: 8
                    },
                    notEmpty: {
                        message: 'Por favor selecciona el tipo de usuario que será.'
                    }
                }
            },
            'street' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa una calle valida'
                    }
                }
            },
            'number' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa un numero de calle valido'
                    }
                }
            },
            'colony' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa una colonia'
                    }
                }
            },
            'city' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa una ciudad'
                    }
                }
            },
            'state' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa un estado'
                    }
                }
            },
            'zip' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa un codigo postal'
                    }
                }
            },
            'country' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa un codigo postal'
                    }
                }
            },
            'reference' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa una referencia'
                    }
                }
            }

        }
    })
});

