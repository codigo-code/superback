/**
 * 
 */
function comprobarClave(){ 
   	var pass = document.getElementById("password").value;  	
   	var repass = document.getElementById("repassword").value;

   	if (pass != repass) {
      	alert("Las dos claves son diferentes...\n Vuelve a intentarlo");
      	return;
	}
} 

function validarFormulario(){

	//selector en javaScript
	var pass = document.getElementById("password").value;
	var repass = document.getElementById("repassword").value;
	var form =document.getElementById("formulario");
	console.log(pass);
	console.log(repass);
	
	if(pass == repass){
		form.action="formulario";
		form.method="post";
		form.submit();
	}else{
		alert("La contraseña no es igual por favor verificar!");
	}
	
}