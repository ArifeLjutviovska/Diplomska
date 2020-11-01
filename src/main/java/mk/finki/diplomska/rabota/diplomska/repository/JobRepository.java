package mk.finki.diplomska.rabota.diplomska.repository;

import mk.finki.diplomska.rabota.diplomska.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
}
