package com.utn.controlador;

import com.utn.modelo.Operacion;

public class OperacionController {

	
	public boolean esValida(Operacion operacion){
		if(operacion.getConsumo() < 100){
			
			return true;
		}
		
		return false;
	}
}
