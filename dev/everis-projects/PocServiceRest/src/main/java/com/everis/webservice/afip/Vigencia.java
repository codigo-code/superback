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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Vigencia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Vigencia"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{ar.gov.afip.dia.serviciosweb.wgesTabRef}Descripcion"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="VigenciaDesde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VigenciaHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vigencia", propOrder = {
    "vigenciaDesde",
    "vigenciaHasta"
})
@XmlSeeAlso({
    PaisAduana.class,
    Opcion.class,
    LugarOperativo.class
})
public class Vigencia
    extends Descripcion
{

    @XmlElement(name = "VigenciaDesde")
    protected String vigenciaDesde;
    @XmlElement(name = "VigenciaHasta")
    protected String vigenciaHasta;

    /**
     * Obtiene el valor de la propiedad vigenciaDesde.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVigenciaDesde() {
        return vigenciaDesde;
    }

    /**
     * Define el valor de la propiedad vigenciaDesde.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVigenciaDesde(String value) {
        this.vigenciaDesde = value;
    }

    /**
     * Obtiene el valor de la propiedad vigenciaHasta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVigenciaHasta() {
        return vigenciaHasta;
    }

    /**
     * Define el valor de la propiedad vigenciaHasta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVigenciaHasta(String value) {
        this.vigenciaHasta = value;
    }

}
