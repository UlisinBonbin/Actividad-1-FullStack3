package com.fullstack3.bff_emergencias.client;
import com.fullstack3.bff_emergencias.dto.ReporteRequestDTO;
import com.fullstack3.bff_emergencias.dto.ReporteResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "reportes-service", url = "http://localhost:8081/api/v1/reportes")
public interface ReportesClient {
    @PostMapping
    ReporteResponseDTO guardarReporte(@RequestBody ReporteRequestDTO request);
}
