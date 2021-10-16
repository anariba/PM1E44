package com.example.pm1e144;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pm1e144.configuracion.SQLliteConexion;
import com.example.pm1e144.configuracion.Transacciones;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner cmbpaises;
    EditText nombres,telefono,nota;
    Button btnagregar;
    Button btnlista;
    Button btnadd;
    String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres = (EditText) findViewById(R.id.txtnombre);
        telefono = (EditText) findViewById(R.id.txttelefono);
        nota = (EditText) findViewById(R.id.txtnota);
        btnagregar = (Button) findViewById(R.id.btnagregar);
        btnlista = (Button) findViewById(R.id.btnlista);

        cmbpaises = (Spinner) findViewById(R.id.cmbpaises);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cmb_paises, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbpaises.setAdapter((adapter));

        cmbpaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                p = cmbpaises.getItemAtPosition(i).toString();
              //  Toast.makeText(getApplicationContext(),"Registro Ingresado:" +     p.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btnlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityListView.class);
                startActivity(intent);
            }
        });

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()){
                    AgregarPersonas();
                }

            }
        });

    }

    public boolean validar(){
        Boolean retorno=true;
        String  c1=nombres.getText().toString();
        String  c2=telefono.getText().toString();
        String  c3=nota.getText().toString();

        if(c1.isEmpty()){
            nombres.setError("Campo Vacio");
            retorno=false;
        }
        if(c2.isEmpty()){
            telefono.setError("Campo Vacio");
            retorno=false;
        }
        if(c3.isEmpty()){
            nota.setError("Campo Vacio");
            retorno=false;
        }


        return true;
    }

    private void AgregarPersonas() {

        SQLliteConexion conexion = new SQLliteConexion(this, Transacciones.NameDataBase, null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.pais, p);
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.telefono, telefono.getText().toString());
        valores.put(Transacciones.nota, nota.getText().toString());

        Long resultado =db.insert(Transacciones.tablaPersonas,Transacciones.id,valores);
        Toast.makeText(getApplicationContext(),"Registro Ingresado:" + resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();



    }

    private void LimpiarPantalla() {
        nombres.setText("");

        telefono.setText("");
        nota.setText("");
    }
}