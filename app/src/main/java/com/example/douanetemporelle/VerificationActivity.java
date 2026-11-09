
package com.example.douanetemporelle;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    TextView nom, age, motif;
    Button approuver, refuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // 1. ربط العناصر
        nom = findViewById(R.id.tv_nom_verif_mf);
        age = findViewById(R.id.tv_age_verif_mf);
        motif = findViewById(R.id.tv_motif_verif_mf);

        approuver = findViewById(R.id.btn_approuver_mf);
        refuser = findViewById(R.id.btn_refuser_mf);

        // 2. استقبال المعلومات
        String matricule = "9425";

        String n = getIntent().getStringExtra(
                MainActivity.construireCle(matricule, "NOM_VOYAGEUR"));

        String a = getIntent().getStringExtra(
                MainActivity.construireCle(matricule, "ANNEE_NAISSANCE"));

        String d = getIntent().getStringExtra(
                MainActivity.construireCle(matricule, "ANNEE_DESTINATION"));

        String m = getIntent().getStringExtra(
                MainActivity.construireCle(matricule, "MOTIF"));

        // 3. التحقق من المعلومات
        if (a == null || d == null) {
            finish();
            return;
        }

        // 4. حساب العمر الزمني
        int anneeNaissance = Integer.parseInt(a);
        int anneeDestination = Integer.parseInt(d);

        int ageTemporel = anneeDestination - anneeNaissance;

        // 5. عرض المعلومات
        nom.setText(getString(R.string.afficher_nom, n));
        age.setText(getString(R.string.afficher_age, ageTemporel));
        motif.setText(getString(R.string.afficher_motif, m));

        // 6. تعطيل الموافقة إذا كان العدد أوليا
        if ("Modification du passé".equals(m) && estPremier(ageTemporel)) {
            approuver.setEnabled(false);
        }

        // 7. زر الموافقة
        approuver.setOnClickListener(v -> {

            Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);

            intent.putExtra(MainActivity.construireCle(matricule, "NOM_VOYAGEUR"), n);
            intent.putExtra(MainActivity.construireCle(matricule, "VERDICT"), true);

            startActivity(intent);
        });

        // 8. زر الرفض
        refuser.setOnClickListener(v -> {

            Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);

            intent.putExtra(MainActivity.construireCle(matricule, "NOM_VOYAGEUR"), n);
            intent.putExtra(MainActivity.construireCle(matricule, "VERDICT"), false);

            startActivity(intent);
        });
    }

    // 9. التحقق هل العدد أولي
    public boolean estPremier(int nombre) {

        if (nombre <= 1) {
            return false;
        }

        for (int i = 2; i < nombre; i++) {
            if (nombre % i == 0) {
                return false;
            }
        }

        return true;
    }
}