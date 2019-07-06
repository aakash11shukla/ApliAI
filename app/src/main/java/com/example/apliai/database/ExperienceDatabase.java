package com.example.apliai.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.apliai.models.Experience;


@Database(entities = {Experience.class}, version = 1, exportSchema = false)
public abstract class ExperienceDatabase extends RoomDatabase {

    public abstract ExperienceDao experienceDao();
    private static volatile ExperienceDatabase INSTANCE;

    public static ExperienceDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExperienceDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            ExperienceDatabase.class, "experience_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
