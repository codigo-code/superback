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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Contenedor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Contenedor"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodError" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="InfoAdicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Contenedor", propOrder = {
    "codError",
    "infoAdicional"
})
@XmlSeeAlso({
    Descripciones.class,
    Vigencias.class,
    PaisesAduanas.class,
    Empresas.class,
    Opciones.class,
    LugaresOperativos.class,
    TablasReferencia.class,
    DescripcionesCodificaciones.class,
    FechaUltAct.class
})
public class Contenedor {

    @XmlElement(name = "CodError")
    protected int codError;
    @XmlElement(name = "InfoAdicional")
    protected String infoAdicional;

    /**
     * Obtiene el valor de la propiedad codError.
     * 
     */
    public int getCodError() {
        return codError;
    }

    /**
     * Define el valor de la propiedad codError.
     * 
     */
    public void setCodError(int value) {
        this.codError = value;
    }

    /**
     * Obtiene el valor de la propiedad infoAdicional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfoAdicional() {
        return infoAdicional;
    }

    /**
     * Define el valor de la propiedad infoAdicional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfoAdicional(String value) {
        this.infoAdicional = value;
    }

}
