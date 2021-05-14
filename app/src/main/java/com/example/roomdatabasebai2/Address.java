package com.example.roomdatabasebai2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Address {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    public Address(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
