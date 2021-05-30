package se.consid.applications.tidig.reportedtime;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class ReportedTimeKey implements Serializable {

    @Column(name="customer_id")
    private Long customer_id;

    @Column(name="project_id")
    private Long project_id;

    @Column(name="activity_id")
    private Long activity_id;

    @Column(name="emp_id")
    private String emp_id;

    @Column(name="reported_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
    private LocalDate reported_date;

    public ReportedTimeKey() {
    }

    public ReportedTimeKey(Long customer_id, Long project_id, Long activity_id, String emp_id, LocalDate reported_date) {
        this.customer_id = customer_id;
        this.project_id = project_id;
        this.activity_id = activity_id;
        this.emp_id = emp_id;
        this.reported_date = reported_date;
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

    public Long getActivityId() {
        return activity_id;
    }

    public void setActivityId(Long activity_id) {
        this.activity_id = activity_id;
    }

    public String getEmpId() {
        return emp_id;
    }

    public void setEmpId(String emp_id) {
        this.emp_id = emp_id;
    }

    public LocalDate getReportedDate() {
        return reported_date;
    }

    public void setReportedDate(LocalDate reported_date) {
        this.reported_date = reported_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportedTimeKey that = (ReportedTimeKey) o;
        return Objects.equals(customer_id, that.customer_id) && Objects.equals(project_id, that.project_id) && Objects.equals(activity_id, that.activity_id) && Objects.equals(emp_id, that.emp_id) && Objects.equals(reported_date, that.reported_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id, project_id, activity_id, emp_id, reported_date);
    }


    @Override
    public String toString() {
        return
                "customer_id=" + customer_id +
                ", project_id=" + project_id +
                ", activity_id='" + activity_id + '\'' +
                ", emp_id='" + emp_id + '\'' +
                ", reported_date=" + reported_date ;
    }
}
