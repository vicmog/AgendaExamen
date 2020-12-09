package com.example.agendaexamen.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.agendaexamen.R;
import com.example.agendaexamen.model.ValidacionCadenas;
import com.example.agendaexamen.model.entity.Contacto;
import com.example.agendaexamen.viewmodel.AgendaViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ActivityUpdate extends AppCompatActivity {


    private AgendaViewModel viewModel;
    private Contacto contactoUpdate;
    private TextInputEditText etNombre,etApellidos,etTelefono,etFechaNacimiento,etLocalidad,etCalle,etNumero;
    private TextInputLayout tilNombre,tilApellidos,tilTelefono,tilFecha,tilLocalidad,tilCalle,tilNumero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        init();
    }

    private void init() {

        tilNombre = findViewById(R.id.tilNombreUpdate);
        tilApellidos = findViewById(R.id.tilApellidosUpdate);
        tilTelefono = findViewById(R.id.tilTelefonoUpdate);
        tilFecha = findViewById(R.id.tilFechaUpdate);
        tilLocalidad = findViewById(R.id.tilLocalidadUpdate);
        tilCalle = findViewById(R.id.tilCalleUpdate);
        tilNumero = findViewById(R.id.tilNumeroUpdate);

        etNombre = findViewById(R.id.etUpdateNombre);
        etApellidos = findViewById(R.id.etUpdateApellidos);
        etTelefono = findViewById(R.id.etUpdateTelefono);
        etFechaNacimiento = findViewById(R.id.etUpdateFecha);
        etLocalidad = findViewById(R.id.etUpdateLocalidad);
        etCalle = findViewById(R.id.etUpdateCalle);
        etNumero = findViewById(R.id.etUpdateNumero);


        viewModel = new ViewModelProvider(this).get(AgendaViewModel.class);

        recogeDatosIntent();
        cargaContacto();


        Button btCancelar = findViewById(R.id.btCancelarUpdate);
        Button btActualizar = findViewById(R.id.btActualizar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverMain();
            }
        });

        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validaFormulario()){
                   Contacto contactoActualizado= new Contacto();
                    contactoActualizado.setNombre(etNombre.getText().toString());
                    contactoActualizado.setApellidos(etApellidos.getText().toString());
                    contactoActualizado.setTelefono(etTelefono.getText().toString());
                    contactoActualizado.setFechaNacimiento(etFechaNacimiento.getText().toString());
                    contactoActualizado.setLocalidad(etLocalidad.getText().toString());
                    contactoActualizado.setCalle(etCalle.getText().toString());
                    contactoActualizado.setNumero(Integer.parseInt(etNumero.getText().toString()));

                   viewModel.update(contactoActualizado.getNombre(),contactoActualizado.getApellidos(),contactoActualizado.getTelefono(),contactoActualizado.getFechaNacimiento(),contactoActualizado.getLocalidad(),contactoActualizado.getCalle(),contactoActualizado.getNumero(),contactoUpdate.getId());
                   volverMain();
                }

            }
        });


    }
    private void  cargaContacto(){
        etNombre.setText(contactoUpdate.getNombre());
        etApellidos.setText(contactoUpdate.getApellidos());
        etTelefono.setText(contactoUpdate.getTelefono());
        etFechaNacimiento.setText(contactoUpdate.getFechaNacimiento());
        etLocalidad.setText(contactoUpdate.getLocalidad());
        etCalle.setText(contactoUpdate.getCalle());
        etNumero.setText(contactoUpdate.getNumero()+"");


    }
    private void volverMain(){
       /* Intent intentMain = new Intent(this,MainActivity.class);
        startActivity(intentMain);*/
        finish();
    }
    private void recogeDatosIntent(){
        Intent intenPosicion =getIntent();
        contactoUpdate = new Contacto();
        contactoUpdate.setId(intenPosicion.getIntExtra("id",0));
        contactoUpdate.setNombre(intenPosicion.getStringExtra("nombre"));
        contactoUpdate.setApellidos(intenPosicion.getStringExtra("apellidos"));
        contactoUpdate.setTelefono(intenPosicion.getStringExtra("telefono"));
        contactoUpdate.setFechaNacimiento(intenPosicion.getStringExtra("fecha"));
        contactoUpdate.setLocalidad(intenPosicion.getStringExtra("localidad"));
        contactoUpdate.setCalle(intenPosicion.getStringExtra("calle"));
        contactoUpdate.setNumero(intenPosicion.getIntExtra("numero",0));
    }
    private boolean validaFormulario(){
        boolean validado =false;

        if(!ValidacionCadenas.campoVacio(etNombre.getText().toString())){
            tilNombre.setErrorEnabled(false);
            if(!ValidacionCadenas.campoVacio(etApellidos.getText().toString())){
                tilApellidos.setErrorEnabled(false);
                if(!ValidacionCadenas.campoVacio(etTelefono.getText().toString())){
                    tilTelefono.setErrorEnabled(false);
                    if(ValidacionCadenas.cadenaEsTelefono(etTelefono.getText().toString())){
                        tilTelefono.setErrorEnabled(false);
                        if(!ValidacionCadenas.campoVacio(etFechaNacimiento.getText().toString())){
                            tilFecha.setErrorEnabled(false);
                            if(!ValidacionCadenas.campoVacio(etLocalidad.getText().toString())){
                                tilLocalidad.setErrorEnabled(false);
                                if(!ValidacionCadenas.campoVacio(etCalle.getText().toString())){
                                    tilCalle.setErrorEnabled(false);
                                    if(!ValidacionCadenas.campoVacio(etNumero.getText().toString())){
                                        if(ValidacionCadenas.cadenaEsNumerica(etNumero.getText().toString())){
                                            tilNumero.setErrorEnabled(false);
                                            validado = true;
                                        }else{
                                            tilNumero.setErrorEnabled(false);
                                            tilNumero.setError("Numero no valido");
                                        }
                                    }else{
                                        tilNumero.setError("Campo vacio");
                                    }
                                }else{
                                    tilCalle.setError("Campo vacio");
                                }
                            }else{
                                tilLocalidad.setError("Campo vacio");
                            }
                        }else{
                            tilFecha.setError("Campo vacio");
                        }

                    }else{
                        tilTelefono.setError("Telefono no valido");
                    }

                }else{
                    tilTelefono.setError("Campo vacio");
                }
            }else{
                tilApellidos.setError("Campo vacio");
            }
        }else{
            tilNombre.setError("Campo vacio");
        }
        return  validado;
    }

}