package com.example.tindertec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProfileFragment extends AppCompatActivity {

    Spinner cboInteresGen;
    Spinner cboCarrera;
    Spinner cboSede;

    List<String> lstInteresGen;
    List<String> lstCarrera;
    List<String> lstSede;

    EditText txtNombre, txtInfo, txtInteresGen, txtCarrera, txtSede;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_profile);

        //Combos
        cboInteresGen = (Spinner) findViewById(R.id.cboInteresGen);
        cboCarrera = (Spinner) findViewById(R.id.cboCarrera);
        cboSede = (Spinner) findViewById(R.id.cboSede);

        String codInteres=cboInteresGen.getSelectedItemId()+1+"";
        String codCarrera=cboCarrera.getSelectedItemId()+1+"";
        String codSede=cboSede.getSelectedItemId()+1+"";

        //TxtBoxes
        txtNombre = findViewById(R.id.txtNombre);
        txtInfo = findViewById(R.id.txtInfo);

        btnGuardar = findViewById(R.id.btnGuardar);

        //Metodos
        cboInteresGenero();
        cboCarrera();
        cboSede();
        //leerInfo();


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarCambios(txtNombre.getText().toString(), txtInfo.getText().toString(), txtInteresGen.getText().toString(), txtCarrera.getText().toString(), txtSede.getText().toString());
            }
        });

        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstInteresGen);
        cboInteresGen.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2= new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstCarrera);
        cboCarrera.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3= new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstSede);
        cboSede.setAdapter(adapter3);
    }


    /*private void leerInfo(){
        String url = "XXX";

        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    txtNombre.setText(jsonObject.getString("nombres"));
                    txtInfo.setText(jsonObject.getString("descripcion"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(postRequest);
    }*/

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

    private void guardarCambios( /*final int codigo, */ final String nombres, final String info, final String interes, final String carrera, final String sede){
        String url = "XXX";

        StringRequest postRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    txtNombre.setText(jsonObject.getString("nombres"));
                    txtInfo.setText(jsonObject.getString("descripcion"));
                    txtInteresGen.setText(jsonObject.getString("cod_interes"));
                    txtCarrera.setText(jsonObject.getString("cod_carrera"));
                    txtSede.setText(jsonObject.getString("cod_sede"));
                    Toast.makeText(EditProfileFragment.this, "INFO: " + response, Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        })
        {
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("cod_usu", "1");
                params.put("nombres", nombres);
                params.put("descripcion", info);
                params.put("cod_interes", interes);
                params.put("cod_carrera", carrera);
                params.put("cod_sede", sede);
                return params;
            }
        }
                ;
        Volley.newRequestQueue(this).add(postRequest);
    }
}