package se.consid.applications.tidig.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  ProjectRepository  extends JpaRepository<Project, ProjectKey> {


}
