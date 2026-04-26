package com.miguelcardenas.demo.dto;

import com.miguelcardenas.demo.domain.model.TemperatureLog;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Modelo para el registro puntual de mediciones de temperatura")
public class TemperatureRecordDTO {

    @Schema(
        description = "Valor numérico de la temperatura capturada por el sensor (°C))", 
        example = "42.5"
    )
    private Double temperature;

    @Schema(
        description = "Fase del proceso de producción a la que corresponde la medición (ej. HEATING, INCUBATION)", 
        example = "INCUBATION"
    )
    private TemperatureLog.LogType type;
}