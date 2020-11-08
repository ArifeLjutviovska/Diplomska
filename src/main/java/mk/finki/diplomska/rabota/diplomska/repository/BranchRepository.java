package mk.finki.diplomska.rabota.diplomska.repository;

import mk.finki.diplomska.rabota.diplomska.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {

    boolean existsByName(String name);
    Branch findByName(String name);
}
