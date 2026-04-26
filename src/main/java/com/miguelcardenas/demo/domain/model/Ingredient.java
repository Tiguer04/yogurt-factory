package com.miguelcardenas.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un ingrediente específico dentro de una receta de yogurt")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del ingrediente en la base de datos", example = "1")
    private Long id;
    
    @Column(nullable = false)
    @Schema(description = "Nombre del ingrediente", example = "Leche entera de vaca")
    private String name;
    
    @Schema(description = "Cantidad numérica del ingrediente en litros o gramos", example = "10.5")
    private Double quantity;
    
    @Schema(description = "Unidad de medida para la cantidad", example = "Litros")
    private String unit; 
    
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonIgnore
    @Schema(description = "Referencia a la receta a la que pertenece este ingrediente")
    private Recipe recipe;
    
    @Schema(description = "Observaciones adicionales sobre el ingrediente", example = "Debe estar a temperatura ambiente")
    private String notes;
    
    @Column(nullable = false)
    @Schema(description = "Indica si el ingrediente es opcional para la preparación", example = "false")
    private Boolean optional;
}