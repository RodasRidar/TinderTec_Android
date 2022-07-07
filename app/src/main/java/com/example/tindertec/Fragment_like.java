package com.example.tindertec;

import android.graphics.Picture;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tindertec.adapter.PictureAdapterRecyclerView;
import com.example.tindertec.models.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Fragment_like extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    // DECLARO VARIABLES
    private RecyclerView recyclerUsuario;
    private ArrayList<Usuario> listaUsuario;
    private RequestQueue rq;
    private PictureAdapterRecyclerView adaptadorUsuario;

    public Fragment_like() {

    }

    public static Fragment_like newInstance(String param1, String param2) {
        Fragment_like fragment = new Fragment_like();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.e("Seguimineto", "onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listaUsuario= new ArrayList<>();
        Log.e("Seguimineto", "onCreateView");
        View vista = inflater.inflate(R.layout.fragment_like, container, false);
        rq = Volley.newRequestQueue(vista.getContext());

        Log.e("Seguimineto", "Paso Cargar Persona");
        recyclerUsuario = vista.findViewById(R.id.recycleViewListsUser);
        recyclerUsuario.setLayoutManager(new LinearLayoutManager(getContext()));
        listaUsuario=cargarPersona(listaUsuario);
        adaptadorUsuario = new PictureAdapterRecyclerView(listaUsuario);
        recyclerUsuario.setAdapter(adaptadorUsuario);

        return vista;
    }

    private ArrayList<Usuario> cargarPersona(ArrayList<Usuario> lst) {
        ArrayList<Usuario> lista=lst;
        String URL = "http://192.168.3.26:8080/MeGustas/Lista";
        StringRequest requerimiento = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            public void onResponse(String response) {
                try {

                    Log.e("Seguimineto","Inicio del try");
                    Usuario usuario=new Usuario();
                    JSONArray jsonObject = new JSONArray(response);
                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject obj = jsonObject.getJSONObject(i);
                        String nombres = obj.getString("nombres");
                        String f1 = obj.getString("foto1");
                       // String carrera = obj.getString("des_carrera");
                        //usuario.setNom_carrera(carrera);
                        usuario.setFoto1(f1);
                        usuario.setNombres(nombres);
                        lista.add(usuario);
                        //Log.e("NOmbre:",nombres);
                        //Log.e("Carrera:",carrera);
                    }
                    //recuperarLista(lista);
                    Log.e("listaUsuarioMETODO:","Se cargo la lista");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Seguimineto","Catch");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        rq.add(requerimiento);
        Log.e("Seguimineto","RQ.ADD");
        return lista;

    }
    private void recuperarLista(ArrayList<Usuario> lst){
        Log.e("recuperarLista:","Cargando lista");
        listaUsuario=lst;
        Log.e("lst:",listaUsuario.get(0).getFoto1());

    }
}