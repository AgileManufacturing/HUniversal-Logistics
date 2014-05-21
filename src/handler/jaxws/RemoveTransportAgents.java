
package handler.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "removeTransportAgents", namespace = "http://handler/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeTransportAgents", namespace = "http://handler/")
public class RemoveTransportAgents {

    @XmlElement(name = "transportAgentIDs", namespace = "")
    private List<Integer> transportAgentIDs;

    /**
     * 
     * @return
     *     returns List<Integer>
     */
    public List<Integer> getTransportAgentIDs() {
        return this.transportAgentIDs;
    }

    /**
     * 
     * @param transportAgentIDs
     *     the value for the transportAgentIDs property
     */
    public void setTransportAgentIDs(List<Integer> transportAgentIDs) {
        this.transportAgentIDs = transportAgentIDs;
    }

}
