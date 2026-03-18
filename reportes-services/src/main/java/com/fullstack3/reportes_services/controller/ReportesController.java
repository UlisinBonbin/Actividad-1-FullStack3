package com.fullstack3.reportes_services.controller;


import com.fullstack3.reportes_services.model.Reportes;
import com.fullstack3.reportes_services.services.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/reportes")
@RestController

public class ReportesController {
    @Autowired
    ReportesService reportesService;

    @GetMapping
    public List<Reportes>getAllReportes(){
        return reportesService.obtenerTodos();
    }

    @PostMapping
    public Reportes saveProducto(@RequestBody Reportes reportes){
        return reportesService.guardarReporte(reportes);
    }

}
