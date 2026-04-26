package com.miguelcardenas.demo.dto;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Clase contenedora de las solicitudes relacionadas con los lotes de producción")
public class BatchDTO {
    
    @Data
    @Schema(description = "Datos necesarios para iniciar un nuevo lote de producción de yogurt")
    public static class StartBatchRequest {
        
        @Schema(description = "ID de la receta base que se utilizará para este lote", example = "1")
        private Long recipeId;
        
        @Schema(description = "Volumen de leche personalizado en litros (opcional, sobreescribe el valor de la receta)", example = "50.5")
        private Double customMilkVolume;
        
        @Schema(description = "Cantidad de cultivo iniciador (starterAmount)", example = "200.0")
        private Double customStarterAmount;
    }
    
    @Data
    @Schema(description = "Información requerida para registrar el fallo de un lote")
    public static class FailRequest {
        
        @Schema(description = "Motivo detallado por el cual el lote se marca como fallido", 
                example = "Fallo eléctrico: la temperatura cayó por debajo de 40°C durante la incubación")
        private String reason;
    }
}