package se.consid.applications.tidig.activity;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "activity" )
public class Activity {

    @EmbeddedId
    private ActivityKey key;

    @Column(name = "activity_name")
    private String name;

    public Activity() {
    }

    public Activity(ActivityKey key) {
        this.key = key;
    }

    public ActivityKey getKey() {
        return key;
    }

    public void setKey(ActivityKey key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(key, activity.key) && Objects.equals(name, activity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name);
    }
}
