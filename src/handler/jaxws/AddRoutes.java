
package handler.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addRoutes", namespace = "http://handler/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addRoutes", namespace = "http://handler/")
public class AddRoutes {

    @XmlElement(name = "routes", namespace = "")
    private List<object.Route> routes;

    /**
     * 
     * @return
     *     returns List<Route>
     */
    public List<object.Route> getRoutes() {
        return this.routes;
    }

    /**
     * 
     * @param routes
     *     the value for the routes property
     */
    public void setRoutes(List<object.Route> routes) {
        this.routes = routes;
    }

}
