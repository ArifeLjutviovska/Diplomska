package mk.finki.diplomska.rabota.diplomska.repository;



import mk.finki.diplomska.rabota.diplomska.models.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

    public DBFile findByFilename(String name);





}