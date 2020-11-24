package com.example.agendaexamen.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.agendaexamen.model.dao.ContactoDao;
import com.example.agendaexamen.model.entity.Contacto;

@Database(entities = {Contacto.class}, version = 1, exportSchema = false)
public abstract class ContactoDataBase extends RoomDatabase {

public abstract ContactoDao getContactoDao();

    private volatile static ContactoDataBase INSTANCE;

    public static ContactoDataBase getDb(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), ContactoDataBase.class,"agendaDb.sqllite").build();
        }
        return INSTANCE;
    }
}
