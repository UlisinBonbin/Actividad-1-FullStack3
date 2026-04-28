package com.fullstack3.bff_emergencias.service;
import com.fullstack3.bff_emergencias.DTO.UsuarioRequestDTO;
import com.fullstack3.bff_emergencias.DTO.UsuarioResponseDTO;
import com.fullstack3.bff_emergencias.client.ReportesClient;
import com.fullstack3.bff_emergencias.client.UsuarioClient;
import com.fullstack3.bff_emergencias.DTO.ReporteRequestDTO;
import com.fullstack3.bff_emergencias.DTO.ReporteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergenciaOrchestratorService {

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private ReportesClient reportesClient;

    public ReporteResponseDTO procesarReporte(ReporteRequestDTO request) {

        // Si NO es anónimo, debemos obtener los datos del usuario
        if (Boolean.FALSE.equals(request.getAnonimo())) {

            if (request.getUsuarioId() == null) {
                throw new RuntimeException("Error: usuarioId es obligatorio cuando anonimo = false");
            }

            // 1. Obtener usuario desde usuario-service
            UsuarioResponseDTO usuario = usuarioClient.obtenerPorId(request.getUsuarioId());

            if (usuario == null) {
                throw new RuntimeException("Error: no existe el usuario con ID " + request.getUsuarioId());
            }

            // 2. Rellenar runCiudadano automáticamente
            request.setRunCiudadano(usuario.getRun());
        }

        // 3. Enviar reporte al microservicio de reportes
        return reportesClient.guardarReporte(request);
    }

    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO request) {
        return usuarioClient.crearUsuario(request);
    }
}


