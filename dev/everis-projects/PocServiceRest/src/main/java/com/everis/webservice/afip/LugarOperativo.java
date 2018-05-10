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
 * <p>Clase Java para LugarOperativo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LugarOperativo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{ar.gov.afip.dia.serviciosweb.wgesTabRef}Vigencia"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Aduana" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LugarOperativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LugarOperativo", propOrder = {
    "aduana",
    "lugarOperativo"
})
public class LugarOperativo
    extends Vigencia
{

    @XmlElement(name = "Aduana")
    protected String aduana;
    @XmlElement(name = "LugarOperativo")
    protected String lugarOperativo;

    /**
     * Obtiene el valor de la propiedad aduana.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAduana() {
        return aduana;
    }

    /**
     * Define el valor de la propiedad aduana.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAduana(String value) {
        this.aduana = value;
    }

    /**
     * Obtiene el valor de la propiedad lugarOperativo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarOperativo() {
        return lugarOperativo;
    }

    /**
     * Define el valor de la propiedad lugarOperativo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarOperativo(String value) {
        this.lugarOperativo = value;
    }

}
