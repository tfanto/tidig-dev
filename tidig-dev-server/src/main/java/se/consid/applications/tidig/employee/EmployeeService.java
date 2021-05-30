package se.consid.applications.tidig.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

    @Autowired
    private ApiKey2EmpIdRepository apiKey2EmpIdRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public String getEmpNoFromApiKey(String apiKey) {

        Optional<ApiKey2EmpId> empId = apiKey2EmpIdRepository.findById(apiKey);
        if (empId.isEmpty()) return null;
        return empId.get().getEmpId();
    }


    /**
     *
     * @param apiUser
     * @return
     */
    public EmployeeTree buildTree(String apiUser) {

        Employee employee = employeeRepository.getEmployeeByEmpId(apiUser);
        EmployeeTree tree = new EmployeeTree(employee);
        tree = buildTree(employee.getEmpId(), tree);
        return tree;
    }

    /**
     *
     * @param empId
     * @param tree
     * @return
     */
    private EmployeeTree buildTree(String empId, EmployeeTree tree) {

        List<Employee> listResult = employeeRepository.findAllByChiefEmpId(empId);
        if (listResult.isEmpty()) {
            return tree;
        }
        for (Employee employee : listResult) {
            String id = employee.getEmpId();
            EmployeeTree node = new EmployeeTree(employee);
            tree.addChild(node);
            buildTree(id, node);
        }
        return tree;
    }

    /** flat version of tree
     *
     * @param apiUser
     * @return
     */

    public List<Employee> getApiEmployeeTimePermission(String apiUser) {

        Employee employee = employeeRepository.getEmployeeByEmpId(apiUser);
        List<Employee> list =  new ArrayList<>();
        list = getApiEmployeeTimePermission(employee.getEmpId(), list);
        return list;
    }

    private List<Employee> getApiEmployeeTimePermission(String empId, List<Employee> list) {

        List<Employee> listResult = employeeRepository.findAllByChiefEmpId(empId);
        if (listResult.isEmpty()) {
            return list;
        }
        for (Employee employee : listResult) {
            list.add(employee);
            getApiEmployeeTimePermission(employee.getEmpId(), list);
        }
        return list;



    }
}
