package testedeapi.com.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import testedeapi.com.models.*;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    UserDetails findByRegistroAcademico(String registroAcademicoString);
    boolean existsByEmail(String email); 
    boolean existsByRegistroAcademico(String registroAcademico);
    
}
