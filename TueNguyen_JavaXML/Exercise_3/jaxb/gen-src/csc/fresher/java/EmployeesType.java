//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.16 at 04:59:49 PM ICT 
//


package csc.fresher.java;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for employeesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employeesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employee" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="fullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="yearsOfExperience">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *                         &lt;maxExclusive value="100"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element ref="{}comment" minOccurs="0"/>
 *                   &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="division" use="required" type="{}SKU" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeesType", propOrder = {
    "employee"
})
public class EmployeesType {

    @XmlElement(required = true)
    protected List<EmployeesType.Employee> employee;

    /**
     * Gets the value of the employee property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employee property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployee().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeesType.Employee }
     * 
     * 
     */
    public List<EmployeesType.Employee> getEmployee() {
        if (employee == null) {
            employee = new ArrayList<EmployeesType.Employee>();
        }
        return this.employee;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="fullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="yearsOfExperience">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
     *               &lt;maxExclusive value="100"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element ref="{}comment" minOccurs="0"/>
     *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="division" use="required" type="{}SKU" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "fullName",
        "yearsOfExperience",
        "title",
        "comment",
        "dateOfBirth"
    })
    public static class Employee {

        @XmlElement(required = true)
        protected String fullName;
        protected int yearsOfExperience;
        @XmlElement(required = true)
        protected String title;
        protected String comment;
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dateOfBirth;
        @XmlAttribute(name = "division", required = true)
        protected String division;

        /**
         * Gets the value of the fullName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFullName() {
            return fullName;
        }

        /**
         * Sets the value of the fullName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFullName(String value) {
            this.fullName = value;
        }

        /**
         * Gets the value of the yearsOfExperience property.
         * 
         */
        public int getYearsOfExperience() {
            return yearsOfExperience;
        }

        /**
         * Sets the value of the yearsOfExperience property.
         * 
         */
        public void setYearsOfExperience(int value) {
            this.yearsOfExperience = value;
        }

        /**
         * Gets the value of the title property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTitle() {
            return title;
        }

        /**
         * Sets the value of the title property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTitle(String value) {
            this.title = value;
        }

        /**
         * Gets the value of the comment property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getComment() {
            return comment;
        }

        /**
         * Sets the value of the comment property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setComment(String value) {
            this.comment = value;
        }

        /**
         * Gets the value of the dateOfBirth property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateOfBirth() {
            return dateOfBirth;
        }

        /**
         * Sets the value of the dateOfBirth property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateOfBirth(XMLGregorianCalendar value) {
            this.dateOfBirth = value;
        }

        /**
         * Gets the value of the division property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDivision() {
            return division;
        }

        /**
         * Sets the value of the division property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDivision(String value) {
            this.division = value;
        }

    }

}
