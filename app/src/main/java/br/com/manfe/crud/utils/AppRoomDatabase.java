package br.com.manfe.crud.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.manfe.crud.daos.UserDao;
import br.com.manfe.crud.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract UserDao userDAO();

    private static volatile AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
