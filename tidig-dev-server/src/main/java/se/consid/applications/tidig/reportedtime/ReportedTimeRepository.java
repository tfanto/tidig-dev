package se.consid.applications.tidig.reportedtime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ReportedTimeRepository  extends JpaRepository<ReportedTime, ReportedTimeKey> {



}
