package com.marcosledesma.ej04_crearvistasdesdecodigo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marcosledesma.ej04_crearvistasdesdecodigo.modelos.Alumno;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int CREAR_ALUMNO = 1;
    private final int VER_ACTIVITY = 2;
    private LinearLayout contenedor;
    private Button btnCrear;
    private ArrayList<Alumno> alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenedor = findViewById(R.id.contenedorMain);
        btnCrear = findViewById(R.id.btnCrearMain);

        alumnos = new ArrayList<>();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CrearAlumnoActivity.class);
                startActivityForResult(intent, CREAR_ALUMNO);
            }
        });
    }

    // Creamos on activityResult después de añadir el intent al bundle en crear alumno
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if (requestCode == CREAR_ALUMNO && resultCode == RESULT_OK){
            if (data != null){
                final Alumno alumno = data.getExtras().getParcelable("ALUMNO");
                // Guardo el alumno en el ArrayList
                alumnos.add(alumno);
                final int posicion = alumnos.size()-1;

                // Eliminar el alumno del Arraylist
                // alumnos.remove(alumno);

                if (alumno != null){
                    // CREAR TEXTVIEW DESDE CODIGO
                    TextView txtAlumno = new TextView(this);
                    txtAlumno.setText(alumno.getNombre());
                    txtAlumno.setTextSize(20);
                    txtAlumno.setTextColor(Color.GREEN);

                    // CREADO UN INTENT CON LA INFO
                    txtAlumno.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, VerAlumnoActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("ALUMNO", alumno);
                            bundle.putInt("POSICION", posicion);
                            intent.putExtras(bundle);
                            //
                            startActivityForResult(intent, VER_ACTIVITY);
                        }
                    });
                    // AGREGAR TXT AL CONTENEDOR
                    contenedor.addView(txtAlumno);
                }
            }
        }

        // Actualizar o borrar el alumno del ArrayList (btnGuardar y btnBorrar)
        if(requestCode == VER_ACTIVITY && resultCode == RESULT_OK){
            if (data != null){
                int posicion = data.getExtras().getInt("POSICION");
                Alumno alumno = data.getExtras().getParcelable("ALUMNO");
                // Si alumno está vacío lo elimino
                if (alumno == null){
                    alumnos.remove(posicion);
                }else{
                    // Actualizamos el alumno en el ArrayList
                    alumnos.get(posicion).setNombre(alumno.getNombre());
                    alumnos.get(posicion).setApellidos(alumno.getApellidos());
                }
                repintarElementos();
            }
        }
    }

    // Ver todas las actualizaciones que hemos hecho
    private void repintarElementos() {
        contenedor.removeAllViews();

        for (int i = 0; i < alumnos.size(); i++) {
            // CREAR TEXTVIEW DESDE CODIGO
            final Alumno alumno = alumnos.get(i);
            TextView txtAlumno = new TextView(this);
            txtAlumno.setText(alumno.getNombre());
            txtAlumno.setTextSize(20);
            txtAlumno.setTextColor(Color.GREEN);

            // CREADO UN INTENT CON LA INFO
            final int posicion = i;
            txtAlumno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, VerAlumnoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ALUMNO", alumno);
                    bundle.putInt("POSICION", posicion);
                    intent.putExtras(bundle);
                    //
                    startActivityForResult(intent, VER_ACTIVITY);
                }
            });
            // AGREGAR TXT AL CONTENEDOR
            contenedor.addView(txtAlumno);
        }
    }
}