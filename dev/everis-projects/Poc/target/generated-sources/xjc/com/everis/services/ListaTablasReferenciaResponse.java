//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.13 at 12:04:41 PM ART 
//


package com.everis.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListaTablasReferenciaResult" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}TablasReferencia" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the listaTablasReferenciaResult property.
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
     * Sets the value of the listaTablasReferenciaResult property.
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
