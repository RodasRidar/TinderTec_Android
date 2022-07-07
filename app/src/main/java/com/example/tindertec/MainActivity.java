package com.example.tindertec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tindertec.models.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toogle ;
    private DrawerLayout dl ;
    private NavigationView nv;

    private Usuario usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl=findViewById(R.id.drawerLayout);
        toogle= (ActionBarDrawerToggle) new ActionBarDrawerToggle(this,dl,R.string.open_drawer,R.string.close_drawer);
        dl.addDrawerListener(toogle);
        nv =findViewById(R.id.nav_view);
        toogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //String nombreUsu=getIntent().getStringExtra("Nombre");

        usu=LoginActivity.userInSession;
      //  fotoperfil.setImageURI(usu.getFoto1());

        //Toast toast= Toast.makeText(this,"Bienvenido"+nombreUsu,Toast.LENGTH_LONG);
        Toast toast= Toast.makeText(this,"Bienvenido "+usu.getNombres(),Toast.LENGTH_LONG);
        toast.show();



        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_home:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container_view, homeFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;

                    case R.id.nav_profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container_view, profileFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
/*
                    case R.id.nav_matchs:
                        MatchFragment matchFragment = new MatchFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container_view, matchFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
*/
                    case R.id.nav_likes:
                        Fragment_like likesFragment = new Fragment_like();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container_view, likesFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();

                        break;

                    case R.id.nav_subs:
                        SubsFragment subsragment = new SubsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container_view, subsragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.nav_close_session:
                        finishAffinity();
                        break;
                }

                dl.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(toogle.onOptionsItemSelected(item)){
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}