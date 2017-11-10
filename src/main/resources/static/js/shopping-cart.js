function verify(id_prod) {
    //Security:
    document.getElementById("btn_continue").disabled = true;
    id_prod = id_prod.toString();
    //Strings:
    var s_precio = "precio" + id_prod;
    var s_cantidad = "cantidad" + id_prod;
    var s_descuento = "descuento" + id_prod;
    var s_subtotal = "subtotal" + id_prod;

    //Valores:
    var precio, descuento, cantidad, total;

    precio = parseFloat(document.getElementById(s_precio).innerText);
    cantidad = parseFloat(document.getElementById(s_cantidad).value);
    descuento = "0." + document.getElementById(s_descuento).innerText;
    descuento = parseFloat(descuento);

    precio = (precio * cantidad * descuento).toFixed(2);
    document.getElementById(s_subtotal).innerText = precio;

}

function calc_total(quantity) {
    var s_subtotal = "subtotal";
    var subtotal = 0.0;
    for(var i = 1; i <= quantity; i++){
        var element = s_subtotal+(i.toString());
        subtotal += parseFloat(document.getElementById(element).innerText);
    }
    var iva = subtotal * 0.16;
    var total = subtotal + iva;

    document.getElementById("subtotal").innerText = subtotal.toFixed(2);
    document.getElementById("iva").innerText = iva.toFixed(2);
    document.getElementById("total").innerText = total.toFixed(2);

    document.getElementById("btn_continue").disabled = false;
}