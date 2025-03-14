package testedeapi.com.dto;
import testedeapi.com.models.Role;

public record UserLoginDto(
    String registroAcademico, 
    String password,
    Role role
    ) {
    
}
