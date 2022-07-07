package com.example.tindertec.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tindertec.LoginActivity;
import com.example.tindertec.R;
import com.example.tindertec.models.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

public class DialogoEliminarUsuario extends AppCompatDialogFragment {

    Usuario user ;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        user = LoginActivity.userInSession;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Eliminar Usuario")
                .setMessage("Esta acción eliminará tu usuario Tindertec para siempre.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        eliminarUsuario(user.getCod_usu());
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Cierra el dialogo
            }
        });
        return builder.create();
    }

    private void eliminarUsuario(final int codUsuario){
        Usuario usu = new Usuario();
        String url ="http://192.168.1.40:8080/Usuario/Eliminar";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Toast.makeText(getContext(), "Usuario eliminado", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.getMessage());
            }
        });
        Volley.newRequestQueue(getContext()).add(postRequest);
    }
}

