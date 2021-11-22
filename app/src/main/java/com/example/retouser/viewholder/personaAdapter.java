package com.example.retouser.viewholder;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retouser.R;
import com.example.retouser.model.Persona;
import com.example.retouser.viewmore;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class personaAdapter extends FirebaseRecyclerAdapter<Persona, personaAdapter.personaViewHolder> {


    public personaAdapter(@NonNull FirebaseRecyclerOptions<Persona> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull personaAdapter.personaViewHolder holder, int position, @NonNull Persona model) {
        holder.titleTextView.setText(model.getName());
        holder.descripcion.setText(model.getDescripcion());
        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), viewmore.class);
                //pasa la informacion selecionada en la lista
                intent.putExtra("persona", model);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public personaAdapter.personaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_persona, parent, false);
        return new personaViewHolder(inflate);
    }

    public class personaViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, descripcion;
        Button ver;

        public personaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descripcion=itemView.findViewById(R.id.textdescripcion);
            ver=itemView.findViewById(R.id.Ver);
        }
    }
}
