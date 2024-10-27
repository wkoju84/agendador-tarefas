package com.william.agendadortarefas.business.dtos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private String email;
    private String senha;

}
