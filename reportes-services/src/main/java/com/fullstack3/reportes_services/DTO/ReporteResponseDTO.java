package com.fullstack3.reportes_services.DTO;

import com.fullstack3.reportes_services.enums.EstadoReporte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponseDTO {
    // Aquí sí le devolvemos todo para que lo vea en su pantalla
    private Long id;
    private LocalDateTime fechaReporte;
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private String tipoIncendio;
    private EstadoReporte estado;
    private String runCiudadano; // Opcional, para que confirme que quedó a su rut
}
