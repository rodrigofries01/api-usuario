package com.fries.api_usuario.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        Long id,

        @NotNull(message = "Nome é obrigatório")
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 100, message = "Name deve ter entre 3 e 100 caracteres")
        String name,

        @NotNull(message = "E-mail é obrigatório")
        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "Insira um e-mail válido")
        String email,

        @NotNull(message = "Senha é obrigatória")
        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, max = 10, message = "A senha deve conter no minimo 6 caracteres e no maximo 10")
        String password
){
}
