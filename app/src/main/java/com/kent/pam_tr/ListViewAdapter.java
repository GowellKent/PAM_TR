package com.kent.pam_tr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Data> {
    public ListViewAdapter(@NonNull Context context, ArrayList<Data> dataArrayList) {
        super(context, 0, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).
                    inflate(R.layout.item_listview, parent, false);
        }

        Data data = getItem(position);

        TextView tvAddress = listitemView.findViewById(R.id.tvNIM);
        tvAddress.setText(data.getNim());

        TextView tvName = listitemView.findViewById(R.id.tvName);
        tvName.setText(data.getNama());

        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), data.getNama(), Toast.LENGTH_SHORT).show();
                String nama = data.getNama();

                Intent intentToDetail = new Intent(v.getContext(), DetailAkun.class);
                intentToDetail.putExtra("namaFile", nama);
                v.getContext().startActivity(intentToDetail);
            }
        });
        return listitemView;
    }
}
