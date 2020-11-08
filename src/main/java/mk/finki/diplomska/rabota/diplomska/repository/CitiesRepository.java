package mk.finki.diplomska.rabota.diplomska.repository;

import mk.finki.diplomska.rabota.diplomska.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitiesRepository extends JpaRepository<City,Long> {

    boolean existsByName(String name);


    Optional<City> findByName(String name);
}
