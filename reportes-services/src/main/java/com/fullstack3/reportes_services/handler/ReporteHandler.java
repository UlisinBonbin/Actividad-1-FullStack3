package com.fullstack3.reportes_services.handler;

import com.fullstack3.reportes_services.dto.ReporteRequestDTO;
import com.fullstack3.reportes_services.model.Reportes;

public interface ReporteHandler {
    void procesarSegunTipo(Reportes reporte, ReporteRequestDTO requestDTO);

    String getTipoIncendioSoportado();

}
