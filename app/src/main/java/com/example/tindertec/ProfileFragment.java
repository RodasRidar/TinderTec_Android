package com.example.tindertec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tindertec.dialogs.DialogoEliminarUsuario;
import com.example.tindertec.models.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView nombreyedad;
    private TextView carreraUsr;
    private TextView infoUsr;
    private CircleImageView profilePic;

    private String mParam1;
    private String mParam2;
    FloatingActionButton btnEdit;


    Button btnEliminar;
    Button btnGaleria;
    Usuario user ;
    public ProfileFragment() {

    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = LoginActivity.userInSession;

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ConstraintLayout cl = view.findViewById(R.id.ProfileFragment);

        nombreyedad = view.findViewById(R.id.txtNombreYedad);
        carreraUsr = view.findViewById(R.id.txtCarrera);
        infoUsr = view.findViewById(R.id.txtInfo);
        profilePic = view.findViewById(R.id.circleImageView);

        try {
            String nombreYEdad=user.getNombres()+", "+ obtenerEdad(user.getFecha_naci());
            nombreyedad.setText(nombreYEdad);

            String InfoUsr = user.getDescripcion();
            infoUsr.setText(InfoUsr);

            Picasso.get().load(user.getFoto1()).into(profilePic);

            JSONObject jsonObject = new JSONObject(user.getNom_carrera());
            String CarreraUsr = jsonObject.getJSONObject("carrera").getString("des_carrera");
            carreraUsr.setText(CarreraUsr);

        } catch (ParseException | JSONException e) {
            e.printStackTrace();
        }

        btnEdit = (FloatingActionButton) view.findViewById(R.id.FloatingActionButton);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileFragment.class);
                getActivity().startActivity(intent);
            }
        });

        btnGaleria = (Button) view.findViewById(R.id.btnGaleria);
        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GalleryFragment.class);
                getActivity().startActivity(intent);
            }
        });


        btnEliminar= (Button) view.findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                /*int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getContext(), "Se eliminara el usuario" ,Toast.LENGTH_LONG);
                toast.show(); */
                validarDelete();
            }
        });
        return view;
    }

    public void validarDelete(){
        DialogoEliminarUsuario dialogoEliminarUsuario = new DialogoEliminarUsuario();
        dialogoEliminarUsuario.show(getActivity().getSupportFragmentManager(), "Validar");
    }


    public String obtenerEdad(String fecna) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
        // fecna= repoUsua.findById(1).get().getFecha_naci();
        Date fechaNacimiento = sdf.parse(fecna);
        Date secondDate = sdf.parse("2022-01-01");

        long diff = (secondDate.getTime() - fechaNacimiento.getTime()) / 365;

        TimeUnit time = TimeUnit.DAYS;
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
        String age;
        age = diffrence + "";

        return age;
    }

}