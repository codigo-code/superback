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
 * <p>Clase Java para Opciones complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Opciones"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{ar.gov.afip.dia.serviciosweb.wgesTabRef}Contenedor"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Opciones" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}ArrayOfOpcion" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Opciones", propOrder = {
    "idReferencia",
    "opciones"
})
public class Opciones
    extends Contenedor
{

    @XmlElement(name = "IdReferencia")
    protected String idReferencia;
    @XmlElement(name = "Opciones")
    protected ArrayOfOpcion opciones;

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
     * Obtiene el valor de la propiedad opciones.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOpcion }
     *     
     */
    public ArrayOfOpcion getOpciones() {
        return opciones;
    }

    /**
     * Define el valor de la propiedad opciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOpcion }
     *     
     */
    public void setOpciones(ArrayOfOpcion value) {
        this.opciones = value;
    }

}
