package testedeapi.com.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import testedeapi.com.repository.UserRepository;
import testedeapi.com.dto.UserDto;
import testedeapi.com.models.UserModel;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    @Transactional
    public Optional<UserDto> updateUser(Long id, UserDto userDto) {
        return userRepo.findById(id).map(user -> {
            user.setNome(userDto.nome());
            user.setEmail(userDto.email());
            user.setTelefone(userDto.telefone());
            user.setAtivo(userDto.ativo());
            UserModel updatedUser = userRepo.save(user);
            return ConvertToDto(updatedUser);
        });
    }
    @Transactional(readOnly = true)
    public List<UserModel> getAllUser() {
        return userRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<UserModel> getById(Long id) {
        return userRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<UserModel> getByRegistroAcademico(String registroAcademico) {
        return userRepo.findByRegistroAcademico(registroAcademico);
    }

    @Transactional
    public UserDto createUser(UserModel userModel) {
        if (userRepo.existsByEmail(userModel.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (userRepo.existsByRegistroAcademico(userModel.getRegistroAcademico())) {
            throw new IllegalArgumentException("Registro acadêmico já cadastrado");
        }
        userModel.setSenha(userModel.getSenha()); 
        UserModel savedUser = userRepo.save(userModel);
        return ConvertToDto(savedUser);
        
    }
    

    @Transactional
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    private UserDto ConvertToDto(UserModel user) {
        return new UserDto(
            user.getNome(),
            user.getEmail(),
            user.getRegistroAcademico(),
            user.getTelefone(),
            user.getAtivo()
        );

  
}
}
