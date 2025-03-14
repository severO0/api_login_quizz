package testedeapi.com.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import testedeapi.com.models.*;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByRegistroAcademico(String registroAcademico);
    UserDetails findByLogin(String login);
    boolean existsByEmail(String email); 
    boolean existsByRegistroAcademico(String registroAcademico);
    
}
