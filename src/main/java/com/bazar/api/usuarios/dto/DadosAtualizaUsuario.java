package com.bazar.api.usuarios.dto;

import com.bazar.api.usuarios.model.Usuario;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaUsuario(
        @NotNull
        Long id,
        String nome,
        String email,
        DadosEndereco endereco) {
}
