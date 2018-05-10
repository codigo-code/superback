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
 *         &lt;element name="Autentica" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}Autenticacion" minOccurs="0"/&gt;
 *         &lt;element name="IdReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "autentica",
    "idReferencia"
})
@XmlRootElement(name = "ListaLugaresOperativos")
public class ListaLugaresOperativos {

    @XmlElement(name = "Autentica")
    protected Autenticacion autentica;
    @XmlElement(name = "IdReferencia")
    protected String idReferencia;

    /**
     * Obtiene el valor de la propiedad autentica.
     * 
     * @return
     *     possible object is
     *     {@link Autenticacion }
     *     
     */
    public Autenticacion getAutentica() {
        return autentica;
    }

    /**
     * Define el valor de la propiedad autentica.
     * 
     * @param value
     *     allowed object is
     *     {@link Autenticacion }
     *     
     */
    public void setAutentica(Autenticacion value) {
        this.autentica = value;
    }

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

}
