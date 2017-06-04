

function cargarArchivo(elemento){
	var file = elemento.files[0];
	
	var objHidden = document.formulario.nombre;
	objHidden.value = file.name;
	
	document.formulario.target = "null" ;
	
	document.formulario.action = "ProcesoArchivo" ;
	document.formulario.submit();
	
	alert("imagen guardada");
	
}