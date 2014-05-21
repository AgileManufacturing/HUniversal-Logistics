
package handler.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addCells", namespace = "http://handler/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addCells", namespace = "http://handler/")
public class AddCells {

    @XmlElement(name = "cells", namespace = "")
    private List<object.Cell> cells;

    /**
     * 
     * @return
     *     returns List<Cell>
     */
    public List<object.Cell> getCells() {
        return this.cells;
    }

    /**
     * 
     * @param cells
     *     the value for the cells property
     */
    public void setCells(List<object.Cell> cells) {
        this.cells = cells;
    }

}
