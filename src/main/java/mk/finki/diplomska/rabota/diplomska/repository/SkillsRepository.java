package mk.finki.diplomska.rabota.diplomska.repository;


import mk.finki.diplomska.rabota.diplomska.models.Job;
import mk.finki.diplomska.rabota.diplomska.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skill,Long> {


    @Query("select s  from Skill s,StudentUser u where s.name like :name and u.id = :id")
    Skill findByNameStudent(String name,Long id);

    Skill findByName(String name);


    boolean existsByName(String name);


}
