package com.miguelcardenas.demo.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "temperature_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Registro individual de temperatura capturado durante una fase específica del proceso de producción")
public class TemperatureLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del log de temperatura", example = "501")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "batch_id", nullable = false)
    @Schema(description = "Referencia al lote (batch) al que pertenece este registro de temperatura")
    private YogurtBatch batch;
    
    @Column(nullable = false)
    @Schema(description = "Valor de la temperatura capturada (°C))", example = "43.5")
    private Double temperature; 
    
    @Column(nullable = false)
    @Schema(description = "Fecha y hora exacta en la que se realizó la medición", example = "2026-04-26T10:30:00")
    private LocalDateTime recordedAt;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Fase del proceso en la que se tomó la medida", example = "INCUBATION")
    private LogType type;
    
    @Schema(description = "Observaciones sobre la medición", example = "Temperatura estable en el centro del tanque")
    private String notes;
    
    @Schema(description = "Tipos de fases monitoreadas en el sistema")
    public enum LogType {
        HEATING, COOLING, INCUBATION, REFRIGERATION, MANUAL
    }
}