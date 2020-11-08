package mk.finki.diplomska.rabota.diplomska.repository;


import mk.finki.diplomska.rabota.diplomska.models.Skill;
import mk.finki.diplomska.rabota.diplomska.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {


    @Query("select  case when count(s)>0 then true else false end from Subject s,StudentUser u where  s.name like :name  and u.id = :id ")
    boolean ExistByNameStudent(String name,Long id);

   Subject findByName(String name);

   boolean existsByName(String name);
}
