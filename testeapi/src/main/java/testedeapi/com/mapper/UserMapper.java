package testedeapi.com.mapper;

import testedeapi.com.dto.UserRequestDto;
import testedeapi.com.dto.UserResponseDto;
import testedeapi.com.models.UserModel;

public class UserMapper {

    public static UserResponseDto toResponseDto(UserModel user) {
        return new UserResponseDto(
            user.getId(),
            user.getNome(),
            user.getEmail(),
            user.getTelefone(),
            user.getRegistroAcademico(),
            user.getAtivo()
        );
    }

    public static UserModel toEntity(UserRequestDto requestDto) {
        return UserModel.builder()
            .nome(requestDto.nome())
            .email(requestDto.email())
            .telefone(requestDto.telefone())
            .registroAcademico(requestDto.registroAcademico())
            .senha(requestDto.senha())  // Senha será criptografada antes de salvar
            .ativo(true)  // Definir como ativo por padrão
            .build();
    }
}
