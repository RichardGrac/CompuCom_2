function tipo_pagina() {
    if(document.getElementById("input1").value != ""){
        /* Cambiamos titulo de página */
        document.title = 'Editar Paciente - Opticas Cristal';
        /* Quitamos el icono de upload y lo cambiamos por uno de save */
        document.getElementById("span_boton").classList.remove('fa');
        document.getElementById("span_boton").classList.remove('fa-upload');
        document.getElementById("span_boton").classList.add('glyphicon');
        document.getElementById("span_boton").classList.add('glyphicon-save');
        /* Cambiamos el sub-titulo de "/ Nuevo usuario" a " / Edicion de usuario " */
        document.getElementById("tipo_pagina").innerHTML = "/ Edición de paciente";
        /* Cambiamos de "Dar de alta" a "Guardar" */
        document.getElementById("boton_submit").innerHTML = "Guardar &nbsp;&nbsp;";
    }
}