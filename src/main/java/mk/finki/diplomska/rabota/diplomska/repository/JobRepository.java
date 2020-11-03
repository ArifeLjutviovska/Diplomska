package mk.finki.diplomska.rabota.diplomska.repository;

import mk.finki.diplomska.rabota.diplomska.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {


    @Query("select case when  count(j)>0 then true else false end  from Job j,CompanyUser c where j.title like :title and c.id = :id")
    boolean existByTitle(String title,Long id);
}
