package testedeapi.com.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import testedeapi.com.models.*;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByRegistroAcademico(String registroAcademico);
    boolean existsByEmail(String email); 
    boolean existsByRegistroAcademico(String registroAcademico);
    
}
