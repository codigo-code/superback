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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DescripcionesCodificaciones complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DescripcionesCodificaciones"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{ar.gov.afip.dia.serviciosweb.wgesTabRef}Contenedor"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DescripcionesCodificaciones" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}ArrayOfDescripcionCodificacion" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescripcionesCodificaciones", propOrder = {
    "idReferencia",
    "descripcionesCodificaciones"
})
public class DescripcionesCodificaciones
    extends Contenedor
{

    @XmlElement(name = "IdReferencia")
    protected String idReferencia;
    @XmlElement(name = "DescripcionesCodificaciones")
    protected ArrayOfDescripcionCodificacion descripcionesCodificaciones;

    /**
     * Obtiene el valor de la propiedad idReferencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdReferencia() {
        return idReferencia;
    }

    /**
     * Define el valor de la propiedad idReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdReferencia(String value) {
        this.idReferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionesCodificaciones.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDescripcionCodificacion }
     *     
     */
    public ArrayOfDescripcionCodificacion getDescripcionesCodificaciones() {
        return descripcionesCodificaciones;
    }

    /**
     * Define el valor de la propiedad descripcionesCodificaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDescripcionCodificacion }
     *     
     */
    public void setDescripcionesCodificaciones(ArrayOfDescripcionCodificacion value) {
        this.descripcionesCodificaciones = value;
    }

}
