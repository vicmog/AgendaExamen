package com.example.agendaexamen.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.agendaexamen.model.AgendaRepository;
import com.example.agendaexamen.model.entity.Contacto;

import java.util.List;

public class AgendaViewModel extends AndroidViewModel {

    private AgendaRepository repository;
    private LiveData<List<Contacto>> listLiveData;


    public AgendaViewModel(@NonNull Application application) {
        super(application);
        repository = new AgendaRepository(application);
        listLiveData = repository.getLiveListData();
    }



    public LiveData<List<Contacto>> getAllContactos(){
        return listLiveData;
    }

    public void insert(Contacto c){
        repository.insert(c);
    }
    public int delete(Contacto c){
       return repository.delete(c);
    }

    public void update(String nombre, String apellidos,String telefono,String fecha,String localidad,String calle,int numero,int id){

                repository.update(nombre,apellidos,telefono,fecha,localidad,calle,numero,id);


    }

}
