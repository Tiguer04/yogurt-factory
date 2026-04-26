package com.miguelcardenas.demo.dto;

import java.util.List;

import com.miguelcardenas.demo.domain.model.Recipe;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo para la creación y actualización de recetas de yogurt")
public class RecipeDTO {

    @Schema(description = "Nombre de la receta", example = "Yogurt Griego Tradicional")
    private String name;

    @Schema(description = "Breve explicación de las características de la receta y el producto final", 
            example = "Yogurt de textura espesa y alto contenido proteico")
    private String description;

    @Schema(description = "Volumen base de leche sugerido para la receta (litros)", example = "10.0")
    private Double defaultMilkVolume;

    @Schema(description = "Cantidad base de cultivo iniciador (starter) sugerida (gramos/ml)", example = "50.0")
    private Double defaultStarterAmount;

    @Schema(description = "Temperatura ideal para la fase de pasteurización/calentamiento (°C)", example = "85.0")
    private Double heatingTemperature;

    @Schema(description = "Tiempo de mantenimiento de la temperatura de calentamiento (minutos)", example = "30")
    private Integer heatingDuration;

    @Schema(description = "Temperatura a la que se deben añadir los cultivos lácticos (°C)", example = "42.0")
    private Double inoculationTemperature;

    @Schema(description = "Temperatura constante durante la fermentación (°C)", example = "43.0")
    private Double incubationTemperature;

    @Schema(description = "Tiempo mínimo estimado para una fermentación correcta (minutos)", example = "360")
    private Integer minIncubationTime;

    @Schema(description = "Tiempo de incubación máximo permitido (minutos)", example = "720")
    private Integer maxIncubationTime;

    @Schema(description = "Tiempo sugerido de reposo en frío antes del consumo (minutos)", example = "240")
    private Integer refrigerationTime;

    @Schema(description = "Nivel de complejidad técnica para la elaboración de la receta", example = "BEGINNER")
    private Recipe.DifficultyLevel difficulty;

    @Schema(description = "Recomendaciones adicionales", 
            example = "Filtrar con tela de queso para mayor consistencia")
    private String tips;

    @Schema(description = "Listado de ingredientes requeridos")
    private List<IngredientDTO> ingredients;
}