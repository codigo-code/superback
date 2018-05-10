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
 *         &lt;element name="ListaPaisesAduanasResult" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}PaisesAduanas" minOccurs="0"/&gt;
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
    "listaPaisesAduanasResult"
})
@XmlRootElement(name = "ListaPaisesAduanasResponse")
public class ListaPaisesAduanasResponse {

    @XmlElement(name = "ListaPaisesAduanasResult")
    protected PaisesAduanas listaPaisesAduanasResult;

    /**
     * Obtiene el valor de la propiedad listaPaisesAduanasResult.
     * 
     * @return
     *     possible object is
     *     {@link PaisesAduanas }
     *     
     */
    public PaisesAduanas getListaPaisesAduanasResult() {
        return listaPaisesAduanasResult;
    }

    /**
     * Define el valor de la propiedad listaPaisesAduanasResult.
     * 
     * @param value
     *     allowed object is
     *     {@link PaisesAduanas }
     *     
     */
    public void setListaPaisesAduanasResult(PaisesAduanas value) {
        this.listaPaisesAduanasResult = value;
    }

}
