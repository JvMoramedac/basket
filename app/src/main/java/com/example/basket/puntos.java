package com.example.basket;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.basket.databinding.ActivityPuntosBinding;

public class puntos extends AppCompatActivity {

    // Data Binding
    private ActivityPuntosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar Data Binding
        binding = ActivityPuntosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener datos del Intent
        Intent intent = getIntent();
        int localScore = intent.getIntExtra(MainActivity.EXTRA_LOCAL_SCORE, 0);
        int visitorScore = intent.getIntExtra(MainActivity.EXTRA_VISITOR_SCORE, 0);

        // Mostrar resultados
        displayResults(localScore, visitorScore);

        // Configurar botón de volver
        binding.btnBack.setOnClickListener(v -> finish());
    }

    /**
     * Muestra los resultados del partido y determina el ganador
     * @param localScore Puntuación del equipo local
     * @param visitorScore Puntuación del equipo visitante
     */
    private void displayResults(int localScore, int visitorScore) {
        // Mostrar marcador final en formato X - Y
        String finalScore = localScore + " - " + visitorScore;
        binding.tvFinalScore.setText(finalScore);

        // Determinar y mostrar el resultado
        String resultMessage = calculateResultMessage(localScore, visitorScore);
        binding.tvResultMessage.setText(resultMessage);
    }

    /**
     * Calcula el mensaje de resultado según los marcadores
     * @param localScore Puntuación del equipo local
     * @param visitorScore Puntuación del equipo visitante
     * @return Mensaje indicando el ganador o empate
     */
    private String calculateResultMessage(int localScore, int visitorScore) {
        if (localScore > visitorScore) {
            return getString(R.string.result_local_wins);
        } else if (visitorScore > localScore) {
            return getString(R.string.result_visitor_wins);
        } else {
            return getString(R.string.result_tie);
        }
    }
}