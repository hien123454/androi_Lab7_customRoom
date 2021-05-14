package com.example.roomdatabasebai2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AddressDao {
    @Query("SELECT * FROM address")
    List<Address> getAll();

    @Query("SELECT * FROM address WHERE id IN (:addressIds)")
    List<Address> loadAllByIds(int[] addressIds);

    @Query("SELECT * FROM address WHERE name LIKE :name" )
    Address findByName(String name);

    @Insert
    void insertAll(Address... addresses);

    @Delete
    void delete(Address address);

    @Update
    void update(Address address);
}
