package se.consid.applications.tidig.api.dto;

import se.consid.applications.tidig.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListSimpleDto {
    List<EmployeeSimpleDto> users = new ArrayList<>();

    public void addEmployees(Employee emp){
        EmployeeSimpleDto simple = new EmployeeSimpleDto();
        simple.setEmpId(emp.getEmpId());
        simple.setName( emp.getFirstName() + " " + emp.getSurName());
        users.add(simple);
    }

    public List<EmployeeSimpleDto> getUsers() {
        return users;
    }

    public void setUsers(List<EmployeeSimpleDto> users) {
        this.users = users;
    }
}
