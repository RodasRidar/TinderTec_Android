package com.example.tindertec;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tindertec.models.Usuario;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText loginCorreo;
    private  EditText loginContra;
    public static  Usuario userInSession = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginCorreo= findViewById(R.id.loginCorreo);
        loginContra= findViewById(R.id.loginContra);
        userInSession=new Usuario();
        buttonLogin=(Button)findViewById(R.id.buttonLogin);

    }
    //
    public void goCreateAccount(View view){
        Intent intent = new Intent(this , RegistroActivity.class);
        startActivity(intent);
    }

    private void init(Usuario user){
        Intent intent = new Intent( this, MainActivity.class);

        //intent.putExtra("Nombre",user.getNombres());
        startActivity(intent);
        userInSession=user;


    }

    public void goLogin(View view){
        Usuario usu=new Usuario();
        String url="http://192.168.3.26:8080/Seguridad/Login";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    usu.setCod_usu(Integer.parseInt(jsonObject.getString("cod_usu")));
                    usu.setNombres(jsonObject.getString("nombres"));
                    usu.setFoto1(jsonObject.getString("foto1"));
                    usu.setFoto2(jsonObject.getString("foto2"));
                    usu.setFoto3(jsonObject.getString("foto3"));
                    usu.setFoto4(jsonObject.getString("foto4"));
                    usu.setFoto5(jsonObject.getString("foto5"));
                    usu.setFecha_naci(jsonObject.getString("fecha_naci"));
                    usu.setDescripcion(jsonObject.getString("descripcion"));
                    usu.setCod_carrera(Integer.parseInt(jsonObject.getString("cod_carrera")));
                    usu.setNom_carrera(jsonObject.getString("carrera"));
                    usu.setCod_sede(Integer.parseInt(jsonObject.getString("cod_sede")));
                    usu.setNom_sede(jsonObject.getString("sede"));
                    usu.setCod_interes(Integer.parseInt(jsonObject.getString("cod_interes")));
                    usu.setNom_interes(jsonObject.getString("interesGenero"));
                    usu.setCod_genero(Integer.parseInt(jsonObject.getString("cod_genero")));
                    if(usu.getCod_usu()!=0){
                        init(usu);
                    }
                    else{
                        Toast toast= Toast.makeText(LoginActivity.this,"No existe usuario",Toast.LENGTH_SHORT);
                        toast.show();
                    }
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
            protected Map<String,String>getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("email" ,loginCorreo.getText().toString());
                params.put("clave" ,loginContra.getText().toString());
                //Log.e("PRINT", loginContra.getText().toString() +loginCorreo.getText().toString() );
                return params;
            }
        }
                ;
        Volley.newRequestQueue(this).add(postRequest);
        ;


    }
    /*
    private Usuario Login(){
        String url="http://192.168.3.26:8080/Seguridad/Login";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("Objeto",jsonObject.toString());
                    usu.setCod_usu(Integer.parseInt(jsonObject.getString("cod_usu")));
                    usu.setNombres(jsonObject.getString("nombres"));
                    usu.setFoto1(jsonObject.getString("foto1"));
                    usu.setFoto2(jsonObject.getString("foto2"));
                    usu.setFoto3(jsonObject.getString("foto3"));
                    usu.setFoto4(jsonObject.getString("foto4"));
                    usu.setFoto5(jsonObject.getString("foto5"));
                    usu.setFecha_naci(jsonObject.getString("fecha_naci"));
                    usu.setDescripcion(jsonObject.getString("descripcion"));
                    usu.setCod_carrera(Integer.parseInt(jsonObject.getString("cod_carrera")));
                    usu.setNom_carrera(jsonObject.getString("carrera"));
                    usu.setCod_sede(Integer.parseInt(jsonObject.getString("cod_sede")));
                    usu.setNom_sede(jsonObject.getString("sede"));
                    usu.setCod_interes(Integer.parseInt(jsonObject.getString("cod_interes")));
                    usu.setNom_interes(jsonObject.getString("interesGenero"));
                    usu.setCod_genero(Integer.parseInt(jsonObject.getString("cod_genero")));
                    Log.e("UserNombre",usu.getNombres());
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
            protected Map<String,String>getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("email" ,loginCorreo.getText().toString());
                params.put("clave" ,loginContra.getText().toString());
                //Log.e("PRINT", loginContra.getText().toString() +loginCorreo.getText().toString() );
                return params;
            }
        }
                ;
        Volley.newRequestQueue(this).add(postRequest);
        return usu;
    }*/

}