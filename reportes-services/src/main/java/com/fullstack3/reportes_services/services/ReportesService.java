package com.fullstack3.reportes_services.services;

import com.fullstack3.reportes_services.dto.ReporteRequestDTO;
import com.fullstack3.reportes_services.dto.ReporteResponseDTO;
import com.fullstack3.reportes_services.enums.EstadoReporte;
import com.fullstack3.reportes_services.model.Reportes;
import com.fullstack3.reportes_services.repository.ReportesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack3.reportes_services.factory.ReporteHandlerFactory;
import com.fullstack3.reportes_services.handler.ReporteHandler;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public final class ReportesService {

    private  ReportesRepository reportesRepository;

    private ReporteHandlerFactory factory;

    // 1. Obtener todos los reportes convertidos a DTO
    public List<ReporteResponseDTO> obtenerTodos(){
        List<Reportes> reportes = reportesRepository.findAll();

        return reportes.stream()
                .map(this::mapearAResponseDTO)
                .collect(Collectors.toList());
    }

    public ReporteResponseDTO guardarReporte(ReporteRequestDTO requestDTO) {
        if (requestDTO.getAnonimo() == null) {
            throw new RuntimeException("El campo anonimo es obligatorio");
        }

        if (Boolean.TRUE.equals(requestDTO.getAnonimo())) {
            requestDTO.setUsuarioId(null);
            requestDTO.setRunCiudadano(null);
        } else {
            if (requestDTO.getUsuarioId() == null && (requestDTO.getRunCiudadano() == null || requestDTO.getRunCiudadano().isBlank())) {
                throw new RuntimeException("Si no es anónimo, debes enviar usuarioId o runCiudadano");
            }
        }

        Reportes reporte = new Reportes();
        reporte.setLatitud(requestDTO.getLatitud());
        reporte.setLongitud(requestDTO.getLongitud());
        reporte.setDescripcion(requestDTO.getDescripcion());
        reporte.setTipoIncendio(requestDTO.getTipoIncendio());
        reporte.setUsuarioId(requestDTO.getUsuarioId());
        reporte.setRunCiudadano(requestDTO.getRunCiudadano());
        reporte.setAnonimo(requestDTO.getAnonimo());
        reporte.setEstado(EstadoReporte.PENDIENTE);

        // Aca se usa el factory method
        ReporteHandler handler = factory.getHandler(requestDTO.getTipoIncendio());
        handler.procesarSegunTipo(reporte, requestDTO);


        Reportes reporteGuardado = reportesRepository.save(reporte);
        return mapearAResponseDTO(reporteGuardado);
    }



    // 3. Método auxiliar de mapeo (Entidad -> DTO)
    private ReporteResponseDTO mapearAResponseDTO(Reportes reporte) {
        ReporteResponseDTO responseDTO = new ReporteResponseDTO();
        responseDTO.setId(reporte.getId());
        responseDTO.setFechaReporte(reporte.getFechaReporte());
        responseDTO.setLatitud(reporte.getLatitud());
        responseDTO.setLongitud(reporte.getLongitud());
        responseDTO.setDescripcion(reporte.getDescripcion());
        responseDTO.setTipoIncendio(reporte.getTipoIncendio());
        responseDTO.setEstado(reporte.getEstado());
        responseDTO.setRunCiudadano(reporte.getRunCiudadano());

        responseDTO.setNivelPrioridad(reporte.getNivelPrioridad());
        responseDTO.setRadioImpacto(reporte.getRadioImpacto());
        responseDTO.setEquipoAsignado(reporte.getEquipoAsignado());

        return responseDTO;
    }
}