package mk.finki.diplomska.rabota.diplomska.repository;

import mk.finki.diplomska.rabota.diplomska.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguagesRepository extends JpaRepository<Language,Long> {

    boolean existsByName(String name);

    Optional<Language> findByName(String name);
}
