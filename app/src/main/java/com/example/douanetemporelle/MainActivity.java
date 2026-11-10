
package com.example.douanetemporelle;

import android.os.Bundle;
import android.content.Intent;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nom, naissance, destination;
    Spinner motif;
    Button btn;

    // 1. إنشاء المفاتيح باستعمال matricule
    public static String construireCle(String matricule, String nomCle) {
        return matricule + "_" + nomCle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. ربط العناصر
        nom = findViewById(R.id.et_nom_mf);
        naissance = findViewById(R.id.et_naissance_mf);
        destination = findViewById(R.id.et_destination_mf);
        motif = findViewById(R.id.sp_motif_mf);
        btn = findViewById(R.id.btn_inspecter_mf);

        // 3. أسباب السفر
        String[] liste = {"Tourisme", "Modification du passé", "Fuite"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                liste
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        motif.setAdapter(adapter);

        // 4. زر الانتقال إلى واجهة التحقق
        btn.setOnClickListener(v -> {

            String n = nom.getText().toString().trim();
            String a = naissance.getText().toString().trim();
            String d = destination.getText().toString().trim();
            String m = motif.getSelectedItem().toString();

            // 5. التحقق من الحقول الفارغة
            if (n.isEmpty() || a.isEmpty() || d.isEmpty()) {
                Toast.makeText(this,
                        "Remplissez tous les champs",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // 6. التحقق من صحة السنوات
            try {

                int anneeNaissance = Integer.parseInt(a);
                int anneeDestination = Integer.parseInt(d);

                if (anneeNaissance <= 0 || anneeDestination <= 0) {
                    Toast.makeText(this,
                            "Les années doivent être positives",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 7. التحقق من سنة الميلاد
                if (anneeNaissance > 2026) {
                    Toast.makeText(this,
                            "L'année de naissance ne peut pas être dans le futur",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (anneeDestination <= anneeNaissance) {
                    Toast.makeText(this,
                            "L'année de destination doit être supérieure à l'année de naissance",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                // 8. رقم matricule
                String matricule = "9425";

                // 9. فتح الواجهة الثانية
                Intent intent = new Intent(MainActivity.this,
                        VerificationActivity.class);

                // 10. إرسال المعلومات
                intent.putExtra(construireCle(matricule, "NOM_VOYAGEUR"), n);
                intent.putExtra(construireCle(matricule, "ANNEE_NAISSANCE"), a);
                intent.putExtra(construireCle(matricule, "ANNEE_DESTINATION"), d);
                intent.putExtra(construireCle(matricule, "MOTIF"), m);

                startActivity(intent);

            } catch (NumberFormatException e) {
                Toast.makeText(this,
                        "Veuillez saisir des années valides",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
