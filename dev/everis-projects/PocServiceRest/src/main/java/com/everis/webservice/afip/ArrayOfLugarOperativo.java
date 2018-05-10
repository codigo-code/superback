//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.04.23 a las 12:48:59 PM ART 
//


package com.everis.webservice.afip;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfLugarOperativo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfLugarOperativo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LugarOperativo" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}LugarOperativo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfLugarOperativo", propOrder = {
    "lugarOperativo"
})
public class ArrayOfLugarOperativo {

    @XmlElement(name = "LugarOperativo", nillable = true)
    protected List<LugarOperativo> lugarOperativo;

    /**
     * Gets the value of the lugarOperativo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lugarOperativo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLugarOperativo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LugarOperativo }
     * 
     * 
     */
    public List<LugarOperativo> getLugarOperativo() {
        if (lugarOperativo == null) {
            lugarOperativo = new ArrayList<LugarOperativo>();
        }
        return this.lugarOperativo;
    }

}
