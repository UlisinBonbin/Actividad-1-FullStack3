package com.fullstack3.usuario_service.service;

import com.fullstack3.usuario_service.DTO.UsuarioRequestDTO;
import com.fullstack3.usuario_service.DTO.UsuarioResponseDTO;
import com.fullstack3.usuario_service.model.Usuario;
import com.fullstack3.usuario_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Devuelve una lista de los usuarios dto
    public List<UsuarioResponseDTO> obtenerTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        // Transforma la lista de Entidades a una lista de DTOs usando Streams
        return usuarios.stream()
                .map(this::mapearAResponseDTO)
                .collect(Collectors.toList());
    }

    // Crear un usuario (Recibe RequestDTO, Retorna ResponseDTO)
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO requestDTO) {
        // Paso A: Convertir el DTO que llega desde el Controller a una Entidad
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setRun(requestDTO.getRun());
        nuevoUsuario.setRol(requestDTO.getRol());

        // Paso B: Guardar la Entidad en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        // Paso C: Convertir la Entidad recién guardada al DTO de respuesta y retornarlo
        return mapearAResponseDTO(usuarioGuardado);
    }

    // Método auxiliar privado para centralizar el mapeo de Entidad -> DTO
    private UsuarioResponseDTO mapearAResponseDTO(Usuario usuario) {
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(usuario.getId());
        responseDTO.setRun(usuario.getRun());
        responseDTO.setRol(usuario.getRol());
        return responseDTO;
    }
}