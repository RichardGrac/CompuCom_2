function verify(quantity) {
    //Security:
    document.getElementById("btn_continue").disabled = true;
    for(var id_prod = 0; id_prod < quantity; id_prod++){
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

        precio = (precio * cantidad) - (precio * cantidad * descuento);
        precio = precio.toFixed(2);
        document.getElementById(s_subtotal).innerText = precio;
    }
    calc_total(quantity);
}

function calc_total(quantity) {
    var s_subtotal = "subtotal";
    var subtotal = 0.0;
    for(var i = 0; i < quantity; i++){
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

function calc_price(quantity){
    /* Doesn't exists products in the cart */
    if (quantity == "0"){
        document.getElementById("btn_continue").setAttribute("disabled", "disabled");
    }else{
        for (var i = 0; i < quantity; i++){
            var s_price = ("precio" + i.toString());
            var price = parseFloat(document.getElementById(s_price).innerText);
            price = (price - (price * 0.16));
            price = price.toFixed(2);
            document.getElementById(s_price).innerText = price;
        }
        verify(quantity);
    }
}

function testing(id_sc, iter, id_user) {
    var quantity = document.getElementById('cantidad' + iter).value;
    var url = '/shopping_cart/updatequantity?sc_id=' + id_sc + '&quantity=' + quantity;
    $.get(url, function () {
        window.location.href = "/shopping_cart/showcart?id_user=" + id_user;
    });
}