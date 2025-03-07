package testedeapi.com.dto;


public record UserDto(
    String nome,
    String email, 
    String registroAcademico,
    String telefone, 
    Boolean ativo
    ) {
} 
