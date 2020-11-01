package mk.finki.diplomska.rabota.diplomska.repository;


import mk.finki.diplomska.rabota.diplomska.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
