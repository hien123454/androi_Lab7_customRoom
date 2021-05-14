package com.example.roomdatabasebai2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Address.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Address addressdao();
}
