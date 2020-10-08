package com.marcosledesma.ej04_crearvistasdesdecodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.marcosledesma.ej04_crearvistasdesdecodigo.modelos.Alumno;

public class VerAlumnoActivity extends AppCompatActivity {

    private EditText txtNombre, txtApellidos;
    private Button btnGuardar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_alumno);
        txtNombre = findViewById(R.id.txtNombreVer);
        txtApellidos = findViewById(R.id.txtApellidosVer);
        btnGuardar = findViewById(R.id.btnGuardarVer);
        btnEliminar = findViewById(R.id.btnEliminarVer);

        // Recojo el alumno del bundle de la otra actividad
        final Alumno alumno = getIntent().getExtras().getParcelable("ALUMNO");
        if (alumno != null) {
            final int posicion = getIntent().getExtras().getInt("POSICION");
            txtNombre.setText(alumno.getNombre());
            txtApellidos.setText(alumno.getApellidos());
            // Poner "título" a la actividad
            setTitle(alumno.getNombre());

            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!txtNombre.getText().toString().isEmpty() &&
                            !txtApellidos.getText().toString().isEmpty()) {
                        alumno.setNombre(txtNombre.getText().toString());
                        alumno.setApellidos(txtApellidos.getText().toString());
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("ALUMNO", alumno);
                        bundle.putInt("POSICION", posicion);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Recibir alumno del ArrayList y eliminarlo
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    // Meto la posicion del alumno
                    bundle.putInt("POSICION", posicion);
                    intent.putExtras(bundle);

                    // Ya tengo el intent para llevar al Main y borrar el alumno que lleva
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }
}