package testedeapi.com.service;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testedeapi.com.repository.UserRepository;
import testedeapi.com.dto.UserUpdateDto;
import testedeapi.com.dto.UserRequestDto;
import testedeapi.com.dto.UserResponseDto;
import testedeapi.com.exception.UserException;
import testedeapi.com.mapper.UserMapper;
import testedeapi.com.models.Role;
import testedeapi.com.models.UserModel;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        UserModel user = userRepo.findById(id)
                .orElseThrow(() -> new UserException("Usuário não encontrado"));

        user.setNome(userUpdateDto.nome());
        user.setTelefone(userUpdateDto.telefone());
        user.setAtivo(userUpdateDto.ativo());

        UserModel updatedUser = userRepo.save(user);
        return UserMapper.toResponseDto(updatedUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUser() {
        return userRepo.findAll().stream()
                .map(UserMapper::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponseDto getById(Long id) {
        return userRepo.findById(id)
                .map(UserMapper::toResponseDto)
                .orElseThrow(() -> new UserException("Usuário não encontrado"));
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        validarUsuarioExistente(userRequestDto.email(), userRequestDto.registroAcademico());
        if (userRepo.existsByEmail(userRequestDto.email())) {
            throw new UserException("Email já cadastrado");
        }
        if (userRepo.existsByRegistroAcademico(userRequestDto.registroAcademico())) {
            throw new UserException("Registro acadêmico já cadastrado");
        }

        UserModel user = UserMapper.toEntity(userRequestDto);
        user.setSenha(passwordEncoder.encode(userRequestDto.senha()));
        user.setRole(Role.USER);
        UserModel savedUser = userRepo.save(user);
        return UserMapper.toResponseDto(savedUser);
    }
    
    private void validarUsuarioExistente(String email, String registroAcademico) {
        if (userRepo.existsByEmail(email)){
            throw new UserException("Email já existente");
        }
        if (userRepo.existsByRegistroAcademico(registroAcademico)) {
            throw new UserException("Registro Acadêmico já existente");
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new UserException("Usuário não encontrado");
        }
        userRepo.deleteById(id);
    }
}
