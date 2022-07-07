package com.example.tindertec.utilidades;

public class Utilidades {

    //constantes campos tabla usuario
    public static final String TABLA_USUARIO = "tb_usuario";
    public static final String CAMPO_USUARIO_NOMBRES="nombres";
    public static final String CAMPO_USUARIO_FOTO1="foto1";
    public static final String CAMPO_USUARIO_FOTO2="foto2";
    public static final String CAMPO_USUARIO_FOTO3="foto3";
    public static final String CAMPO_USUARIO_FOTO4="foto4";
    public static final String CAMPO_USUARIO_FOTO5="foto5";
    public static final String CAMPO_USUARIO_FECHA_NACIMIENTO="fecha_nacimiento";
    public static final String CAMPO_USUARIO_DESCRIPCION="descripcion";
    public static final String CAMPO_USUARIO_COD_CARRERA="cod_carrera";
    public static final String CAMPO_USUARIO_COD_SEDE="cod_sede";
    public static final String CAMPO_USUARIO_COD_INTERES="cod_interes";
    public static final String CAMPO_USUARIO_COD_GENERO="cod_genero";
    public static final String CAMPO_USUARIO_COD_SUSCRIPCION="cod_suscripcion";


    public static  final String CREAR_TABLA_USUARIO  = "CREATE TABLE " +
            " " +
            TABLA_USUARIO
            + " ( " +
            CAMPO_USUARIO_NOMBRES
            + " TEXT , "+
            CAMPO_USUARIO_FOTO1
            + " TEXT , "+
            CAMPO_USUARIO_FOTO2
            + " TEXT , "+
            CAMPO_USUARIO_FOTO3
            + " TEXT , "+
            CAMPO_USUARIO_FOTO4
            + " TEXT , "+
            CAMPO_USUARIO_FOTO5
            + " TEXT , "+
            CAMPO_USUARIO_DESCRIPCION
            + " TEXT , "+
            CAMPO_USUARIO_COD_CARRERA
            + " INTEGER , "+
            CAMPO_USUARIO_COD_SEDE
            + " INTEGER , "+
            CAMPO_USUARIO_COD_INTERES
            + " INTEGER , "+
            CAMPO_USUARIO_COD_GENERO
            + " INTEGER , "+
            CAMPO_USUARIO_COD_SUSCRIPCION
            + " INTEGER , "+
            CAMPO_USUARIO_FECHA_NACIMIENTO
            + " TEXT) ";

    public static  final String INSERTAR_REGISTROS_USUARIO  ="INSERT INTO " +
            Utilidades.TABLA_USUARIO +
            " ( " + Utilidades.CAMPO_USUARIO_NOMBRES +  " , " + Utilidades.CAMPO_USUARIO_FOTO1+  " , " + Utilidades.CAMPO_USUARIO_FOTO2
            +  " , " + Utilidades.CAMPO_USUARIO_FOTO3+  " , " + Utilidades.CAMPO_USUARIO_FOTO4+  " , " + Utilidades.CAMPO_USUARIO_FOTO5
            +  " , " + Utilidades.CAMPO_USUARIO_DESCRIPCION+  " , " + Utilidades.CAMPO_USUARIO_COD_CARRERA+  " , " + Utilidades.CAMPO_USUARIO_COD_SEDE
            +  " , " + Utilidades.CAMPO_USUARIO_COD_INTERES+  " , " + Utilidades.CAMPO_USUARIO_COD_GENERO+  " , " + Utilidades.CAMPO_USUARIO_COD_SUSCRIPCION
            +  " , " + Utilidades.CAMPO_USUARIO_FECHA_NACIMIENTO
            +") VALUES ("
            +"'Richard'"+","+"'1231Richard1231'"+")";



}
