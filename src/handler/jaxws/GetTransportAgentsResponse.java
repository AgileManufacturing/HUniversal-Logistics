
package handler.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getTransportAgentsResponse", namespace = "http://handler/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTransportAgentsResponse", namespace = "http://handler/")
public class GetTransportAgentsResponse {

    @XmlElement(name = "return", namespace = "")
    private List<object.TransportAgent> _return;

    /**
     * 
     * @return
     *     returns List<TransportAgent>
     */
    public List<object.TransportAgent> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<object.TransportAgent> _return) {
        this._return = _return;
    }

}
