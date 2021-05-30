package se.consid.applications.tidig.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

     List<Employee> findAllByChiefEmpId(String empId);

     Employee getEmployeeByEmpId(String empId);

}
