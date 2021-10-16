package com.example.pm1e144;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pm1e144.configuracion.SQLliteConexion;
import com.example.pm1e144.configuracion.Transacciones;
import com.example.pm1e144.tablas.Personas;

import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {
    SQLliteConexion conexion;
    ListView listapersonas;
    Button btnregresar;
    ArrayList<Personas> lista;
    ArrayList<String> Arreglopersonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        btnregresar = (Button) findViewById(R.id.btnregresar) ;

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        conexion = new SQLliteConexion(this, Transacciones.NameDataBase, null,1);
        listapersonas = (ListView) findViewById(R.id.listapersonas);
        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,Arreglopersonas);
        listapersonas.setAdapter(adp);


    }

    private void ObtenerListaPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas lista_personas = null;
        lista = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery( "select * from "+ Transacciones.tablaPersonas,null);

        while(cursor.moveToNext()){
            lista_personas = new Personas();
          //  lista_personas.setId(cursor.getInt(0));
            lista_personas.setPais(cursor.getString(1));
            lista_personas.setNombres(cursor.getString(2));
            lista_personas.setTelefono(cursor.getInt(3));

            lista.add(lista_personas);
        }
        cursor.close();
        filllist();
    }

    private void filllist() {
        Arreglopersonas = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            Arreglopersonas.add(
                    lista.get(i).getPais() + " | " +
                    lista.get(i).getNombres() + " | " +
                    lista.get(i).getTelefono());
        }
    }
}