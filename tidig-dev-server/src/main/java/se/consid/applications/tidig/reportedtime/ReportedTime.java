package se.consid.applications.tidig.reportedtime;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "reportedtime" )
public class ReportedTime {

    @EmbeddedId
    private ReportedTimeKey key;

    private Float reported_time;

    @Column(name="time_row_id")
    private Long time_row_id;

    @Column(name="is_submitted")
    private Boolean is_submitted;

    @Column(name="description")
    private String description;

    public ReportedTime(ReportedTimeKey key) {
        this.key = key;
    }
    public ReportedTime() {
        // must be
    }

    public ReportedTimeKey getKey() {
        return key;
    }

    public void setKey(ReportedTimeKey key) {
        this.key = key;
    }

    public Float getReportedTime() {
        return reported_time;
    }

    public void setReportedTime(Float reportedTime) {
        this.reported_time = reportedTime;
    }


    public Long getTime_row_id() {
        return time_row_id;
    }

    public void setTime_row_id(Long time_row_id) {
        this.time_row_id = time_row_id;
    }

    public Boolean getIs_submitted() {
        return is_submitted;
    }

    public void setIs_submitted(Boolean is_submitted) {
        this.is_submitted = is_submitted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportedTime that = (ReportedTime) o;
        return Objects.equals(key, that.key) && Objects.equals(reported_time, that.reported_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, reported_time);
    }

    @Override
    public String toString() {
        return "ReportedTime{" +
                 key +
                ", reported_time=" + reported_time +
                ", time_row_id=" + time_row_id +
                ", is_submitted=" + is_submitted +
                ", description='" + description + '\'' +
                '}';
    }
}
