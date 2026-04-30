package com.fullstack3.reportes_services.model;

import com.fullstack3.reportes_services.enums.EquipoAsignado;
import com.fullstack3.reportes_services.enums.EstadoReporte;
import com.fullstack3.reportes_services.enums.NivelPrioridad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reportes")
@EntityListeners(AuditingEntityListener.class) // <-- Obligatorio para que @CreatedDate funcione
public class Reportes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime fechaReporte;

    private Double latitud;

    private Double longitud;

    private String descripcion;

    private String tipoIncendio;

    @Enumerated(EnumType.STRING)
    private EstadoReporte estado;

    private Long usuarioId;
    private String runCiudadano;
    private Boolean anonimo;

    @Enumerated(EnumType.STRING)
    private NivelPrioridad nivelPrioridad;

    private Integer radioImpacto; //Esta en metros

    @Enumerated(EnumType.STRING)
    private EquipoAsignado equipoAsignado;
}
