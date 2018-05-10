function calcularConsumo() {
	var producto = document.getElementsByName("producto")[0].value;
	var cantidad = document.getElementsByName("cantidad")[0].value;
	var consumo;
	switch (producto) {

	case "Cortina":
		consumo = cantidad * 10;
		break;
	case "Puerta":
		consumo = cantidad * 20;
		break;
	case "Mesa":
		consumo = cantidad * 30;
		break;
	case "Silla":
		consumo = cantidad * 40;
		break;
	case "Pepito":
		consumo = cantidad * 50;
		break;
	}

	document.getElementsByName("consumo")[0].value = consumo;
}