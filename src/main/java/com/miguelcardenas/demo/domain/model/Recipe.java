package com.miguelcardenas.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad principal que define los parámetros técnicos y pasos para la elaboración de un tipo de yogurt")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la receta", example = "1")
    private Long id;
    
    @Column(nullable = false, unique = true)
    @Schema(description = "Nombre único de la receta", example = "Yogurt Griego Premium")
    private String name;
    
    @Schema(description = "Descripción detallada sobre el sabor, textura o historia de la receta", example = "Yogurt de alta densidad, filtrado artesanalmente")
    private String description;
    
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @Builder.Default
    @Schema(description = "Lista de ingredientes necesarios para esta receta")
    private List<Ingredient> ingredients = new ArrayList<>();
    
    @Column(nullable = false)
    @Schema(description = "Volumen de leche recomendado para la preparación base (en litros)", example = "10.0")
    private Double defaultMilkVolume; 
    
    @Column(nullable = false)
    @Schema(description = "Cantidad de cultivo iniciador o yogurt base (en cucharadas)", example = "4.0")
    private Double defaultStarterAmount; 
    
    @Column(nullable = false)
    @Schema(description = "Temperatura ideal para la pasteurización de la leche (°C)", example = "85.0")
    private Double heatingTemperature; 
    
    @Column(nullable = false)
    @Schema(description = "Tiempo de mantenimiento de la temperatura de calentamiento (en minutos)", example = "30")
    private Integer heatingDuration; 
    
    @Column(nullable = false)
    @Schema(description = "Temperatura para la inoculación (°C)", example = "42.5")
    private Double inoculationTemperature; 
    
    @Column(nullable = false)
    @Schema(description = "Temperatura constante durante el proceso de incubación (°C)", example = "43.0")
    private Double incubationTemperature; 
    
    @Column(nullable = false)
    @Schema(description = "Tiempo mínimo necesario para una fermentación exitosa (en minutos)", example = "230")
    private Integer minIncubationTime; 
    
    @Column(nullable = false)
    @Schema(description = "Tiempo máximo sugerido para evitar exceso de acidez (en minutos)", example = "120")
    private Integer maxIncubationTime; 
    
    @Column(nullable = false)
    @Schema(description = "Tiempo mínimo de reposo en frío antes de su consumo (en minutos)", example = "400")
    private Integer refrigerationTime; 
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Nivel de complejidad de la receta", example = "INTERMEDIATE")
    private DifficultyLevel difficulty;
    
    @Schema(description = "Consejos adicionales para mejorar el resultado final", example = "No agitar el recipiente durante la incubación")
    private String tips;
    
    @Column(nullable = false)
    @Schema(description = "Estado de la receta (si está disponible para ser usada en nuevos lotes)", example = "true")
    private Boolean active;
    
    @Schema(description = "Niveles de dificultad disponibles")
    public enum DifficultyLevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }
}