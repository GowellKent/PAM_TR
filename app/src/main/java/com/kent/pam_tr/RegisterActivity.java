package com.kent.pam_tr;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private TextView txtRegister;
    private TextInputLayout txtRegisNama, txtRegisNim, txtRegisUsername, txtRegisPassword;
    private Button btnRegis;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtRegister = findViewById(R.id.txtRegister);
        txtRegisNama = findViewById(R.id.txtregisNama);
        txtRegisNim = findViewById(R.id.txtRegisNim);
        txtRegisUsername = findViewById(R.id.txtRegisUsername);
        txtRegisPassword = findViewById(R.id.txtRegisPassword);
        btnRegis = findViewById(R.id.btnDaftar);

        db = FirebaseFirestore.getInstance();

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrasi();
            }
        });

    }

    public void Registrasi(){

        Map<String, Object> akun = new HashMap<>();
        String nama = txtRegisNama.getEditText().getText().toString();
        String nim =  txtRegisNim.getEditText().getText().toString();
        String username = txtRegisUsername.getEditText().getText().toString();
        String password = txtRegisPassword.getEditText().getText().toString();

            akun.put("nama",nama);
            akun.put("nim", nim);
            akun.put("username", username);
            akun.put("password", password);

            db.collection("akun")
                    .add(akun)
                    .addOnSuccessListener(documentReference -> {
                        Log.d(TAG, "Registrasi: "+documentReference.getId());

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(txtRegister.getContext());
                        builder1.setTitle("Registration Success");
                        builder1.setIcon(R.drawable.ic_success);
                        AlertDialog alert1 = builder1.create();
                        alert1.show();
                    })
                    .addOnFailureListener(e -> {
                        Log.w(TAG, "Registrasi: ", e);
                    });
    }
}