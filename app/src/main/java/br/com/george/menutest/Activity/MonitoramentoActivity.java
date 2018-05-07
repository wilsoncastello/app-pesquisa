package br.com.george.menutest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.george.menutest.R;

public class MonitoramentoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TextView titulo;
    private String textoDescricao;
    private ImageView btIniciarLeitor;
    private ImageView btVerificarBanco;

    private TextView textMonitoramento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoramento);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Monitoramento");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        textoDescricao = "É um fato conhecido de todos que um leitor se distrairá com o conteúdo de texto legível de uma página " +
                "quando estiver examinando sua diagramação. A vantagem de usar Lorem Ipsum é que ele tem uma distribuição normal de " +
                "letras, ao contrário de \"Conteúdo aqui, conteúdo aqui\", fazendo com que ele tenha uma aparência similar a de um" +
                " texto legível. Muitos softwares de publicação e editores de páginas na internet agora usam Lorem Ipsum como " +
                "texto-modelo padrão, e uma rápida busca por 'lorem ipsum' mostra vários websites ainda em sua fase de construção. " +
                "Várias versões novas surgiram ao longo dos anos, eventualmente por acidente, e às vezes de propósito (injetando " +
                "humor, e coisas do gênero).";

        titulo = (TextView) findViewById(R.id.titulo_monitoramento);
        btIniciarLeitor = (ImageView) findViewById(R.id.bt_iniciar_leitor);
        btVerificarBanco = (ImageView) findViewById(R.id.bt_verificar_banco);

        textMonitoramento = (TextView) findViewById(R.id.text_monitoramento);

        titulo.setText("Porque nós o usamos?");
        textMonitoramento.setText(textoDescricao);

        btIniciarLeitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonitoramentoActivity.this, BluetoothActivity.class);
                startActivity(intent);
            }
        });

        btVerificarBanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonitoramentoActivity.this, DatabaseActivity.class);
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
            Intent intent = new Intent(MonitoramentoActivity.this, InformativoActivity.class);
            startActivity(intent);
            finish();
        }  else if (id == R.id.nav_galeria_imagens) {
            Intent intent = new Intent(MonitoramentoActivity.this, GaleriaImagensActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria_videos) {
            Intent intent = new Intent(MonitoramentoActivity.this, GaleriaVideosActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_RFID) {
        } else if (id == R.id.nav_sobre) {
            Intent intent = new Intent(MonitoramentoActivity.this, SobreActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_diagnostico) {
            Intent intent = new Intent(MonitoramentoActivity.this, DiagnosticoActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
