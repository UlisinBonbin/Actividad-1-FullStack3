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
        if (request.getUsuarioId() != null) {
            try {
                usuarioClient.obtenerPorId(request.getUsuarioId());
            } catch (Exception e) {
                throw new RuntimeException("Error: El usuario con ID " + request.getUsuarioId() + " no existe.");
            }
        }
        return reportesClient.guardarReporte(request);
    }

    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO request) {
        return usuarioClient.crearUsuario(request);
    }

}
