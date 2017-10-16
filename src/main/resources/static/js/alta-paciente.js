$(document).ready(function() {
    $('#dataTables-example').DataTable({
            responsive: true
        });
    $("#formato-alta-paciente").bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {            
             name: {
                validators: {
                    notEmpty: {
                        message: 'Por favor completa el nombre del paciente'
                    }
                }
            },
            firtslastname: {
                validators: {
                    notEmpty: {
                        message: 'Por favor completa el primer apellido del paciente'
                    }
                }
            },
            secondlastname: {
                validators: {
                    notEmpty: {
                        message: 'Por favor completa el segundo apellido del paciente'
                    }
                }
            },
            borndate: {
                validators: {
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'Escriba la fecha en formato DD/MM/AAAA'
                    },
                    notEmpty: {
                        message: 'Por favor completa la fecha de nacimiento del paciente'
                    }
                }
            },
            age: {
                validators: {
                    numeric:{
                        message: 'Por favor introduzca un valor numérico'
                    },
                    notEmpty: {
                        message: 'Por favor completa el campo de la edad del paciente'
                    }
                }
            },
            occupation: {
                validators: {
                    notEmpty: {
                        message: 'Por favor completa la ocupación del paciente'
                    }
                }
            },
            phone: {
                validators: {
                    stringLength: {
                        min: 7,
                        max: 10,
                        message: 'El teléfono tiene que tener entre 7 a 10 dígitos'
                    },
                    notEmpty: {
                        message: 'Por favor completa el teléfono de casa del paciente'
                    }
                }
            },

            address: {
                validators: {
                    notEmpty: {
                        message: 'Por favor completa el domicilio del paciente'
                    }
                }
            },
            noaddress: {
                validators: {
                    numeric:{
                        message: 'Por favor introduzca un valor numérico'
                    },
                    notEmpty: {
                        message: 'Por favor completa el número de hogar del paciente'
                    }
                }
            },
            colony: {
                validators: {
                    notEmpty: {
                        message: 'Por favor completa la colonia del paciente'
                    }
                }
            }
        }
        })
    });
