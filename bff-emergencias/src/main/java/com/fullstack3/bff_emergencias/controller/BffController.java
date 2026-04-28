package com.fullstack3.bff_emergencias.controller;
import com.fullstack3.bff_emergencias.DTO.ReporteRequestDTO;
import com.fullstack3.bff_emergencias.DTO.ReporteResponseDTO;
import com.fullstack3.bff_emergencias.DTO.UsuarioRequestDTO;
import com.fullstack3.bff_emergencias.DTO.UsuarioResponseDTO;
import com.fullstack3.bff_emergencias.service.EmergenciaOrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/bff/emergencias")
public class BffController {

    @Autowired
    private EmergenciaOrchestratorService orchestrator;

    @PostMapping("/reportar")
    public ReporteResponseDTO crearReporte(@RequestBody ReporteRequestDTO request) {
        return orchestrator.procesarReporte(request);
    }

    @PostMapping("/usuarios")
    public UsuarioResponseDTO crearUsuario(@RequestBody UsuarioRequestDTO request) {
        return orchestrator.registrarUsuario(request);
    }

}
