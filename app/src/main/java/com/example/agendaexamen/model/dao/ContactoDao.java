package com.example.agendaexamen.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agendaexamen.model.entity.Contacto;

import java.util.List;

@Dao
public interface ContactoDao {



    @Delete
    int delete(Contacto contacto);

    @Query("select * from contacto where id = :id")
    Contacto get(int id);

    @Query("select * from contacto")
    LiveData<List<Contacto>> getAll();
    @Insert
    long insert(Contacto contacto);
    @Update
    int update (Contacto contacto);

    @Query("UPDATE contacto SET nombre = :nombre, apellidos = :apellidos,telefono = :telefono,fechaNacimiento = :fecha,localidad =:localidad,calle = :calle,numero =:numero WHERE id =:id")
    void update2(String nombre, String apellidos,String telefono,String fecha,String localidad,String calle,int numero,int id);


}
