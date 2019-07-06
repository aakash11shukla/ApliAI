package com.example.apliai.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.apliai.models.Experience;
import com.example.apliai.repository.ExperienceRepository;

import java.util.List;


public class ExperienceViewModel extends ViewModel {

    public MutableLiveData<String> companyName = new MutableLiveData<>();
    public MutableLiveData<String> industry = new MutableLiveData<>();
    public MutableLiveData<String> designation = new MutableLiveData<>();
    public MutableLiveData<String> location = new MutableLiveData<>();
    public MutableLiveData<String> from = new MutableLiveData<>();
    public MutableLiveData<String> to = new MutableLiveData<>();

    private ExperienceRepository repository;

    public void init(Context context){
        repository = new ExperienceRepository(context);
    }

    public void addExperience(Experience experience){
        repository.addExperience(experience);
    }

    public LiveData<List<Experience>> getAllExperiences(){
        return repository.getAllExperiences();
    }

}
