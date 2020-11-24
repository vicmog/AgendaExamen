package com.example.agendaexamen.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacto")
public class Contacto {


    public Contacto() {
    }

    public Contacto(@NonNull String nombre, @NonNull String apellidos, @NonNull String telefono, @NonNull String fechaNacimiento, @NonNull String localidad, @NonNull String calle, int numero) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.localidad = localidad;
        this.calle = calle;
        this.numero = numero;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @NonNull
    @ColumnInfo(name = "telefono")
    private String telefono;

    @NonNull
    @ColumnInfo(name = "fechaNacimiento")
    private String fechaNacimiento;

    @NonNull
    @ColumnInfo(name = "localidad")
    private String localidad;

    @NonNull
    @ColumnInfo(name = "calle")
    private String calle;

    @NonNull
    @ColumnInfo(name = "numero")
    private int numero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@NonNull String apellidos) {
        this.apellidos = apellidos;
    }

    @NonNull
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NonNull String telefono) {
        this.telefono = telefono;
    }

    @NonNull
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(@NonNull String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @NonNull
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(@NonNull String localidad) {
        this.localidad = localidad;
    }

    @NonNull
    public String getCalle() {
        return calle;
    }

    public void setCalle(@NonNull String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return  id +"  "+ nombre  +"  "+ apellidos +"  "+  telefono +"  "+ fechaNacimiento +"  "+ localidad +"  "+ calle +"  "+  numero ;
    }
}
