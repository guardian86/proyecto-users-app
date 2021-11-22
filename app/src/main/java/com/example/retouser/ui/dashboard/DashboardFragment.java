package com.example.retouser.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retouser.R;
import com.example.retouser.databinding.FragmentDashboardBinding;
import com.example.retouser.model.Persona;
import com.example.retouser.viewholder.personaAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private personaAdapter personaAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = view.findViewById(R.id.recviewpersona);
        recyclerView.setLayoutManager(new CustomLinearLayoutManager(getActivity()));

        listarDatos();
        return  view;

    }

    private void listarDatos() {
        FirebaseRecyclerOptions<Persona> options =
                new FirebaseRecyclerOptions.Builder<Persona>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Persona"), Persona.class)
                        .build();

        Log.d("Options"," data : "+options);

        personaAdapter = new personaAdapter(options);
        recyclerView.setAdapter(personaAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        personaAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        personaAdapter.stopListening();
    }

    public class CustomLinearLayoutManager extends LinearLayoutManager {
        public CustomLinearLayoutManager(Context context) {
            super(context);
        }

        public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        //Generate constructors

        @Override
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }
}