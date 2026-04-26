package com.miguelcardenas.demo.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "yogurt_batches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Entidad que representa un lote de producción específico")
public class YogurtBatch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del lote", example = "101")
    private Long id;
    
    @Column(nullable = false)
    @Schema(description = "Código de seguimiento generado automáticamente", example = "YB-1714150000000")
    private String batchCode;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @Schema(description = "Receta base utilizada para este lote")
    private Recipe recipe;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Estado actual del proceso de producción", example = "INCUBATING")
    private BatchStatus status;
    
    @Column(nullable = false)
    @Schema(description = "Volumen real de leche utilizado (Litros)", example = "20.0")
    private Double milkVolume; 
    
    @Column(nullable = false)
    @Schema(description = "Cantidad real de iniciador añadido (Cucharadas)", example = "8.0")
    private Double starterAmount; 
    
    @Column(nullable = false)
    @Schema(description = "Temperatura objetivo para la fase actual (°C)", example = "43.0")
    private Double targetTemperature; 
    
    @Column(nullable = false)
    @Schema(description = "Tiempo total programado para la incubación (en minutos)", example = "8")
    private Integer incubationTime; 
    
    @Schema(description = "Fecha y hora de inicio de la preparación")
    private LocalDateTime startTime;
    
    @Schema(description = "Marca de tiempo cuando inició la fermentación")
    private LocalDateTime incubationStartTime;
    
    @Schema(description = "Marca de tiempo estimada o real del fin de la fermentación")
    private LocalDateTime incubationEndTime;
    
    @Schema(description = "Marca de tiempo cuando el lote ingresó a refrigeración")
    private LocalDateTime refrigerationStartTime;
    
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    @Builder.Default
    @Schema(description = "Historial de registros de temperatura asociados a este lote")
    private List<TemperatureLog> temperatureLogs = new ArrayList<>();
    
    @Schema(description = "Notas adicionales sobre incidentes o calidad del lote", example = "Se utilizó leche de un nuevo proveedor")
    private String notes;
    
    @Column(nullable = false)
    @Schema(description = "Fecha de creación del registro en el sistema")
    private LocalDateTime createdAt;
    
    @Schema(description = "Fecha de la última actualización de estado o datos")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        batchCode = "YB-" + System.currentTimeMillis();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    @Schema(description = "Estados posibles de un lote de producción")
    public enum BatchStatus {
        PREPARING, HEATING, COOLING, INOCULATING, INCUBATING, REFRIGERATING, COMPLETED, FAILED
    }
}