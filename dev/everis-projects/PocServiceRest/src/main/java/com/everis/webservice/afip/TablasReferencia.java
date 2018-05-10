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
 * <p>Clase Java para TablasReferencia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TablasReferencia"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{ar.gov.afip.dia.serviciosweb.wgesTabRef}Contenedor"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TablasReferencia" type="{ar.gov.afip.dia.serviciosweb.wgesTabRef}ArrayOfTablaReferencia" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TablasReferencia", propOrder = {
    "tablasReferencia"
})
public class TablasReferencia
    extends Contenedor
{

    @XmlElement(name = "TablasReferencia")
    protected ArrayOfTablaReferencia tablasReferencia;

    /**
     * Obtiene el valor de la propiedad tablasReferencia.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTablaReferencia }
     *     
     */
    public ArrayOfTablaReferencia getTablasReferencia() {
        return tablasReferencia;
    }

    /**
     * Define el valor de la propiedad tablasReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTablaReferencia }
     *     
     */
    public void setTablasReferencia(ArrayOfTablaReferencia value) {
        this.tablasReferencia = value;
    }

}
