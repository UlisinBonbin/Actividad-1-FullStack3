package com.fullstack3.bff_emergencias.service;

import com.fullstack3.bff_emergencias.client.ReportesClient;
import com.fullstack3.bff_emergencias.client.UsuarioClient;
import com.fullstack3.bff_emergencias.dto.ReporteRequestDTO;
import com.fullstack3.bff_emergencias.dto.ReporteResponseDTO;
import com.fullstack3.bff_emergencias.dto.UsuarioRequestDTO;
import com.fullstack3.bff_emergencias.dto.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergenciaOrchestratorService {

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private ReportesClient reportesClient;

    public ReporteResponseDTO procesarReporte(ReporteRequestDTO request) {

        // Validaciones básicas
        if (request.getAnonimo() == null) {
            throw new RuntimeException("El campo anonimo es obligatorio");
        }

        if (request.getLatitud() == null) {
            throw new RuntimeException("La latitud es obligatoria");
        }

        if (request.getLongitud() == null) {
            throw new RuntimeException("La longitud es obligatoria");
        }

        if (request.getDescripcion() == null || request.getDescripcion().isBlank()) {
            throw new RuntimeException("La descripcion es obligatoria");
        }

        if (request.getTipoIncendio() == null || request.getTipoIncendio().isBlank()) {
            throw new RuntimeException("El tipoIncendio es obligatorio");
        }

        // Si NO es anónimo, debe venir usuarioId
        if (!request.getAnonimo()) {
            if (request.getUsuarioId() == null) {
                throw new RuntimeException("usuarioId es obligatorio cuando anonimo = false");
            }

            UsuarioResponseDTO usuario = usuarioClient.obtenerPorId(request.getUsuarioId());

            if (usuario == null) {
                throw new RuntimeException("No existe el usuario con ID " + request.getUsuarioId());
            }

            request.setRunCiudadano(usuario.getRun());
        } else {
            // Si es anónimo, limpiamos datos de usuario
            request.setUsuarioId(null);
            request.setRunCiudadano(null);
        }

        return reportesClient.guardarReporte(request);
    }

    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO request) {

        if (request.getRun() == null || request.getRun().isBlank()) {
            throw new RuntimeException("El run es obligatorio");
        }

        if (request.getRol() == null || request.getRol().isBlank()) {
            throw new RuntimeException("El rol es obligatorio");
        }

        return usuarioClient.crearUsuario(request);
    }
}