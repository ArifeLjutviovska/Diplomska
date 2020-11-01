package mk.finki.diplomska.rabota.diplomska.repository;


import mk.finki.diplomska.rabota.diplomska.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skill,Long> {
}
