
package com.example.douanetemporelle;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView resultat;
    Button nouveau;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 1. ربط العناصر
        resultat = findViewById(R.id.tv_resultat_mf);
        nouveau = findViewById(R.id.btn_nouveau_mf);
        layout = findViewById(R.id.layout_result_mf);

        // 2. استقبال المعلومات
        String matricule = "9425";

        String nom = getIntent().getStringExtra(
                MainActivity.construireCle(matricule, "NOM_VOYAGEUR"));

        boolean verdict = getIntent().getBooleanExtra(
                MainActivity.construireCle(matricule, "VERDICT"), false);

        // 3. عرض النتيجة
        if (verdict) {
            layout.setBackgroundColor(0xFF4CAF50);
            resultat.setText(getString(R.string.message_approuve, nom));
        } else {
            layout.setBackgroundColor(0xFFF44336);
            resultat.setText(getString(R.string.message_refuse, nom));
        }

        // 4. العودة إلى الواجهة الأولى
        nouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}
