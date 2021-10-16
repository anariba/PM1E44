package com.example.pm1e144.configuracion;

public class Transacciones {

    public static final String NameDataBase ="PM01DB";


    public static final String tablaPersonas = "personas";
    public static final String id="id";
    public static final String pais="pais";
    public static final String nombres="nombres";
    public static final String telefono="telefono";
    public static final String nota="nota";
    public static final String CreateTablePersonas = "CREATE TABLE personas(id INTEGER PRYMARY KEY AUTROINCREMENT,"+" pais TEXT,nombres TEXT,telefono INTEGER,nota TEXT)";
    public static final String DROPTablePersonas ="DROP TABLE IF EXISTS personas";





}
