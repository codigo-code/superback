//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.05.07 a las 05:32:21 PM ART 
//


package com.everis.webservice.afip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para WsDummyResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="WsDummyResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="appserver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dbserver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="authserver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsDummyResponse", propOrder = {
    "appserver",
    "dbserver",
    "authserver"
})
public class WsDummyResponse {

    protected String appserver;
    protected String dbserver;
    protected String authserver;

    /**
     * Obtiene el valor de la propiedad appserver.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppserver() {
        return appserver;
    }

    /**
     * Define el valor de la propiedad appserver.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppserver(String value) {
        this.appserver = value;
    }

    /**
     * Obtiene el valor de la propiedad dbserver.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbserver() {
        return dbserver;
    }

    /**
     * Define el valor de la propiedad dbserver.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbserver(String value) {
        this.dbserver = value;
    }

    /**
     * Obtiene el valor de la propiedad authserver.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthserver() {
        return authserver;
    }

    /**
     * Define el valor de la propiedad authserver.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthserver(String value) {
        this.authserver = value;
    }

}
