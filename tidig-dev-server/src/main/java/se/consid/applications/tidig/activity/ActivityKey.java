package se.consid.applications.tidig.activity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ActivityKey implements Serializable {

    @Column(name = "customer_id")
    private Long customer_id;

    @Column(name = "project_id")
    private Long project_id;

    @Column(name = "activity_id")
    private Long activity_id;

    public ActivityKey() {
    }

    public ActivityKey(Long customer_id, Long project_id, Long activity_id) {
        this.customer_id = customer_id;
        this.project_id = project_id;
        this.activity_id = activity_id;
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

    public Long getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Long activity_id) {
        this.activity_id = activity_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityKey that = (ActivityKey) o;
        return Objects.equals(customer_id, that.customer_id) && Objects.equals(project_id, that.project_id) && Objects.equals(activity_id, that.activity_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id, project_id, activity_id);
    }
}
