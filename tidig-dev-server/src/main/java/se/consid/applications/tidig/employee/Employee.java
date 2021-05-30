package se.consid.applications.tidig.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "employee" )
public class Employee implements Serializable {

    @Id
    @Column(name = "emp_id")
    private String empId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "sur_name")
    private String surName;
    @Column(name = "chief_emp_id")
    private String chiefEmpId;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getChiefEmpId() {
        return chiefEmpId;
    }

    public void setChiefEmpId(String chiefEmpId) {
        this.chiefEmpId = chiefEmpId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empId, employee.empId) && Objects.equals(firstName, employee.firstName) && Objects.equals(surName, employee.surName) && Objects.equals(chiefEmpId, employee.chiefEmpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, firstName, surName, chiefEmpId);
    }
}
