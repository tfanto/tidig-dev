package se.consid.applications.tidig.activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, ActivityKey> {
}
