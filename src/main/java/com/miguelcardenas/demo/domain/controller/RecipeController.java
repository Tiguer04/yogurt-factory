package com.miguelcardenas.demo.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miguelcardenas.demo.domain.model.Recipe;
import com.miguelcardenas.demo.domain.service.RecipeService;
import com.miguelcardenas.demo.dto.RecipeDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recipes") // --> localhost:8080/api/recipes/1/deactivate
@RequiredArgsConstructor
@Tag(name = "Gestión de Recetas", description = "Operaciones para crear, actualizar, obtener, activar y desactivar recetas para la producción de yogurt.")
public class RecipeController {
    
    private final RecipeService recipeService;
    

    @Operation(
        summary = "Crear nueva receta",
        description = "Registra una nueva receta en la base de datos. El cuerpo de la petición debe seguir la estructura del RecipeDTO. El estado inicial será 'activo' por defecto."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Receta creada exitosamente."),
        @ApiResponse(responseCode = "400", description = "Error de validación. Verifique que todos los campos obligatorios del DTO estén presentes y en el formato correcto.")
    })
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = recipeService.createRecipe(recipeDTO);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }
    

    @Operation(
        summary = "Actualizar receta existente",
        description = "Modifica los atributos de una receta identificada por su ID. Se requiere enviar el objeto completo con los cambios deseados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta actualizada correctamente."),
        @ApiResponse(responseCode = "400", description = "Petición inválida. Los datos suministrados no cumplen con las restricciones de la entidad."),
        @ApiResponse(responseCode = "404", description = "No se encontró ninguna receta con el ID proporcionado.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = recipeService.updateRecipe(id, recipeDTO);
        return ResponseEntity.ok(recipe);
    }
    

    @Operation(
        summary = "Obtener receta por ID",
        description = "Busca una receta específica en la base de datos utilizando su identificador único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta encontrada exitosamente."),
        @ApiResponse(responseCode = "404", description = "La receta con el ID proporcionado no existe.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return ResponseEntity.ok(recipe);
    }
    

    @Operation(
        summary = "Obtener todas las recetas",
        description = "Devuelve una lista de todas las recetas que se encuentran actualmente activas en la base de datos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida correctamente.")
    })
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllActiveRecipes());
    }
    

    @Operation(
        summary = "Buscar recetas por palabra clave (keyword)",
        description = "Busca recetas cuyo nombre o descripción coincidan con la palabra clave proporcionada como parámetro de búsqueda."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente."),
        @ApiResponse(responseCode = "400", description = "El parámetro de búsqueda es inválido.")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam String keyword) {
        return ResponseEntity.ok(recipeService.searchRecipes(keyword));
    }
    

    @Operation(
        summary = "Desactivar receta",
        description = "Cambia el estado de una receta a 'inactiva' para que no pueda ser utilizada en nuevos lotes de producción."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta desactivada correctamente."),
        @ApiResponse(responseCode = "404", description = "No se encontró la receta para desactivar.")
    })
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateRecipe(@PathVariable Long id) {
        recipeService.deactivateRecipe(id);
        return ResponseEntity.ok().build();
    }
    

    @Operation(
        summary = "Activar receta",
        description = "Cambia el estado de una receta a 'activa', permitiendo su uso nuevamente en el sistema y su aplicación en los lotes de yogurt."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta activada correctamente."),
        @ApiResponse(responseCode = "404", description = "No se encontró la receta para activar.")
    })
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateRecipe(@PathVariable Long id) {
        recipeService.activateRecipe(id);
        return ResponseEntity.ok().build();
    }
}