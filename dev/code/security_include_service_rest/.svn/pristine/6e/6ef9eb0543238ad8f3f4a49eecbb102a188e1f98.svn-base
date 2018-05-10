package ar.gob.gcaba.security.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.text.StrSubstitutor;

public class Utilitario {

	public static void permutar(java.util.List<String> arr, int k, int factorial, List<String> listaPermutada) {

		for (int i = k; i < arr.size(); i++) {
			java.util.Collections.swap(arr, i, k);
			permutar(arr, k + 1, factorial, listaPermutada);
			java.util.Collections.swap(arr, k, i);
		}
		if (k == arr.size() - 1) {

			String palabraInsertar = new String();
			for (int i = 0; i < arr.size(); i++) {
				String palabra = arr.get(i);
				palabraInsertar += palabra;
				if (i < arr.size() - 1) {
					palabraInsertar += " ";
				}
			}

			listaPermutada.add(palabraInsertar);

			if (listaPermutada.size() == factorial)
				return;

		}
	}

	public static void main(String[] args) {
		int fac = factorial(3);
		List<String> listaPermuta = new ArrayList<String>();
		List<String> listaPermutacion = java.util.Arrays.asList("hola", "chau", "siga");
		Utilitario.permutar(listaPermutacion, 0, fac, listaPermuta);
		System.out.println(java.util.Arrays.toString(listaPermuta.toArray()));
	}

	public static int factorial(int n) {
		if (n - 1 == 0) {
			return n;
		} else {
			return factorial(n - 1) * n;
		}
	}

	/**
	 * Arma un string que sirve como parametro para la busqueda por solr el
	 * mismo tendra la forma:
	 * 
	 * 
	 * valor ingresado: cadena1 cadena
	 * 
	 * valor a buscar: cadena1cadena2 OR ("cadena1 cadena2~2") or (
	 * "cadena2 cadena1~2")
	 * 
	 * @param cadenaBusqueda
	 * @param listaPermutada
	 * @param cantidadDePalabras
	 * @return
	 */
	public static String armarParametroConsultaSolr(String cadenaBusqueda, List<String> listaPermutada,
			int cantidadDePalabras) {

		StringBuffer parametroDeQuery = new StringBuffer();

		parametroDeQuery.append("(");

		parametroDeQuery.append(cadenaBusqueda.replace(" ", "") + " OR ");

		if (cantidadDePalabras == 1) {
			parametroDeQuery.append(cadenaBusqueda);
			parametroDeQuery.append("*");
			parametroDeQuery.append(" OR ");
		}

		for (int i = 0; i < listaPermutada.size(); i++) {
			String cadena = listaPermutada.get(i);
			parametroDeQuery.append("(\"");
			parametroDeQuery.append(cadena);
			parametroDeQuery.append("\"");
			parametroDeQuery.append("~" + cantidadDePalabras);
			parametroDeQuery.append(")");

			if (i < listaPermutada.size() - 1) {
				parametroDeQuery.append(" OR ");
			}
		}

		parametroDeQuery.append(")");

		return parametroDeQuery.toString();
	}

	/**
	 * Concatena los parametros para la consulta a BD
	 * 
	 * @param cadenaBusqueda
	 * @param listaPermutada
	 * @param cantidadDePalabras
	 * @return
	 */
	public static String armarParametroConsultaBD(String cadenaBusqueda, List<String> listaPermutada,
			int cantidadDePalabras) {

		StringBuffer parametroDeQuery = new StringBuffer();

		if (cantidadDePalabras == 1) {
			parametroDeQuery.append(cadenaBusqueda);
		} else {

			for (int i = 0; i < listaPermutada.size(); i++) {
				String cadena = listaPermutada.get(i);

				parametroDeQuery.append(cadena);

				if (i < listaPermutada.size() - 1) {
					parametroDeQuery.append("|");
				}
			}
		}

		return parametroDeQuery.toString();
	}

	/**
	 * Obtiene las reparticiones formateadas para la consulta
	 * 
	 * @param reparticiones
	 * @return
	 */
	public static String obtenerReparticionesABuscar(List<String> reparticiones) {

		String reparticionesABuscar = "' '";

		if (reparticiones != null && !reparticiones.isEmpty()) {
			reparticionesABuscar = reparticiones.toString().replace("[", "'").replace("]", "'").replaceAll(", ", "','")
					.trim();
		}

		return reparticionesABuscar;

	}

	/***
	 * Reemplaza un parametro dentro de una cadena con el valor dentro del mapa
	 * 
	 * @param cadena
	 * @param mapa
	 * @return
	 */
	public static String setParametroCadena(String cadena, Map mapa) {
		@SuppressWarnings("unchecked")
		StrSubstitutor sub = new StrSubstitutor(mapa);
		String stringResuelto = sub.replace(cadena);
		return stringResuelto;
	}
	/**
	 * genera una union entre dos listas
	 * @param list1
	 * @param list2
	 * @return Lista
	 * @author carmarin
	 */
    public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }
}
