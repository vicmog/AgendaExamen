package com.example.agendaexamen.view.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendaexamen.R;
import com.example.agendaexamen.model.entity.Contacto;
import com.example.agendaexamen.view.ActivityAdd;
import com.example.agendaexamen.view.ActivityUpdate;
import com.example.agendaexamen.view.MainActivity;
import com.example.agendaexamen.viewmodel.AgendaViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class miRecyclerViewAdapter extends RecyclerView.Adapter<miRecyclerViewAdapter.ViewHolder> implements PopupMenu.OnMenuItemClickListener{

private List<Contacto> listaContactos;
private Context contexto;
private AgendaViewModel viewmodel;
private Contacto c = new Contacto();


    public miRecyclerViewAdapter(List<Contacto> list, Context context) {

        this.listaContactos = list;
        contexto = context;
        viewmodel = new ViewModelProvider((ViewModelStoreOwner) context).get(AgendaViewModel.class);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista,parent,false);
        ViewHolder holder = new ViewHolder(vista);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvContacto.setText(listaContactos.get(position).toString());

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c = listaContactos.get(position);

                showMenu(v);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvContacto;
        ConstraintLayout parent_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContacto = itemView.findViewById(R.id.tvContacto);
            parent_layout = itemView.findViewById(R.id.clLayout);
        }
    }
    public void showMenu(View anchor) {
        PopupMenu popup = new PopupMenu(contexto, anchor);
        popup.setOnMenuItemClickListener(this);
        popup.getMenuInflater().inflate(R.menu.menupopup, popup.getMenu());
        popup.show();

    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
    switch (item.getItemId()){

        case R.id.UpdatePopup:pasaDatosUpdate();

            break;
        case R.id.deletePopup:

            viewmodel.delete(c);
            Toast.makeText(contexto, "Contacto "+c.toString()+"  borrado ", Toast.LENGTH_SHORT).show();
            break;

    }


        return false;
    }
    private void pasaDatosUpdate(){
        Intent intentUpdate =new Intent(contexto, ActivityUpdate.class);
        intentUpdate.putExtra("id",c.getId());
        intentUpdate.putExtra("nombre",c.getNombre());
        intentUpdate.putExtra("apellidos",c.getApellidos());
        intentUpdate.putExtra("telefono",c.getTelefono());
        intentUpdate.putExtra("fecha",c.getFechaNacimiento());
        intentUpdate.putExtra("localidad",c.getLocalidad());
        intentUpdate.putExtra("calle",c.getCalle());
        intentUpdate.putExtra("numero",c.getNumero());
        contexto.startActivity(intentUpdate);
    }
}
