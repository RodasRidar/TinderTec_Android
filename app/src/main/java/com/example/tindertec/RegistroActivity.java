package com.example.tindertec;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tindertec.models.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    Button btnenviar,btnFecha;
    Spinner listaGenerosInteres;
    Spinner listaCarrera;
    Spinner listaSede;
    Spinner listaGeneros;
    List<String> lstGeneroInteres;
    List<String> lstGenero;
    List<String> lstCarrera;
    List<String> lstSede;
    TextInputEditText txtUsuario, txtCorreo,txtContraseña,txtDescripcion,txtFoto;


    public DatePickerDialog datePickerDialog;
    public Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnenviar=findViewById(R.id.btnRegistro);
        txtUsuario = findViewById(R.id.registroNombre);
        txtCorreo = findViewById(R.id.registroCorreo);
        txtContraseña=findViewById(R.id.registroContraseña);
        txtDescripcion=findViewById(R.id.registroDescripcion);
        txtFoto=findViewById(R.id.registroFoto1);
        btnenviar = findViewById(R.id.btnRegistro);
        btnFecha=findViewById(R.id.datePickerButton);
        ListaGenero();
        ListaGeneroInteres();
        ListaCarrera();
        ListaSede();
        //combos generos
        listaGeneros=(Spinner)findViewById(R.id.comboBoxGenero);
        listaGenerosInteres=(Spinner)findViewById(R.id.comboBoxGeneroInteres);
        listaCarrera=(Spinner)findViewById(R.id.comboCarrera);
        listaSede=(Spinner)findViewById(R.id.comboSede);

        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CBOX
                String codInteres=listaGenerosInteres.getSelectedItemId()+1+"";
                String codGenero=listaGeneros.getSelectedItemId()+1+"";
                String codCarrera=listaCarrera.getSelectedItemId()+1+"";
                String codSede=listaSede.getSelectedItemId()+1+"";
                //  String fecha="2000-01-01";
                String fecha=  btnFecha.getText().toString();
                String[] parts=fecha.split(" ");
                String DIA = parts[1];
                String MES = parts[0];
                String ANO = parts[2];
                String FechafULL=ANO+"-"+MES+"-"+DIA;
                // Log.e("FECHA",FechafULL);
                registrarusuario(FechafULL,codInteres,codGenero,codSede,codCarrera,txtUsuario.getText().toString(),txtCorreo.getText().toString(),txtContraseña.getText().toString(),txtDescripcion.getText().toString(),txtFoto.getText().toString());
            }
        });

        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstGenero);
        listaGeneros.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2= new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstGeneroInteres);
        listaGenerosInteres.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3= new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstCarrera);
        listaCarrera.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4= new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstSede);
        listaSede.setAdapter(adapter4);

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

    }


    private void  ListaGenero(){
        lstGenero = new ArrayList<>();
        lstGenero.add("Masculino");
        lstGenero.add("Femenino");
        Log.e("COMBO",lstGenero.toString());
    }
    private void  ListaGeneroInteres(){
        lstGeneroInteres = new ArrayList<>();
        lstGeneroInteres.add("Masculino");
        lstGeneroInteres.add("Femenino");
        lstGeneroInteres.add("Ambos");
        Log.e("COMBO",lstGeneroInteres.toString());
    }

    private void  ListaCarrera(){
        lstCarrera = new ArrayList<>();
        lstCarrera.add("Computacion e informatica");
        lstCarrera.add("Traduccion e Interpretacion");
        lstCarrera.add("Comunicacion y arte digital");
        lstCarrera.add("Gestion de Recursos Humanos");
        lstCarrera.add("Administracion de Redes");
        lstCarrera.add("Publicidad y Branding");
        lstCarrera.add("Diseño de interiores");
        lstCarrera.add("Mecatronica");
        lstCarrera.add("Administracion de empresas");
        lstCarrera.add("Administracion de Negocios Internacionales");
        lstCarrera.add("Contabilidad");
        Log.e("COMBO",lstCarrera.toString());
    }

    private void  ListaSede(){
        lstSede = new ArrayList<>();
        lstSede.add("Breña");
        lstSede.add("Bella Vista");
        lstSede.add("Lima Centro");
        lstSede.add("San Juan de Lurigancho");
        lstSede.add("Independencia");
        Log.e("COMBO",lstSede.toString());
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month= month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = makeDateString(day, month, year);
                //
                dateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month)+" "+day+" "+year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "1";
        if(month == 2)
            return "2";
        if(month == 3)
            return "3";
        if(month == 4)
            return "4";
        if(month == 5)
            return "5";
        if(month == 6)
            return "6";
        if(month == 7)
            return "7";
        if(month == 8)
            return "8";
        if(month == 9)
            return "9";
        if(month == 10)
            return "10";
        if(month == 11)
            return "11";
        if(month == 12)
            return "12";
        return "1";
    }



    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
    private void registrarusuario(final String fecha,final String codInteres,final String codGenero,final String codSede,final String codCarrera,final String nombre,final String correo,final String contraseña,final String descripcion,final String foto){
        Usuario usu=new Usuario();
        String urlRegistro ="http://192.168.3.26:8080/Usuario/Registrar";
        StringRequest postRequest = new StringRequest(Request.Method.POST, urlRegistro, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(RegistroActivity.this,"Se registro correctamente", Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.getMessage());

            }
        })
        {
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("Nombres",nombre);
                params.put("Email",correo);
                params.put("Clave",contraseña);
                params.put("Descripcion",descripcion);
                params.put("Foto1",foto);
                params.put("CodInteres",codInteres);
                params.put("CodGenero",codGenero);
                params.put("CodCarrera",codCarrera);
                params.put("CodSede",codSede);
                params.put("FechaNacimiento",fecha);

                return params;


            }

        };
        Volley.newRequestQueue(this).add(postRequest);
        if(postRequest.equals("")){
            Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Registros Existoso",Toast.LENGTH_LONG).show();
            Intent i2 = new Intent(RegistroActivity.this,LoginActivity.class);
            startActivity(i2);
            finish();
        }

    }

    private String DateFormato(String fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
        String fec=null;
        try {
            Date fechaNacimiento = sdf.parse(fecha);
            fec=sdf.format(fechaNacimiento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fec;
    }

}

