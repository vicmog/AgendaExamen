package com.example.agendaexamen.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.agendaexamen.model.dao.ContactoDao;
import com.example.agendaexamen.model.entity.Contacto;
import com.example.agendaexamen.model.room.ContactoDataBase;

import java.util.List;

public class AgendaRepository {

    private ContactoDataBase db;
    private ContactoDao dao;
    private LiveData<List<Contacto>>listLiveData;
    private int numeroBorrado;



    public AgendaRepository(Application context) {
        db = ContactoDataBase.getDb(context);
        dao = db.getContactoDao();
        listLiveData = dao.getAll();

    }


    public LiveData<List<Contacto>> getLiveListData(){
        return listLiveData;
    }

    public void insert(Contacto c){

        Thread hebraInsertRepository = new Thread(){

            @Override
            public void run() {
                super.run();
                dao.insert(c);
            }
        };
        hebraInsertRepository.start();

    }
    public int delete(Contacto c){
        Thread hebraBorraRepository = new Thread(){

            @Override
            public void run() {
                super.run();
              numeroBorrado = dao.delete(c);

            }
        };
        hebraBorraRepository.start();
        return numeroBorrado;
    }
    public void update(String nombre, String apellidos,String telefono,String fecha,String localidad,String calle,int numero,int id){
        Thread hebraUpdate = new Thread(){
            @Override
            public void run() {
                super.run();
                dao.update2(nombre,apellidos,telefono,fecha,localidad,calle,numero,id);

            }
        };
        hebraUpdate.start();

    }



}
