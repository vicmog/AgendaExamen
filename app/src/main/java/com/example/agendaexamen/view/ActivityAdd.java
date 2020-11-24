package com.example.agendaexamen.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.agendaexamen.R;
import com.example.agendaexamen.model.ValidacionCadenas;
import com.example.agendaexamen.model.entity.Contacto;
import com.example.agendaexamen.viewmodel.AgendaViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;



public class ActivityAdd extends AppCompatActivity {

    private AgendaViewModel viewModel;
    private TextInputEditText etNombre,etApellidos,etTelefono,etFechaNacimiento,etLocalidad,etCalle,etNumero;
    private TextInputLayout tilNombre,tilApellidos,tilTelefono,tilFecha,tilLocalidad,tilCalle,tilNumero;
    private Contacto contactoNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();

    }

    private void init() {
        tilNombre = findViewById(R.id.textInputLayoutNombre);
        tilApellidos = findViewById(R.id.textInputLayoutApellidos);
        tilTelefono = findViewById(R.id.textInputLayoutTelefono);
        tilFecha = findViewById(R.id.textInputLayoutFechaNacimiento);
        tilLocalidad = findViewById(R.id.textInputLayoutLocalidad);
        tilCalle = findViewById(R.id.textInputLayoutCalle);
        tilNumero = findViewById(R.id.textInputLayoutNumero);

        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etTelefono = findViewById(R.id.etTelefono);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etLocalidad = findViewById(R.id.etLocalidad);
        etCalle = findViewById(R.id.etCalle);
        etNumero = findViewById(R.id.etNumero);
        viewModel =  new ViewModelProvider(this).get(AgendaViewModel.class);
        Button btAdd = findViewById(R.id.btAnadir);
        Button btCancel = findViewById(R.id.btCancelar);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactoNuevo = new Contacto();
                if(validaFormulario()){
                    contactoNuevo.setNombre(etNombre.getText().toString());
                    contactoNuevo.setApellidos(etApellidos.getText().toString());
                    contactoNuevo.setTelefono(etTelefono.getText().toString());
                    contactoNuevo.setFechaNacimiento(etFechaNacimiento.getText().toString());
                    contactoNuevo.setLocalidad(etLocalidad.getText().toString());
                    contactoNuevo.setCalle(etCalle.getText().toString());
                    contactoNuevo.setNumero(Integer.parseInt(etNumero.getText().toString()));

                    viewModel.insert(contactoNuevo);

                    volverMain();
                }


            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverMain();
            }
        });


    }
    private void volverMain(){
        Intent intentMain = new Intent(this,MainActivity.class);
        startActivity(intentMain);
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