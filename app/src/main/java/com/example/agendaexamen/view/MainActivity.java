package com.example.agendaexamen.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.agendaexamen.R;
import com.example.agendaexamen.model.entity.Contacto;
import com.example.agendaexamen.view.RecyclerViewAdapter.miRecyclerViewAdapter;
import com.example.agendaexamen.viewmodel.AgendaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AgendaViewModel viewModel;
    private FloatingActionButton buttonAdd;
    private List<Contacto>lista;
    private RecyclerView miRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }



    private void init() {
        buttonAdd = findViewById(R.id.btAdd);

        viewModel =  new ViewModelProvider(this).get(AgendaViewModel.class);
        rellenaRecyclerView();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            abreAdd();
            }
        });


    }
    private void rellenaRecyclerView(){
        viewModel.getAllContactos().observe(this, new Observer<List<Contacto>>() {
            @Override
            public void onChanged(List<Contacto> contactos) {
               lista =new ArrayList<>();
                for (int i = 0; i < contactos.size(); i++) {

                    lista.add(contactos.get(i));
                }
                creaRecyclerView();
            }
        });


    }
    private void creaRecyclerView(){
        miRecyclerView = findViewById(R.id.miRecycler);
        miRecyclerViewAdapter adapter = new miRecyclerViewAdapter(lista,this);
        miRecyclerView.setAdapter(adapter);
        miRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void abreAdd(){
        Intent intentAdd = new Intent(this, ActivityAdd.class);
        startActivity(intentAdd);
    }
}