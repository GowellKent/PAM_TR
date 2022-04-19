package com.kent.pam_tr;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ProfileFragment extends Fragment {

    private TextView nameProfile, nimProfile;
    private Button btnLogout;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, null);

        nameProfile = (TextView) root.findViewById(R.id.nameProfile);
        nimProfile = (TextView) root.findViewById(R.id.nimProfile);

        String namae = getActivity().getIntent().getStringExtra("user");

        db = FirebaseFirestore.getInstance();

        db.collection("akun").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> cek = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot ds : cek){
                            String username = ds.get("username").toString();

                            if (namae.equals(username)) {
                                String nama = ds.get("nama").toString();
                                String nim = ds.get("nim").toString();

                                nameProfile.setText(nama);
                                nimProfile.setText(nim);
                            }
                        }

                    }
                });

        Button btnLogout = (Button) root.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        return root;
    }
}