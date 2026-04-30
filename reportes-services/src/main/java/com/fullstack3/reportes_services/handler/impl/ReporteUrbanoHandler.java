package com.fullstack3.reportes_services.handler.impl;

import com.fullstack3.reportes_services.dto.ReporteRequestDTO;
import com.fullstack3.reportes_services.enums.EquipoAsignado;
import com.fullstack3.reportes_services.enums.NivelPrioridad;
import com.fullstack3.reportes_services.handler.ReporteHandler;
import com.fullstack3.reportes_services.model.Reportes;
import org.springframework.stereotype.Component;

@Component
public class ReporteUrbanoHandler implements ReporteHandler {
    @Override
    public void procesarSegunTipo(Reportes reporte, ReporteRequestDTO requestDTO) {

        reporte.setNivelPrioridad(NivelPrioridad.MEDIA);
        reporte.setRadioImpacto(1000); // 1km de radio, por ahora dejemoslo estaticos, despues se pueden añadir mas
        //condiciones y variables que determinen el tipo de incendio, como ahora solo es un protoripo, esta bien.
        reporte.setEquipoAsignado(EquipoAsignado.BOMBEROS_URBANOS);

    }
    @Override
    public String getTipoIncendioSoportado() {
        return "URBANO";
    }
}
