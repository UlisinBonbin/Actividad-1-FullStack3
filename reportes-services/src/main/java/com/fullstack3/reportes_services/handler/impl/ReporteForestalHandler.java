package com.fullstack3.reportes_services.handler.impl;

import com.fullstack3.reportes_services.dto.ReporteRequestDTO;
import com.fullstack3.reportes_services.handler.ReporteHandler;
import com.fullstack3.reportes_services.model.Reportes;
import org.springframework.stereotype.Component;
import com.fullstack3.reportes_services.enums.NivelPrioridad;
import com.fullstack3.reportes_services.enums.EquipoAsignado;

@Component
public class ReporteForestalHandler implements ReporteHandler {

    @Override
    public void procesarSegunTipo(Reportes reporte, ReporteRequestDTO requestDTO) {
        // Lógica específica para incendios forestales
        reporte.setNivelPrioridad(NivelPrioridad.ALTA);
        reporte.setRadioImpacto(5000); // 5km de radio
        reporte.setEquipoAsignado(EquipoAsignado.BOMBEROS_FORESTALES);
    }

    @Override
    public String getTipoIncendioSoportado() {
        return "FORESTAL";
    }
}
