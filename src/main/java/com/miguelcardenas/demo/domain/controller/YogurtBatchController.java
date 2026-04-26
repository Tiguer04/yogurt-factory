package com.miguelcardenas.demo.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miguelcardenas.demo.domain.model.YogurtBatch;
import com.miguelcardenas.demo.domain.service.YogurtMakingService;
import com.miguelcardenas.demo.dto.BatchDTO;
import com.miguelcardenas.demo.dto.TemperatureRecordDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
@Tag(name = "Gestión de Lotes de Yogurt", description = "Operaciones para controlar el ciclo de vida de producción de los lotes de yogurt, desde la preparación hasta la finalización, incluyendo detalles como la creación, inicialización el calentamiento, inoculación, incubación, etc.")
public class YogurtBatchController {
    
    private final YogurtMakingService yogurtMakingService;
    
    @Operation(summary = "Iniciar nuevo lote", description = "Crea un nuevo registro (lote) de producción de yogurt basado en una receta específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Lote creado exitosamente."),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos.")
    })
    @PostMapping
    public ResponseEntity<YogurtBatch> startNewBatch(@RequestBody BatchDTO.StartBatchRequest request) {
        YogurtBatch batch = yogurtMakingService.startNewBatch(
            request.getRecipeId(), 
            request.getCustomMilkVolume(), 
            request.getCustomStarterAmount()
        );
        return new ResponseEntity<>(batch, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Iniciar fase de calentamiento", description = "Cambia el estado del lote a 'Heating' para comenzar el proceso de calentamiento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fase de calentamiento iniciada."),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado.")
    })
    @PostMapping("/{batchId}/heating")
    public ResponseEntity<YogurtBatch> startHeating(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startHeating(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Iniciar fase de inoculación", description = "Marca el inicio de la adición de cultivos lácticos al lote.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fase de inoculación iniciada.")
    })
    @PostMapping("/{batchId}/inoculating")
    public ResponseEntity<YogurtBatch> startInoculating(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startInoculating(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Iniciar fase de incubación", description = "Establece el lote en periodo de reposo y control de temperatura para la fermentación.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fase de incubación iniciada.")
    })
    @PostMapping("/{batchId}/incubation")
    public ResponseEntity<YogurtBatch> startIncubation(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startIncubation(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Iniciar fase de refrigeración", description = "Cambia el estado a refrigeración tras completar la fermentación.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fase de refrigeración iniciada.")
    })
    @PostMapping("/{batchId}/refrigeration")
    public ResponseEntity<YogurtBatch> startRefrigeration(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startRefrigeration(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Completar lote", description = "Finaliza formalmente el proceso de producción de un lote de yogurt.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lote finalizado con éxito.")
    })
    @PostMapping("/{batchId}/complete")
    public ResponseEntity<YogurtBatch> completeBatch(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.completeBatch(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Marcar lote como fallido", description = "Registra una pérdida en la producción especificando el motivo del fallo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lote marcado como fallido correctamente.")
    })
    @PostMapping("/{batchId}/fail")
    public ResponseEntity<YogurtBatch> markAsFailed(
            @PathVariable Long batchId, 
            @RequestBody BatchDTO.FailRequest request) {
        YogurtBatch batch = yogurtMakingService.markAsFailed(batchId, request.getReason());
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Obtener todos los lotes", description = "Obtiene todos los lotes de producción, opcionalmente filtrados por su estado actual.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de lotes recuperada.")
    })
    @GetMapping
    public ResponseEntity<List<YogurtBatch>> getAllBatches(
            @RequestParam(required = false) YogurtBatch.BatchStatus status) {
        if (status != null) {
            return ResponseEntity.ok(yogurtMakingService.getBatchesByStatus(status));
        }
        return ResponseEntity.ok(yogurtMakingService.getAllBatches());
    }
    
    @Operation(summary = "Obtener detalles de un lote", description = "Recupera la información completa y el estado actual de un lote específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lote encontrado."),
        @ApiResponse(responseCode = "404", description = "El ID del lote no existe.")
    })
    @GetMapping("/{batchId}")
    public ResponseEntity<YogurtBatch> getBatch(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.getBatch(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Registrar temperatura", description = "Guarda un registro puntual de temperatura para un lote en una fase específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Temperatura registrada."),
        @ApiResponse(responseCode = "400", description = "Datos de temperatura inválidos.")
    })
    @PostMapping("/{batchId}/temperature")
    public ResponseEntity<Void> recordTemperature(
            @PathVariable Long batchId, 
            @RequestBody TemperatureRecordDTO request) {
        yogurtMakingService.recordTemperature(batchId, request.getTemperature(), request.getType());
        return ResponseEntity.ok().build();
    }
}