package br.com.george.menutest.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import br.com.george.menutest.Helper.Permissao;
import br.com.george.menutest.R;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView buttonInformativo;
    private ImageView buttonDiagnostico;
    private ImageView buttonMonitoramento;
    private File pastaImagesTemp;
    private String[] permissoesNecessarias = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Início");
        setSupportActionBar(toolbar);
        Permissao.validaPermissoes(1,PrincipalActivity.this, permissoesNecessarias);

        pastaImagesTemp = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ImageCameraAppIFSC");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        buttonInformativo = (ImageView) findViewById(R.id.bt_informativo);
        buttonDiagnostico = (ImageView) findViewById(R.id.bt_diagnostico);
        buttonMonitoramento = (ImageView) findViewById(R.id.bt_monitoramento);

        buttonInformativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, InformativoActivity.class);
                startActivity(intent);
            }
        });

        buttonDiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, DiagnosticoActivity.class);
                startActivity(intent);
            }
        });

        buttonMonitoramento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, MonitoramentoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            Intent intent = new Intent(PrincipalActivity.this, InformativoActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_galeria_imagens) {
            Intent intent = new Intent(PrincipalActivity.this, GaleriaImagensActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_galeria_videos) {
            Intent intent = new Intent(PrincipalActivity.this, GaleriaVideosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_RFID) {
            Intent intent = new Intent(PrincipalActivity.this, MonitoramentoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sobre) {
            Intent intent = new Intent(PrincipalActivity.this, SobreActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_diagnostico) {
            Intent intent = new Intent(PrincipalActivity.this, DiagnosticoActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int resultado: grantResults){
            if(resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalActivity.this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Negando algumas permissões você não poderá utilizar o app.");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        deleteRecursive(pastaImagesTemp);
    }
}
