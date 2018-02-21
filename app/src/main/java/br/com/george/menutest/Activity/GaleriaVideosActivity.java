package br.com.george.menutest.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.george.menutest.Adapter.VideoAdapter;
import br.com.george.menutest.Model.Video;
import br.com.george.menutest.R;

public class GaleriaVideosActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Video> videos;
    private ListView listVideos;
    private ArrayAdapter<Video> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Galeria de Vídeos");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final Video video1 = new Video("Cursos online, caixas misteriosas e polêmicas, app da carteira de trabalho e mais - Hoje no TecMundo", R.drawable.img_video, "https://www.youtube.com/watch?v=pplnFBm5ROk");
        final Video video2 = new Video("Moto Snap Gamepad: transforme seu Moto Z em uma espécie de Switch", R.drawable.img_video2, "https://www.youtube.com/watch?v=pplnFBm5ROk");
        final Video video3 = new Video("Os 10 melhores recursos do iPhone X", R.drawable.img_video3, "https://www.youtube.com/watch?v=pplnFBm5ROk");
        final Video video4 = new Video("ELE ESTÁ AQUI! - Galaxy Note 8", R.drawable.img_video4, "https://www.youtube.com/watch?v=pplnFBm5ROk");
        final Video video5 = new Video("As JOGADAS mais HUMILHANTES do Futsal - VINES #1", R.drawable.img_video6, "https://www.youtube.com/watch?v=pplnFBm5ROk");
        final Video video6 = new Video("iPHONE vs. ANDROID - Qual é o melhor?", R.drawable.img_video5, "https://www.youtube.com/watch?v=pplnFBm5ROk");

        videos = new ArrayList<>();
        videos.add(video1);
        videos.add(video2);
        videos.add(video3);
        videos.add(video4);
        videos.add(video5);
        videos.add(video6);

        listVideos = (ListView) findViewById(R.id.list_videos);
        adapter = new VideoAdapter(GaleriaVideosActivity.this, videos);
        listVideos.setAdapter(adapter);

        listVideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri uri;
                Intent intent;

                switch (i) {
                    case 0:
                        uri = Uri.parse(video1.getLinkVideo());
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;

                    case 1:
                        uri = Uri.parse(video2.getLinkVideo());
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;

                    case 2:
                        uri = Uri.parse(video3.getLinkVideo());
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;

                    case 3:
                        uri = Uri.parse(video4.getLinkVideo());
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;

                    case 4:
                        uri = Uri.parse(video5.getLinkVideo());
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;

                    case 5:
                        uri = Uri.parse(video6.getLinkVideo());
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            Intent intent = new Intent(GaleriaVideosActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria_imagens) {
            Intent intent = new Intent(GaleriaVideosActivity.this, GaleriaImagensActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria_videos) {
        } else if (id == R.id.nav_RFID) {
            Intent intent = new Intent(GaleriaVideosActivity.this, MonitoramentoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_sobre) {
            Intent intent = new Intent(GaleriaVideosActivity.this, SobreActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_diagnostico) {
            Intent intent = new Intent(GaleriaVideosActivity.this, DiagnosticoActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
