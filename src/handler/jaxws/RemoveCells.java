
package handler.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "removeCells", namespace = "http://handler/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeCells", namespace = "http://handler/")
public class RemoveCells {

    @XmlElement(name = "cellIDs", namespace = "")
    private List<Integer> cellIDs;

    /**
     * 
     * @return
     *     returns List<Integer>
     */
    public List<Integer> getCellIDs() {
        return this.cellIDs;
    }

    /**
     * 
     * @param cellIDs
     *     the value for the cellIDs property
     */
    public void setCellIDs(List<Integer> cellIDs) {
        this.cellIDs = cellIDs;
    }

}
