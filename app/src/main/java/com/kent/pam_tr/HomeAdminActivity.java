package com.kent.pam_tr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeAdminActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Data> dataArrayList;
    FirebaseFirestore db;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        listView = findViewById(R.id.list_view);
        dataArrayList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        loadDatainListview();
    }

    private void loadDatainListview() {
        db.collection("akun").orderBy("nim").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                Data data = d.toObject(Data.class);
                                dataArrayList.add(data);
                            }
                            ListViewAdapter adapter = new ListViewAdapter(
                                    HomeAdminActivity.this, dataArrayList);

                            listView.setAdapter(adapter);
                        } else {
                            Toast.makeText(HomeAdminActivity.this,
                                    "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomeAdminActivity.this, "Fail to load data..",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}