package mk.finki.diplomska.rabota.diplomska.repository;


import mk.finki.diplomska.rabota.diplomska.models.Skill;
import mk.finki.diplomska.rabota.diplomska.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {


    @Query("select  case when count(s)>0 then true else false end from StudentUser u,Subject s where  u.id = :id and  s.name like :name ")
    boolean DoesExistByNameStudent(String name,Long id);

   Subject findByName(String name);

   boolean existsByName(String name);
}
