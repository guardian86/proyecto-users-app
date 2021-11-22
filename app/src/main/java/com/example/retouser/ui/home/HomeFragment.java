package com.example.retouser.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.retouser.R;
import com.example.retouser.databinding.FragmentHomeBinding;
import com.example.retouser.model.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.UUID;

public class HomeFragment extends Fragment {

    private Spinner spinner;
    EditText name, descripcion, imagenurl;
    TextView validacion;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button agregar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        spinner = view.findViewById(R.id.generolist);
        name = view.findViewById(R.id.campo_nombre);
        descripcion = view.findViewById(R.id.editTextTextMultiLine);
        imagenurl = view.findViewById(R.id.campo_url);
        agregar = view.findViewById(R.id.button);
        validacion= view.findViewById(R.id.validacion);

        initDataSpinnerGenero();
        inicializarFirebase();

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar();
            }
        });
        return view;
    }

    private void initDataSpinnerGenero() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.lista, android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private  void agregar(){
        String nombre = name.getText().toString();
        String descr = descripcion.getText().toString();
        String url = imagenurl.getText().toString();
        String textgenero = spinner.getSelectedItem().toString();

        Toast.makeText(getActivity(),"Agregar", Toast.LENGTH_LONG).show();
        if (nombre.equals("")||descr.equals("")||url.equals("")||textgenero.equals("")){
            validacion();
        }
        else {
            Persona p = new Persona();
            p.setUid(UUID.randomUUID().toString());
            p.setName(nombre);
            p.setDescripcion(descr);
            p.setGenero(textgenero);
            p.setImagenURL(url);
            //se guarda la información de FIrebase con nombre de la tabla Persona
            databaseReference.child("Persona").child(p.getUid()).setValue(p);
            Toast.makeText(getActivity(), "Agregado", Toast.LENGTH_LONG).show();
            limpiarCajas();
        }

    }
    private void limpiarCajas() {
        name.setText("");
        descripcion.setText("");
        imagenurl.setText("");
        validacion.setText("");
    }

    private void validacion() {
        String nombre = name.getText().toString();
        String imagen = imagenurl.getText().toString();
        String descr = descripcion.getText().toString();

        if (nombre.equals("")){
            validacion.setText("Ingrese el nombre");
        }
        else if (descr.equals("")){
            validacion.setText("Ingrese la descripción");
        }
        else if (imagen.equals("")){
            validacion.setText("Ingrese la URL de la imagen");
        }
    }

}