package testedeapi.com.dto;


public record UserUpdateDto(
    String nome,
    String email, 
    String registroAcademico,
    String telefone, 
    Boolean ativo
    ) {
} 
