
package handler.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateTransportAgents", namespace = "http://handler/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateTransportAgents", namespace = "http://handler/")
public class UpdateTransportAgents {

    @XmlElement(name = "transportAgents", namespace = "")
    private List<object.TransportAgent> transportAgents;

    /**
     * 
     * @return
     *     returns List<TransportAgent>
     */
    public List<object.TransportAgent> getTransportAgents() {
        return this.transportAgents;
    }

    /**
     * 
     * @param transportAgents
     *     the value for the transportAgents property
     */
    public void setTransportAgents(List<object.TransportAgent> transportAgents) {
        this.transportAgents = transportAgents;
    }

}
