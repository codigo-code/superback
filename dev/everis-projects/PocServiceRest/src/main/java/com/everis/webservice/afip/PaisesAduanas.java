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
 * <p>Clase Java para PaisesAduanas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PaisesAduanas"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{ar.gov.afip.dia.serviciosweb.wgesTabRef}Contenedor"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaisesAduanas" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}ArrayOfPaisAduana" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaisesAduanas", propOrder = {
    "idReferencia",
    "paisesAduanas"
})
public class PaisesAduanas
    extends Contenedor
{

    @XmlElement(name = "IdReferencia")
    protected String idReferencia;
    @XmlElement(name = "PaisesAduanas")
    protected ArrayOfPaisAduana paisesAduanas;

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

    /**
     * Obtiene el valor de la propiedad paisesAduanas.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPaisAduana }
     *     
     */
    public ArrayOfPaisAduana getPaisesAduanas() {
        return paisesAduanas;
    }

    /**
     * Define el valor de la propiedad paisesAduanas.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPaisAduana }
     *     
     */
    public void setPaisesAduanas(ArrayOfPaisAduana value) {
        this.paisesAduanas = value;
    }

}
