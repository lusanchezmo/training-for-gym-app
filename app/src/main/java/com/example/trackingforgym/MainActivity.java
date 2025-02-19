package com.example.trackingforgym;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.trackingforgym.data.Session;
import com.example.trackingforgym.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        confRed();
        new Session();
        /*new Session();
        if (!Session.getSession()) {
            definirSession();
            System.out.println("paso");
        }*/

        setSupportActionBar(findViewById(R.id.toolbar));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_menu, R.id.nav_stats,R.id.nav_historic)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        setElements();
        setInfNavButtons();
    }

    private void setInfNavButtons(){
        ImageView btnHomeBar = (ImageView) findViewById(R.id.btnHomeBar);
        ImageView btnMenuBar = (ImageView) findViewById(R.id.btnMenuBar);
        ImageView btnStatsBar = (ImageView) findViewById(R.id.btnStatsBar);
        ImageView btnHistoricBar =(ImageView) findViewById(R.id.btnHistoryBar);
        btnHomeBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("jome");
                navController.navigate(R.id.nav_home);
            }
        });
        btnMenuBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("ad");
                navController.navigate(R.id.nav_menu);
            }
        });
        btnStatsBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("joasd");
                navController.navigate(R.id.nav_stats);
            }
        });
        btnHistoricBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("joasd");
                navController.navigate(R.id.nav_historic);

            }
        });
    }

    private void confRed(){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        if (!Session.getSession()) {
            definirSession();
            System.out.println("paso");
        }
        System.out.println("de vuelta");
    }

    public void setElements(){

    }

    public void definirSession(){
        System.out.println("abriendo login");
        Intent abrirLogin = new Intent(MainActivity.this, login.class);
        startActivity(abrirLogin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}