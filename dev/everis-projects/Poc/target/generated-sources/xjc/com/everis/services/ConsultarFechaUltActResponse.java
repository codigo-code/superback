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
 *         &lt;element name="ConsultarFechaUltActResult" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}FechaUltAct" minOccurs="0"/>
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
    "consultarFechaUltActResult"
})
@XmlRootElement(name = "ConsultarFechaUltActResponse")
public class ConsultarFechaUltActResponse {

    @XmlElement(name = "ConsultarFechaUltActResult")
    protected FechaUltAct consultarFechaUltActResult;

    /**
     * Gets the value of the consultarFechaUltActResult property.
     * 
     * @return
     *     possible object is
     *     {@link FechaUltAct }
     *     
     */
    public FechaUltAct getConsultarFechaUltActResult() {
        return consultarFechaUltActResult;
    }

    /**
     * Sets the value of the consultarFechaUltActResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaUltAct }
     *     
     */
    public void setConsultarFechaUltActResult(FechaUltAct value) {
        this.consultarFechaUltActResult = value;
    }

}
