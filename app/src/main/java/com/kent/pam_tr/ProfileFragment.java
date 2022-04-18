package com.kent.pam_tr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ProfileFragment extends Fragment {

    private TextView usernameProfile;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, null);

        usernameProfile = (TextView) root.findViewById(R.id.usernameProfile);

        String namae = getActivity().getIntent().getStringExtra("user");

        usernameProfile.setText(usernameProfile.getText().toString().replace("[Name]", namae));

        db = FirebaseFirestore.getInstance();
       // String namap;

        db.collection("akun").whereArrayContains("user", namae).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> cek = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot ds : cek){
                            String namap = ds.get("nama").toString();
                            System.out.println("test");
                        }

                    }
                });

        return root;
    }
}