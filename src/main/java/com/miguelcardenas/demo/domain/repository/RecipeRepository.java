package com.miguelcardenas.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miguelcardenas.demo.domain.model.Recipe;

@Repository // 
public interface RecipeRepository extends JpaRepository<Recipe, Long> { //(Entidad, tipo de dato de la PK de la Entidad)
    
    Optional<Recipe> findByName(String name); // Optional --> Cero a un retornos.
    
    List<Recipe> findByActive(Boolean active); // List --> Cero a muchos retornos.
    
    List<Recipe> findByDifficulty(Recipe.DifficultyLevel difficulty);
    
    @Query("SELECT r FROM Recipe r WHERE r.active = true AND r.difficulty = :difficulty")
    List<Recipe> findActiveByDifficulty(@Param("difficulty") Recipe.DifficultyLevel difficulty); // @Param mapea los argumentos del método a los marcadores de posicion en el @Query
    
    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(r.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Recipe> searchByKeyword(@Param("keyword") String keyword);
}