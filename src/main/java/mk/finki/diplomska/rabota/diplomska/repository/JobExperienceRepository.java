package mk.finki.diplomska.rabota.diplomska.repository;

import mk.finki.diplomska.rabota.diplomska.models.JobExperience;
import mk.finki.diplomska.rabota.diplomska.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobExperienceRepository extends JpaRepository<JobExperience,Long> {

    @Query("select s  from JobExperience s,StudentUser u where s.CompanyName like :companyName and s.jobIsFor like :jobIsFor and u.id = :id ")
    JobExperience findByNameStudent(String companyName, String jobIsFor,Long id);


}
