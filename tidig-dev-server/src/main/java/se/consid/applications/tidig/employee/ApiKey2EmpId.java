package se.consid.applications.tidig.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "apikey2empid" )
public class ApiKey2EmpId {

    @Id
    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "emp_id")
    private String empId;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiKey2EmpId that = (ApiKey2EmpId) o;
        return Objects.equals(apiKey, that.apiKey) && Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, empId);
    }
}
