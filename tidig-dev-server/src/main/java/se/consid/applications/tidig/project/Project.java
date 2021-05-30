package se.consid.applications.tidig.project;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project" )
public class Project {

    @EmbeddedId
    private ProjectKey key;

    public Project() {

    }
    public Project(ProjectKey key) {
        this.key = key;
    }

    @Column(name = "project_name")
    private String name;

    public ProjectKey getKey() {
        return key;
    }

    public void setKey(ProjectKey key) {
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
        Project project = (Project) o;
        return Objects.equals(key, project.key) && Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name);
    }
}
