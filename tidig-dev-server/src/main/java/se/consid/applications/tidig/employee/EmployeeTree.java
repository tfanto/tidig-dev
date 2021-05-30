package se.consid.applications.tidig.employee;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import se.consid.applications.tidig.api.dto.EmployeeSimpleDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@nodeid")
public class EmployeeTree implements Serializable {

    private String empId;
    private String name;
    //private EmployeeSimpleDto data = null;
    private List<EmployeeTree> children = new ArrayList<>();
    private EmployeeTree parent = null;

    public EmployeeTree() {
    }

    public EmployeeTree(Employee employee) {

        this.empId = employee.getEmpId();
        this.name  = employee.getFirstName() + " " + employee.getSurName();
    }

    public void addChild(EmployeeTree child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Employee data) {
        EmployeeTree newChild = new EmployeeTree(data);
        this.addChild(newChild);
    }

    public void addChildren(List<EmployeeTree> children) {
        for(EmployeeTree t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public List<EmployeeTree> getChildren() {
        return children;
    }

    private void setParent(EmployeeTree parent) {
        this.parent = parent;
    }

    public EmployeeTree getParent() {
        return parent;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildren(List<EmployeeTree> children) {
        this.children = children;
    }
}
