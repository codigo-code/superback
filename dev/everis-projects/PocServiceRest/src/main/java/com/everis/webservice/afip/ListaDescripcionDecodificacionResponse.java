//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.04.23 a las 12:48:59 PM ART 
//


package com.everis.webservice.afip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ListaDescripcionDecodificacionResult" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}DescripcionesCodificaciones" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "listaDescripcionDecodificacionResult"
})
@XmlRootElement(name = "ListaDescripcionDecodificacionResponse")
public class ListaDescripcionDecodificacionResponse {

    @XmlElement(name = "ListaDescripcionDecodificacionResult")
    protected DescripcionesCodificaciones listaDescripcionDecodificacionResult;

    /**
     * Obtiene el valor de la propiedad listaDescripcionDecodificacionResult.
     * 
     * @return
     *     possible object is
     *     {@link DescripcionesCodificaciones }
     *     
     */
    public DescripcionesCodificaciones getListaDescripcionDecodificacionResult() {
        return listaDescripcionDecodificacionResult;
    }

    /**
     * Define el valor de la propiedad listaDescripcionDecodificacionResult.
     * 
     * @param value
     *     allowed object is
     *     {@link DescripcionesCodificaciones }
     *     
     */
    public void setListaDescripcionDecodificacionResult(DescripcionesCodificaciones value) {
        this.listaDescripcionDecodificacionResult = value;
    }

}
