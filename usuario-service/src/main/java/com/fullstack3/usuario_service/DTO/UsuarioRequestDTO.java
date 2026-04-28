package com.fullstack3.usuario_service.DTO;

import com.fullstack3.usuario_service.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {
    private String run;
    private Roles rol;

}
