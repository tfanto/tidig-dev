package se.consid.applications.tidig.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface ApiKey2EmpIdRepository extends JpaRepository<ApiKey2EmpId, String> {
}
