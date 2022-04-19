package com.kent.pam_tr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeAdminFragment extends Fragment {

    private ListView listView;
    private ArrayList<Data> dataArrayList;
    private EditText txtSearch;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home_admin, null);

        listView = (ListView) root.findViewById(R.id.list_view);
        dataArrayList = new ArrayList<>();

        txtSearch = (EditText) root.findViewById(R.id.txtSearch);

        db = FirebaseFirestore.getInstance();

        loadDatainListview();

        return root;
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
                                    getContext(), dataArrayList);

                            listView.setAdapter(adapter);
                            txtSearch.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                }

                                @Override
                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                    adapter.getFilter().filter(charSequence);
                                }

                                @Override
                                public void afterTextChanged(Editable editable) {

                                }
                            });
                        } else {
                            Toast.makeText(getContext(),
                                    "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Fail to load data..",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}