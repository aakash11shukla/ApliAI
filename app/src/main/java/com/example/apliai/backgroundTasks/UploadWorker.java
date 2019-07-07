package com.example.apliai.backgroundTasks;

import android.content.Context;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.apliai.database.ExperienceDatabase;
import com.example.apliai.models.Experience;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadWorker extends Worker {

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        String id = getInputData().getString(SyncStateContract.Constants._ID);

        ExperienceDatabase db = ExperienceDatabase.getDatabase(getApplicationContext());
        Experience experience = db.experienceDao().getExperienceByExperienceId(id);

        String uniqueId = getInputData().getString("UserId");

        FirebaseDatabase mExperienceDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mExperienceReference = mExperienceDatabase.getReference().child("EXPERIENCE/" + uniqueId + "/" + id);
        mExperienceReference.child("companyName").setValue(experience.getCompanyName());
        mExperienceReference.child("industry").setValue(experience.getIndustry());
        mExperienceReference.child("designation").setValue(experience.getDesignation());
        mExperienceReference.child("location").setValue(experience.getLocation());
        mExperienceReference.child("fromDate").setValue(experience.getFromDate());
        mExperienceReference.child("toDate").setValue(experience.getToDate());

        return Result.success();
    }

}
