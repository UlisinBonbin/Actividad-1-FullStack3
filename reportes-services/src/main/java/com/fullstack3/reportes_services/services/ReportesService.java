package com.fullstack3.reportes_services.services;

import com.fullstack3.reportes_services.enums.EstadoReporte;
import com.fullstack3.reportes_services.model.Reportes;
import com.fullstack3.reportes_services.repository.ReportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportesService {

    @Autowired
    ReportesRepository reportesRepository;

    public List<Reportes>obtenerTodos(){
        return  reportesRepository.findAll();
    }

    public Reportes guardarReporte(Reportes reportes){
        if (reportes.getEstado()==null){
            reportes.setEstado(EstadoReporte.PENDIENDTE);

        }return reportesRepository.save(reportes);
    }

}
