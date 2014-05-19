
package handler.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getRoutesResponse", namespace = "http://handler/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRoutesResponse", namespace = "http://handler/")
public class GetRoutesResponse {

    @XmlElement(name = "return", namespace = "")
    private List<object.Route> _return;

    /**
     * 
     * @return
     *     returns List<Route>
     */
    public List<object.Route> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<object.Route> _return) {
        this._return = _return;
    }

}
