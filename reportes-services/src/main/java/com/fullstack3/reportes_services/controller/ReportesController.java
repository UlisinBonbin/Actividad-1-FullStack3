package com.fullstack3.reportes_services.controller;

import com.fullstack3.reportes_services.dto.ReporteRequestDTO;
import com.fullstack3.reportes_services.dto.ReporteResponseDTO;
import com.fullstack3.reportes_services.services.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/reportes")
@RestController
public class ReportesController {

    @Autowired
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