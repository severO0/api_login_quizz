package testedeapi.com.dto;

public record UserRequestDto(
    String nome,
    String email,
    String telefone,
    String registroAcademico,
    String senha
) {}

