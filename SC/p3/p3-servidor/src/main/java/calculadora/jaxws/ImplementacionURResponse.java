
package calculadora.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.6.1
 * Sun Dec 10 11:35:06 CET 2023
 * Generated source version: 3.6.1
 */

@XmlRootElement(name = "implementacionURResponse", namespace = "http://calculadora/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "implementacionURResponse", namespace = "http://calculadora/")

public class ImplementacionURResponse {

    @XmlElement(name = "return")
    private double _return;

    public double getReturn() {
        return this._return;
    }

    public void setReturn(double new_return)  {
        this._return = new_return;
    }

}

