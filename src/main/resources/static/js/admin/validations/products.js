$(document).ready(function() {
    $("#productForm").bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'name': {
                validators: {
                    notEmpty: {
                        message: 'Por favor completa el nombre del producto'
                    }
                }
            },
            'category' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor selecciona una categoria'
                    }
                }
            },
            'price' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa un precio para el producto'
                    },
                    numeric : {
                        message: 'Precio invalido'
                    }
                }
            },
            'description' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa una descripcion para el producto'
                    }
                }
            },
            'image' : {
                validators: {

                }
            },
            'percentage' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa un descuento valido'
                    },
                    numeric : {
                        message: 'Descuento invalido'
                    }
                }
            },
            'quantity' : {
                validators: {
                    notEmpty: {
                        message: 'Por favor ingresa la cantidad de productos disponibles'
                    },
                    integer: {
                        message: 'Cantidad no valida'
                    }
                }
            }
        }
    })
});

