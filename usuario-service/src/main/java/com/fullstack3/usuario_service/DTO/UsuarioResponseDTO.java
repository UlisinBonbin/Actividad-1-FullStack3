package com.fullstack3.usuario_service.DTO;

import com.fullstack3.usuario_service.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Long id;
    private String run;
    private Roles rol;
}
