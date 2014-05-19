
package handler.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getCellsResponse", namespace = "http://handler/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCellsResponse", namespace = "http://handler/")
public class GetCellsResponse {

    @XmlElement(name = "return", namespace = "")
    private List<object.Cell> _return;

    /**
     * 
     * @return
     *     returns List<Cell>
     */
    public List<object.Cell> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<object.Cell> _return) {
        this._return = _return;
    }

}
