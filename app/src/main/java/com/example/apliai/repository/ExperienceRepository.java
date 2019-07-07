package com.example.apliai.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.provider.SyncStateContract;

import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.apliai.backgroundTasks.UploadWorker;
import com.example.apliai.database.ExperienceDao;
import com.example.apliai.database.ExperienceDatabase;
import com.example.apliai.models.Experience;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExperienceRepository {

    private ExperienceDao experienceDao;
    private WorkManager workManager;

    public ExperienceRepository(Context context) {
        ExperienceDatabase db = ExperienceDatabase.getDatabase(context);
        experienceDao = db.experienceDao();
        workManager = WorkManager.getInstance();
    }


    public void addExperience(final Experience experience) {

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uniqueId;

                if(user == null){
                    uniqueId = "hyKA4ElBpUMAF8o9SK1XedKfsUo2";
                }else {
                    uniqueId = user.getUid();
                }
                String key = firebaseDatabase.getReference().child("EXPERIENCE/" + uniqueId).push().getKey();

                experience.setEid(key);
                experienceDao.insert(experience);
                Data.Builder builder = new Data.Builder();
                builder.putString("UserId", uniqueId);
                builder.putString(SyncStateContract.Constants._ID, key);

                Constraints constraints = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
                        .setInputData(builder.build())
                        .setConstraints(constraints)
                        .setBackoffCriteria(BackoffPolicy.LINEAR,
                                15,
                                TimeUnit.SECONDS)
                        .build();

                workManager.enqueue(oneTimeWorkRequest);
            }
        });
    }

    public LiveData<List<Experience>> getAllExperiences() {
        return experienceDao.getAllExperiences();
    }
}
