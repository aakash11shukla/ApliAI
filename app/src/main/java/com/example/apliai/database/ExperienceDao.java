package com.example.apliai.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.apliai.models.Experience;

import java.util.List;

@Dao
public interface ExperienceDao {

    @Insert
    void insert(Experience experience);

    @Query("SELECT * from experience_table WHERE experienceId = :id")
    Experience getExperienceByExperienceId(String id);

    @Query("SELECT * from experience_table")
    LiveData<List<Experience>> getAllExperiences();
}
