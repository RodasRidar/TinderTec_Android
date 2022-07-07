package com.example.tindertec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tindertec.models.GeneroInteres;
import com.example.tindertec.models.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProfileFragment extends AppCompatActivity {

    Usuario user ;
    Spinner cboInteresGen;
    public Spinner cboCarrera;
    Spinner cboSede;

    List<String> lstInteresGen;
    List<String> lstCarrera;
    List<String> lstSede;

    EditText txtNombre, txtInfo;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_profile);
        user = LoginActivity.userInSession;

        //Combos
        cboInteresGen = (Spinner) findViewById(R.id.cboInteresGen);
        cboCarrera = (Spinner) findViewById(R.id.cboCarrera);
        cboSede = (Spinner) findViewById(R.id.cboSede);

        //TxtBoxes
        txtNombre = findViewById(R.id.txtNombre);
        txtInfo = findViewById(R.id.txtInfo);

        btnGuardar = findViewById(R.id.btnGuardar);

        try {
            String nombrePublico = user.getNombres();
            txtNombre.setText(nombrePublico);

            String infoUsuario = user.getDescripcion();
            txtInfo.setText(infoUsuario);


            cboCarrera.setSelection(user.getCod_carrera());
            cboInteresGen.setSelection(user.getCod_interes());
            cboSede.setSelection(user.getCod_sede());
            Log.e("Seguimiento",""+user.getCod_carrera()+user.getCod_interes()+user.getCod_sede());


        }catch (Exception e){
            e.printStackTrace();
        }

        //Metodos
        cboInteresGenero();
        cboCarrera();
        cboSede();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codInteres=cboInteresGen.getSelectedItemId()+1+"";
                String codCarrera=cboCarrera.getSelectedItemId()+1+"";
                String codSede=cboSede.getSelectedItemId()+1+"";
                updateUsuario(user.getCod_usu(),txtNombre.getText().toString(),txtInfo.getText().toString(),codInteres,codCarrera, codSede);
            }
        });

        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstInteresGen);
        cboInteresGen.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2= new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstCarrera);
        cboCarrera.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3= new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstSede);
        cboSede.setAdapter(adapter3);
    }

    private void cboInteresGenero(){
        lstInteresGen = new ArrayList<>();
        lstInteresGen.add("Masculino");
        lstInteresGen.add("Femenino");
        lstInteresGen.add("Ambos");
        Log.e("COMBO",lstInteresGen.toString());
    }

    private void cboCarrera(){
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

    private void cboSede(){
        lstSede = new ArrayList<>();
        lstSede.add("Breña");
        lstSede.add("Bella Vista");
        lstSede.add("Lima Centro");
        lstSede.add("San Juan de Lurigancho");
        lstSede.add("Independencia");
        Log.e("COMBO",lstSede.toString());
    }

    private void updateUsuario(final int codUsuario,final String nombre,final String descripcion, final String codInteres,final String codCarrera,final String codSede){
        Usuario usu = new Usuario();
        String url ="http://192.168.3.26:8080/Usuario/Guardar";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(EditProfileFragment.this, "Actualizado.", Toast.LENGTH_LONG).show();
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
                params.put("Cod_usu", String.valueOf(codUsuario));
                params.put("Nombres",nombre);
                params.put("Descripcion",descripcion);
                params.put("CodInteres",codInteres);
                params.put("CodCarrera",codCarrera);
                params.put("CodSede",codSede);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
        if(postRequest.equals("")){
            Toast.makeText(this,"No puedes dejar un campo vacío.",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Cambios guardados.",Toast.LENGTH_LONG).show();

        }

    }
}