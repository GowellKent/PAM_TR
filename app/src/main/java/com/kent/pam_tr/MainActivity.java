package com.kent.pam_tr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextView txtLogin,txtForgor;
    private TextInputLayout txtUsername, txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.txtLogin);
        txtForgor = findViewById(R.id.txtForgor);
        txtUsername = findViewById(R.id.txtInputUsername);
        txtPassword = findViewById(R.id.txtInputUsername);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekLogin();
            }
        });
    }

    public void cekLogin(){
        String user = "aaaa", pass = "aaaa";
        Intent intentToHome = new Intent(MainActivity.this, HomeActivity.class);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(txtLogin.getContext());
        builder1.setTitle("Login Gagal");
        builder1.setIcon(android.R.drawable.ic_dialog_alert);

        if (user.equalsIgnoreCase(txtUsername.getEditText().getText().toString()) && pass.equalsIgnoreCase(txtPassword.getEditText().getText().toString())){
            builder1.setTitle("Login berhasil");
            builder1.setIcon(android.R.drawable.ic_dialog_info);
            builder1.setMessage("Welcome "+txtUsername.getEditText().getText().toString()+"!");
            AlertDialog alert1 = builder1.create();
            alert1.show();
            startActivity(intentToHome);
        }
        else if(user != txtUsername.getEditText().getText().toString() &&  pass.equalsIgnoreCase(txtPassword.getEditText().getText().toString())){
            builder1.setMessage("Username Tidak Terdaftar");
            AlertDialog alert1 = builder1.create();
            alert1.show();
        }
        else if(pass != txtPassword.getEditText().getText().toString() &&  user.equalsIgnoreCase(txtUsername.getEditText().getText().toString())){
            builder1.setMessage("Password Salah");
            AlertDialog alert1 = builder1.create();
            alert1.show();
        }
        else{
            builder1.setMessage("Username Tidak Terdaftar");
            AlertDialog alert1 = builder1.create();
            alert1.show();
        }
    }
}