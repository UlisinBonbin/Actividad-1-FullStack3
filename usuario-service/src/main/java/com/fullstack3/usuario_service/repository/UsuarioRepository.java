package com.fullstack3.usuario_service.repository;

import com.fullstack3.usuario_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
