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
 *         &lt;element name="ListaTablasReferenciaResult" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}TablasReferencia" minOccurs="0"/&gt;
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
    "listaTablasReferenciaResult"
})
@XmlRootElement(name = "ListaTablasReferenciaResponse")
public class ListaTablasReferenciaResponse {

    @XmlElement(name = "ListaTablasReferenciaResult")
    protected TablasReferencia listaTablasReferenciaResult;

    /**
     * Obtiene el valor de la propiedad listaTablasReferenciaResult.
     * 
     * @return
     *     possible object is
     *     {@link TablasReferencia }
     *     
     */
    public TablasReferencia getListaTablasReferenciaResult() {
        return listaTablasReferenciaResult;
    }

    /**
     * Define el valor de la propiedad listaTablasReferenciaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link TablasReferencia }
     *     
     */
    public void setListaTablasReferenciaResult(TablasReferencia value) {
        this.listaTablasReferenciaResult = value;
    }

}
