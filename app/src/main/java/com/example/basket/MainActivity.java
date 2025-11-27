package com.example.basket;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.basket.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Constantes para las claves de datos
    public static final String EXTRA_LOCAL_SCORE = "extra_local_score";
    public static final String EXTRA_VISITOR_SCORE = "extra_visitor_score";

    // Variables para los marcadores
    private int localScore = 0;
    private int visitorScore = 0;

    // Data Binding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar Data Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar listeners de botones
        setupButtons();

        // Actualizar marcadores iniciales
        updateScores();
    }

    /**
     * Configura todos los listeners de los botones
     */
    private void setupButtons() {
        // Botones equipo Local
        binding.btnLocalPlus1.setOnClickListener(v -> addPointsLocal(1));
        binding.btnLocalPlus2.setOnClickListener(v -> addPointsLocal(2));
        binding.btnLocalMinus1.setOnClickListener(v -> subtractPointsLocal(1));

        // Botones equipo Visitante
        binding.btnVisitorPlus1.setOnClickListener(v -> addPointsVisitor(1));
        binding.btnVisitorPlus2.setOnClickListener(v -> addPointsVisitor(2));
        binding.btnVisitorMinus1.setOnClickListener(v -> subtractPointsVisitor(1));

        // Botón Reset
        binding.btnReset.setOnClickListener(v -> resetScores());

        // Botón Ver Resultados
        binding.btnViewResults.setOnClickListener(v -> navigateToResults());
    }

    /**
     * Añade puntos al equipo Local
     * @param points Puntos a añadir
     */
    private void addPointsLocal(int points) {
        localScore += points;
        updateScores();
    }

    /**
     * Añade puntos al equipo Visitante
     * @param points Puntos a añadir
     */
    private void addPointsVisitor(int points) {
        visitorScore += points;
        updateScores();
    }

    /**
     * Resta puntos al equipo Local
     * Valida que el marcador no sea negativo
     * @param points Puntos a restar
     */
    private void subtractPointsLocal(int points) {
        localScore -= points;
        if (localScore < 0) {
            localScore = 0;
        }
        updateScores();
    }

    /**
     * Resta puntos al equipo Visitante
     * Valida que el marcador no sea negativo
     * @param points Puntos a restar
     */
    private void subtractPointsVisitor(int points) {
        visitorScore -= points;
        if (visitorScore < 0) {
            visitorScore = 0;
        }
        updateScores();
    }

    /**
     * Reinicia ambos marcadores a 0
     */
    private void resetScores() {
        localScore = 0;
        visitorScore = 0;
        updateScores();
    }

    /**
     * Actualiza los TextView con los marcadores actuales
     */
    private void updateScores() {
        binding.tvLocalScore.setText(String.valueOf(localScore));
        binding.tvVisitorScore.setText(String.valueOf(visitorScore));
    }

    /**
     * Navega a la pantalla de resultados pasando los marcadores
     */
    private void navigateToResults() {
        Intent intent = new Intent(MainActivity.this, puntos.class);
        intent.putExtra(EXTRA_LOCAL_SCORE, localScore);
        intent.putExtra(EXTRA_VISITOR_SCORE, visitorScore);
        startActivity(intent);
    }
}