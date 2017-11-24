$(document).ready(function() {
    $("#categoryForm").bootstrapValidator({
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
                        message: 'Por favor ingresa una categoria'
                    }
                }
            }
        }
    })
});