package com.fullstack3.reportes_services.services;

import com.fullstack3.reportes_services.DTO.ReporteRequestDTO;
import com.fullstack3.reportes_services.DTO.ReporteResponseDTO;
import com.fullstack3.reportes_services.enums.EstadoReporte;
import com.fullstack3.reportes_services.model.Reportes;
import com.fullstack3.reportes_services.repository.ReportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportesService {

    @Autowired
    private ReportesRepository reportesRepository;

    // 1. Obtener todos los reportes convertidos a DTO
    public List<ReporteResponseDTO> obtenerTodos(){
        List<Reportes> reportes = reportesRepository.findAll();

        return reportes.stream()
                .map(this::mapearAResponseDTO)
                .collect(Collectors.toList());
    }

    // 2. Guardar reporte (Recibe RequestDTO, devuelve ResponseDTO)
    public ReporteResponseDTO guardarReporte(ReporteRequestDTO requestDTO){

        // Transformamos lo que llegó a la entidad Reportes
        Reportes reporte = new Reportes();
        reporte.setLatitud(requestDTO.getLatitud());
        reporte.setLongitud(requestDTO.getLongitud());
        reporte.setDescripcion(requestDTO.getDescripcion());
        reporte.setTipoIncendio(requestDTO.getTipoIncendio());
        reporte.setUsuarioId(requestDTO.getUsuarioId());
        reporte.setRunCiudadano(requestDTO.getRunCiudadano());
        reporte.setAnonimo(requestDTO.getAnonimo());

        // Forzamos el estado inicial a PENDIENTE
        reporte.setEstado(EstadoReporte.PENDIENTE);

        // Guardamos en la base de datos
        Reportes reporteGuardado = reportesRepository.save(reporte);

        // Retornamos el DTO de respuesta
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
        return responseDTO;
    }
}