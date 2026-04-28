package com.fullstack3.reportes_services.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteRequestDTO {

    // Solo lo que el usuario ingresa en la app
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private String tipoIncendio;

    // Identificación (todos pueden ser opcionales dependiendo del caso)
    private Long usuarioId;
    private String runCiudadano;
    private Boolean anonimo;

}
