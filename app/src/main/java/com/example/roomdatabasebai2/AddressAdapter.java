package com.example.roomdatabasebai2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.Room;

import java.util.List;

public class AddressAdapter extends BaseAdapter {
    List<Address> list;
    Context context;
    int layout;
    String text;
    EditText txtDD;
    public AddressAdapter(List<Address> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view  = LayoutInflater.from(context).inflate(layout,parent, false);

        //Them so thu tu
        TextView tvSTT = view.findViewById(R.id.tvSTT);
        tvSTT.setText(String.valueOf(position+1)+".");

        //Them ten
        txtDD = view.findViewById(R.id.txtDiaDiem1);
        txtDD.setText(list.get(position).getName());
        txtDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyDataSetChanged();
            }
        });

        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "addressdb")
                .allowMainThreadQueries()
                .build();
        final AddressDao addressDao = (AddressDao) db.addressdao();

        //Xoa
        ImageView imgDelete = view.findViewById(R.id.imgXoa);
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Address address = list.get(position);
                addressDao.delete(address);
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        //Sua
        ImageView imgUpdate = view.findViewById(R.id.imgSua);
        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = txtDD.getText().toString();
                if(text.equalsIgnoreCase("")==false){
                    Address address = list.get(position);
                    address.setName(text);
                    addressDao.update(address);
                    list = addressDao.getAll();
                    notifyDataSetChanged();
                }
            }
        });

        return view;
    }

    public void changeList(List<Address> listDD){
        list = listDD;
        notifyDataSetChanged();
    }
}
