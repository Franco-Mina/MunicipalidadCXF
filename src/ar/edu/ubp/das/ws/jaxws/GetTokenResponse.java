
package ar.edu.ubp.das.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.3.6
 * Sun Aug 23 16:18:28 ART 2020
 * Generated source version: 3.3.6
 */

@XmlRootElement(name = "getTokenResponse", namespace = "http://ws.das.ubp.edu.ar/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTokenResponse", namespace = "http://ws.das.ubp.edu.ar/")

public class GetTokenResponse {

    @XmlElement(name = "return")
    private ar.edu.ubp.das.bean.ws.TokenResponseBean _return;

    public ar.edu.ubp.das.bean.ws.TokenResponseBean getReturn() {
        return this._return;
    }

    public void setReturn(ar.edu.ubp.das.bean.ws.TokenResponseBean new_return)  {
        this._return = new_return;
    }

}

