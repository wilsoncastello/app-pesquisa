package br.com.george.menutest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.george.menutest.R;

public class SobreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sobre");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            Intent intent = new Intent(SobreActivity.this, InformativoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria_imagens) {
            Intent intent = new Intent(SobreActivity.this, GaleriaImagensActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria_videos) {
            Intent intent = new Intent(SobreActivity.this, GaleriaVideosActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_RFID) {
            Intent intent = new Intent(SobreActivity.this, MonitoramentoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_sobre) {
        } else if (id == R.id.nav_diagnostico) {
            Intent intent = new Intent(SobreActivity.this, DiagnosticoActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}