package com.example.roomdatabasebai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Address> addresses;
    AddressAdapter addressAdapter;
    AppDatabase db;
    AddressDao addressDao;
    TextView tvThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "addressdb")
                .allowMainThreadQueries()
                .build();
        addressDao = (AddressDao) db.addressdao();
        addresses = addressDao.getAll();
        addressAdapter = new AddressAdapter(addresses,this,R.layout.list_item);
        listView = findViewById(R.id.list_View_1);
        listView.setAdapter(addressAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addressAdapter.notifyDataSetChanged();
            }
        });

        //Them
        tvThem = findViewById(R.id.txtDiaDiem);
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = tvThem.getText().toString();
                if(text.equalsIgnoreCase("")==false){
                    Address address = new Address(addresses.size(), text);
                    addressDao.insertAll(address);
                    addresses = addressDao.getAll();
                    addressAdapter.changeList(addresses);
                }

            }
        });
    }
}