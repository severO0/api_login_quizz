package testedeapi.com.dto;

public record UserResponseDto(
    Long id,
    String nome,
    String email,
    String telefone,
    String registroAcademico,
    Boolean ativo
) {}

