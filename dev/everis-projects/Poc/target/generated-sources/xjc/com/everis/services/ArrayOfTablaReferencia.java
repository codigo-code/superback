//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.13 at 12:04:41 PM ART 
//


package com.everis.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTablaReferencia complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTablaReferencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TablaReferencia" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}TablaReferencia" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTablaReferencia", propOrder = {
    "tablaReferencia"
})
public class ArrayOfTablaReferencia {

    @XmlElement(name = "TablaReferencia", nillable = true)
    protected List<TablaReferencia> tablaReferencia;

    /**
     * Gets the value of the tablaReferencia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tablaReferencia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTablaReferencia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TablaReferencia }
     * 
     * 
     */
    public List<TablaReferencia> getTablaReferencia() {
        if (tablaReferencia == null) {
            tablaReferencia = new ArrayList<TablaReferencia>();
        }
        return this.tablaReferencia;
    }

}