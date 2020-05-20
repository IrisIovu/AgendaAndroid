package eu.ase.angedasincronizareonline.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eu.ase.angedasincronizareonline.database.dao.UserDao;
import eu.ase.angedasincronizareonline.database.model.User;

@Database(entities = {User.class,}, exportSchema = false, version = 1)
public abstract class DBManager extends RoomDatabase {
    private static final String DB_NAME = "Agenda_db";
    private static DBManager dbManager;

    public static DBManager getInstance(Context context) {
        if (dbManager == null) {
            synchronized (DBManager.class) {
                if (dbManager == null) {
                    dbManager = Room
                            .databaseBuilder(context, DBManager.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
            return dbManager;
        }
        return dbManager;
    }

    public abstract UserDao getUserDao();


}
