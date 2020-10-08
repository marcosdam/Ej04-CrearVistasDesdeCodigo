package com.marcosledesma.ej04_crearvistasdesdecodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.marcosledesma.ej04_crearvistasdesdecodigo.modelos.Alumno;

public class CrearAlumnoActivity extends AppCompatActivity {
    private EditText txtNombre, txtApellidos;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alumno);

        txtNombre = findViewById(R.id.txtNombreCrearAlumno);
        txtApellidos = findViewById(R.id.txtApellidosCrearAlmuno);
        btnGuardar = findViewById(R.id.btnGuardarCrearAlumno);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CREAR Alumno, meterlo en un bundle, meterlo en un intent, resultcode
                if(!txtNombre.getText().toString().isEmpty() && !txtApellidos.getText().toString().isEmpty()){
                    Alumno alumno = new Alumno(txtNombre.getText().toString(), txtApellidos.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ALUMNO", alumno);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}