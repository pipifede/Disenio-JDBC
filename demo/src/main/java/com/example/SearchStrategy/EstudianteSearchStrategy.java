package com.example.SearchStrategy;


import java.util.Map;
import java.util.HashMap;
import com.example.Estudiante;
import jakarta.persistence.EntityManager;

public interface EstudianteSearchStrategy {
    String buildSearchQuery(String alias);
}
