		
function genPDF(numero,nombre){
	/* Cambiamos el fondo gris a blanco solo mientras obtenemos el DOM */
	var dom = "DOM" + numero;

	document.getElementById(dom).style.background="white";
    document.getElementById(dom).style.border="1px solid #b3b3b3";
    html2canvas(document.getElementById(dom), {
		onrendered: function (canvas){
			/* Obtenemos la imagen del HTML */ 
			var img = canvas.toDataURL("image/jpg");	
			var width = 208;	/* Tam. carta = 216x279*/
			var heigth = 271;
			var doc = new jsPDF('','mm','letter');
			doc.addImage(img, 'JPEG',4,4,width,heigth);
			
			var doc_name = "Historial_"+nombre+".pdf";
			doc.save(doc_name);
		}
	});
	document.getElementById(dom).style.background="#f5f5f5";
    document.getElementById(dom).style.border="none";
}