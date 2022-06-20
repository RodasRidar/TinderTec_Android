package com.example.tindertec;

import android.graphics.Picture;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tindertec.adapter.PictureAdapterRecyclerView;
import com.example.tindertec.models.Usuario;

import java.util.ArrayList;
import java.util.List;


public class Fragment_like extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerUsuario;
    List<Usuario> listaUsuario;
    public Fragment_like() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista =inflater.inflate(R.layout.fragment_like, container, false);

        recyclerUsuario=vista.findViewById(R.id.recycleViewListsUser);
        recyclerUsuario.setLayoutManager(new LinearLayoutManager(getContext()));
        listaUsuario=llenarLista();
        PictureAdapterRecyclerView adapter = new PictureAdapterRecyclerView(listaUsuario);
        recyclerUsuario.setAdapter(adapter);
        return vista;
    }

    private List<Usuario> llenarLista() {
        List<Usuario> listaUsuario=new ArrayList<>();
        listaUsuario.add(new Usuario("Gabriela Goyburo","https://images.pexels.com/photos/2119370/pexels-photo-2119370.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Computacion e Informatica"));
        listaUsuario.add(new Usuario("Daniela Barraza","https://images.pexels.com/photos/2739750/pexels-photo-2739750.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1","Publicidad"));
    return listaUsuario;
    }
}