package com.kent.pam_tr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DetailAkun extends AppCompatActivity {

    private TextView detNama, detNim, txtPoinOmb, txtPoinPro, txtPoinHum, txtPoinPen, txtPoinTotal;
    private FirebaseFirestore db;

    private TextInputLayout txtInputPoin;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button test;

    int ombAwal, proAwal, humAwal, penAwal, totalAwal;
    String docID;

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

        txtInputPoin = findViewById(R.id.txtInputPoin);

        String namaa = getIntent().getStringExtra("namaFile");

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

                                docID = ds.getId();

                                detNama.setText("Nama : "+nam);
                                detNim.setText("Nim : "+ni);
                                txtPoinOmb.setText(poin.get("omb").toString());
                                txtPoinPro.setText(poin.get("pro").toString());
                                txtPoinHum.setText(poin.get("hum").toString());
                                txtPoinPen.setText(poin.get("pen").toString());
                                txtPoinTotal.setText(poin.get("total").toString());

                                ombAwal = Integer.parseInt(poin.get("omb").toString());
                                proAwal = Integer.parseInt(poin.get("pro").toString());
                                humAwal = Integer.parseInt(poin.get("hum").toString());
                                penAwal = Integer.parseInt(poin.get("hum").toString());
                                totalAwal = Integer.parseInt(poin.get("total").toString());
                            }
                        }

                    }
                });

        ButtonListener();

    }

    public void ButtonListener(){
        radioGroup = findViewById(R.id.radioGroup);
        test = findViewById(R.id.tesbut);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedId);

                switch (selectedId){
                    case R.id.radioOMB : {
                        inputOMB();
                    }break;
                    case R.id.radioPro:  {
                        inputPro();
                    }break;
                    case R.id.radioHum : {
                        inputHum();
                    }break;
                    case R.id.radioPen : {
                        inputPen();
                    }break;
                }

            }
        });
    }

    public void inputOMB(){

        int poinTambah = Integer.parseInt(txtInputPoin.getEditText().getText().toString());

        int ombBaru = poinTambah + ombAwal;
        int totalBaru = ombBaru + proAwal + humAwal + penAwal;

        db.collection("akun").document(docID).update("poin.omb", ombBaru);
        db.collection("akun").document(docID).update("poin.total", totalBaru);

        finish();
        startActivity(getIntent());
    }

    public void inputPro(){

        int poinTambah = Integer.parseInt(txtInputPoin.getEditText().getText().toString());

        int proBaru = poinTambah + proAwal;
        int totalBaru = ombAwal + proBaru + humAwal + penAwal;

        db.collection("akun").document(docID).update("poin.pro", proBaru);
        db.collection("akun").document(docID).update("poin.total", totalBaru);

        finish();
        startActivity(getIntent());
    }

    public void inputHum(){

        int poinTambah = Integer.parseInt(txtInputPoin.getEditText().getText().toString());

        int humBaru = poinTambah + humAwal;
        int totalBaru = ombAwal + proAwal + humBaru + penAwal;

        db.collection("akun").document(docID).update("poin.hum", humBaru);
        db.collection("akun").document(docID).update("poin.total", totalBaru);

        finish();
        startActivity(getIntent());
    }

    public void inputPen(){

        int poinTambah = Integer.parseInt(txtInputPoin.getEditText().getText().toString());

        int penBaru = poinTambah + penAwal;
        int totalBaru = ombAwal + proAwal + humAwal + penBaru;

        db.collection("akun").document(docID).update("poin.pen", penBaru);
        db.collection("akun").document(docID).update("poin.total", totalBaru);

        finish();
        startActivity(getIntent());
    }
}