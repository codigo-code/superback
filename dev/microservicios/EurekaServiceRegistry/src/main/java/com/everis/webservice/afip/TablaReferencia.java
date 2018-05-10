//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.05.07 a las 05:32:21 PM ART 
//


package com.everis.webservice.afip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TablaReferencia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TablaReferencia"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdTabRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TabRefDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="WebMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TablaReferencia", propOrder = {
    "idTabRef",
    "tabRefDesc",
    "webMethod"
})
public class TablaReferencia {

    @XmlElement(name = "IdTabRef")
    protected String idTabRef;
    @XmlElement(name = "TabRefDesc")
    protected String tabRefDesc;
    @XmlElement(name = "WebMethod")
    protected String webMethod;

    /**
     * Obtiene el valor de la propiedad idTabRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTabRef() {
        return idTabRef;
    }

    /**
     * Define el valor de la propiedad idTabRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTabRef(String value) {
        this.idTabRef = value;
    }

    /**
     * Obtiene el valor de la propiedad tabRefDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTabRefDesc() {
        return tabRefDesc;
    }

    /**
     * Define el valor de la propiedad tabRefDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTabRefDesc(String value) {
        this.tabRefDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad webMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebMethod() {
        return webMethod;
    }

    /**
     * Define el valor de la propiedad webMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebMethod(String value) {
        this.webMethod = value;
    }

}
