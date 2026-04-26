package com.miguelcardenas.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo que representa un ingrediente específico dentro de una receta de yogurt")
public class IngredientDTO {

    @Schema(description = "Nombre del ingrediente o insumo", example = "Leche entera")
    private String name;

    @Schema(description = "Cantidad numérica del ingrediente en litros o gramos", example = "10.5")
    private Double quantity;

    @Schema(description = "Unidad de medida del ingrediente", example = "ml")
    private String unit;

    @Schema(description = "Observaciones adicionales sobre el ingrediente (ej. marca específica o temperatura)", 
            example = "Debe estar a temperatura ambiente antes de mezclar")
    private String notes;

    @Schema(description = "Indica si el ingrediente es prescindible e indispensable para la receta base", example = "false")
    private Boolean optional;
}