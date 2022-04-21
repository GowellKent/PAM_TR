package com.kent.pam_tr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailAkun extends AppCompatActivity {

    private TextView detNama, detNim, txtPoinOmb, txtPoinPro, txtPoinHum, txtPoinPen, txtPoinTotal;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akun);

        detNama = findViewById(R.id.namaDetail);
        detNim = findViewById(R.id.nimDetail);
        txtPoinOmb = findViewById(R.id.txtPoinOmbDet);
        txtPoinPro = findViewById(R.id.txtPoinProDet);
        txtPoinHum = findViewById(R.id.txtPoinHumDet);
        txtPoinPen = findViewById(R.id.txtPoinPenDet);
        txtPoinTotal = findViewById(R.id.txtPoinTotalDet);

        String namaa = getIntent().getStringExtra("nama");

        db = FirebaseFirestore.getInstance();

        db.collection("akun").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> cek = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot ds : cek){
                            String nims = ds.get("nama").toString();
                            Map<String, Object> poin = (HashMap<String, Object>) ds.get("poin");

                            if (namaa.equals(nims)) {
                                String nam = ds.get("nama").toString();
                                String ni = ds.get("nim").toString();

                                detNama.setText("Nama : "+nam);
                                detNim.setText("Nim : "+ni);
                                txtPoinOmb.setText(poin.get("omb").toString());
                                txtPoinPro.setText(poin.get("pro").toString());
                                txtPoinHum.setText(poin.get("hum").toString());
                                txtPoinPen.setText(poin.get("pen").toString());
                                txtPoinTotal.setText(poin.get("total").toString());
                            }
                        }

                    }
                });

    }
}