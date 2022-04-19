package com.kent.pam_tr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    private TextView txtLogin,txtForgor;
    private TextInputLayout txtUsername, txtPassword;
    private Button btnLogin, btnRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.txtLogin);
        txtForgor = findViewById(R.id.txtForgor);
        txtUsername = findViewById(R.id.txtInputUsername);
        txtPassword = findViewById(R.id.txtInputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegis = findViewById(R.id.btnRegis);

        db = FirebaseFirestore.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekLogin();
            }
        });

        txtForgor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPassword();
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { Register(); }
        });
    }

    public void cekLogin(){
        db.collection("akun").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> cekAkun = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot ds : cekAkun) {
                    String username = ds.get("username").toString();
                    String password = ds.get("password").toString();

                    String user = txtUsername.getEditText().getText().toString();

                    Intent intentToHome = new Intent(MainActivity.this, HomeActivity.class);
                    intentToHome.putExtra("user", user);
                    Intent intentToHomeAdmin = new Intent(MainActivity.this, HomeAdminActivity.class);

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(txtLogin.getContext());
                    builder1.setTitle("Login Gagal");
                    builder1.setIcon(android.R.drawable.ic_dialog_alert);

                    if (username.equals(txtUsername.getEditText().getText().toString()) && password.equals(txtPassword.getEditText().getText().toString())){
                        builder1.setTitle("Login berhasil");
                        builder1.setIcon(android.R.drawable.ic_dialog_info);
                        builder1.setMessage("Welcome "+txtUsername.getEditText().getText().toString()+"!");
                        AlertDialog alert1 = builder1.create();
                        alert1.show();

                        if (txtUsername.getEditText().getText().toString().equals("admin")){
                            startActivity(intentToHomeAdmin);
                        }else {
                            startActivity(intentToHome);
                        }
                    }
                    else if(username != txtUsername.getEditText().getText().toString() &&  password.equals(txtPassword.getEditText().getText().toString())){
                        builder1.setMessage("Username Tidak Terdaftar");
                        AlertDialog alert1 = builder1.create();
                        alert1.show();
                    }
                    else if(password != txtPassword.getEditText().getText().toString() &&  username.equals(txtUsername.getEditText().getText().toString())){
                        builder1.setMessage("Password Salah");
                        AlertDialog alert1 = builder1.create();
                        alert1.show();
                    }
                    else{
                    }
                }
            }
        });
    }

    public void forgotPassword(){
        Intent intentToForgor = new Intent(MainActivity.this, ForgorActivity.class);

        startActivity(intentToForgor);
    }

    public void Register(){
        Intent intentToRegister = new Intent(MainActivity.this, RegisterActivity.class);

        startActivity(intentToRegister);
    }
}