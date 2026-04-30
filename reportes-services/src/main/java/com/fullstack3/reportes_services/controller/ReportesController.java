package com.fullstack3.reportes_services.controller;

import com.fullstack3.reportes_services.dto.ReporteRequestDTO;
import com.fullstack3.reportes_services.dto.ReporteResponseDTO;
import com.fullstack3.reportes_services.services.ReportesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RequestMapping("/api/v1/reportes")
@RestController
public final class ReportesController {

    private ReportesService reportesService;

    @GetMapping
    public List<ReporteResponseDTO> getAllReportes(){
        return reportesService.obtenerTodos();
    }

    @PostMapping
    public ReporteResponseDTO saveReporte(@RequestBody ReporteRequestDTO requestDTO){
        return reportesService.guardarReporte(requestDTO);
    }
}