package com.example.apliai.backgroundTasks;

import android.content.Context;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.apliai.database.ExperienceDatabase;
import com.example.apliai.models.Experience;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadWorker extends Worker {

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        long id = getInputData().getLong(SyncStateContract.Constants._ID, 0);

        ExperienceDatabase db = ExperienceDatabase.getDatabase(getApplicationContext());
        Experience experience = db.experienceDao().getExperienceByExperienceId(id);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uniqueId;

        if(user == null){
            uniqueId = "hyKA4ElBpUMAF8o9SK1XedKfsUo2";
        }else {
            uniqueId = user.getUid();
        }

        FirebaseDatabase mExperienceDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mExperienceReference = mExperienceDatabase.getReference().child(("EXPERIENCE/" + uniqueId));
        mExperienceReference.push().setValue(experience);

        return Result.success();
    }

}
