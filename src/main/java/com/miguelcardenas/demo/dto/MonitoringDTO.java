package com.miguelcardenas.demo.dto;

import java.util.Map;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "Clase contenedora para los datos de monitoreo y métricas de producción")
public class MonitoringDTO {
    
    @Data
    @Builder
    @Schema(description = "Resumen estadístico de las temperaturas registradas en un lote")
    public static class TemperatureSummary {
        @Schema(description = "Última temperatura registrada en el sensor", example = "42.5")
        private Double currentTemperature;
        
        @Schema(description = "Temperatura más alta alcanzada durante el proceso", example = "85.0")
        private Double maximumTemperature;
        
        @Schema(description = "Temperatura más baja registrada", example = "38.2")
        private Double minimumTemperature;
        
        @Schema(description = "Promedio de temperatura", example = "43.1")
        private Double averageTemperature;
    }
    
    @Data
    @Builder
    @Schema(description = "Datos globales para el panel de control (Dashboard)")
    public static class Dashboard {
        
        @Schema(
            description = "Conteo detallado de lotes agrupados por su estado actual de producción",
            example = "{\"PREPARING\": 3, \"HEATING\": 1, \"INCUBATING\": 5, \"COMPLETED\": 12, \"FAILED\": 0}"
        )
        private Map<String, Long> batchCounts;
        
        @Schema(description = "Suma total de todos los lotes activos", example = "9")
        private Long activeBatchesCount;
        
        @Schema(description = "Cantidad de lotes que alcanzaron el estado COMPLETED en las últimas 24 horas", example = "4")
        private Integer completedToday;
    }
}