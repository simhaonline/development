/*******************************************************************************
 *  Copyright FUJITSU LIMITED 2016 
 *******************************************************************************/

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.05 at 01:29:11 PM CET 
//

package org.oscm.billingservice.business.model.billingresult;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Fine-grained list of user assignment costs, based on particular users. Every
 * entry represents the costs related to one particular user.
 * 
 * <p>
 * Java class for UserAssignmentCostsByUserType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="UserAssignmentCostsByUserType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="factor" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="userId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserAssignmentCostsByUserType")
public class UserAssignmentCostsByUserType {

    @XmlAttribute(required = true)
    protected BigDecimal factor;

    @XmlAttribute(required = true)
    protected String userId;

    /**
     * Gets the value of the factor property.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getFactor() {
        return factor;
    }

    /**
     * Sets the value of the factor property.
     * 
     * @param value
     *            allowed object is {@link BigDecimal }
     * 
     */
    public void setFactor(BigDecimal value) {
        this.factor = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setUserId(String value) {
        this.userId = value;
    }

}
