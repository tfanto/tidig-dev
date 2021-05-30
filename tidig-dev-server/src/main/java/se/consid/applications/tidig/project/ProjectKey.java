package se.consid.applications.tidig.project;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectKey implements Serializable {

    @Column(name = "customer_id")
    private Long customer_id;

    @Column(name = "project_id")
    private Long project_id;

    public ProjectKey() {
        // empty ctor
    }

    public ProjectKey(Long customer_id, Long project_id) {
        this.customer_id = customer_id;
        this.project_id = project_id;
    }

    public Long getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getProjectId() {
        return project_id;
    }

    public void setProjectId(Long project_id) {
        this.project_id = project_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectKey that = (ProjectKey) o;
        return Objects.equals(customer_id, that.customer_id) && Objects.equals(project_id, that.project_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id, project_id);
    }
}
