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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointFragment extends Fragment {

    private TextView txtPoinOmb, txtPoinPro, txtPoinHum, txtPoinPen, txtPoinTotal;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_point, null);

        txtPoinOmb = (TextView) root.findViewById(R.id.txtPoinOmb);
        txtPoinPro = (TextView) root .findViewById(R.id.txtPoinPro);
        txtPoinHum = (TextView) root.findViewById(R.id.txtPoinHum);
        txtPoinPen = (TextView) root.findViewById(R.id.txtPoinPen);
        txtPoinTotal = (TextView) root.findViewById(R.id.txtPoinTotal);

        String user = getActivity().getIntent().getStringExtra("user");

        db = FirebaseFirestore.getInstance();

        db.collection("akun").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> cek = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot ds : cek){
                            String username = ds.get("username").toString();
                            Map<String, Object> poin = (HashMap<String, Object>) ds.get("poin");

                            if (user.equals(username)) {
                                txtPoinOmb.setText(poin.get("omb").toString());
                                txtPoinPro.setText(poin.get("pro").toString());
                                txtPoinHum.setText(poin.get("hum").toString());
                                txtPoinPen.setText(poin.get("pen").toString());
                                txtPoinTotal.setText(poin.get("total").toString());
                            }
                        }
                    }
                });

        return root;
    }
}