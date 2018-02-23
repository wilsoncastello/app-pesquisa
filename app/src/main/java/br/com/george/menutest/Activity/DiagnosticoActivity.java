package br.com.george.menutest.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.george.menutest.R;

public class DiagnosticoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView titulo;
    private WebView descricao;
    private WebView descricaoSmall;
    private String textoDescricao;
    private ImageView btAbreCamera;
    private ImageView btAbreImagem;
    private Uri file;
    private String picturePath;

    static final int OPEN_CAMERA = 1;
    static final int OPEN_FILE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Diagnósticos");
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

        titulo = (TextView) findViewById(R.id.titulo_diagnostico);
        descricao = (WebView) findViewById(R.id.descricao_diagnostico);
        descricaoSmall = (WebView) findViewById(R.id.descricao_diagnostico_small);
        btAbreCamera = (ImageView) findViewById(R.id.bt_abrir_camera);
        btAbreImagem = (ImageView) findViewById(R.id.bt_abrir_imagem);

        titulo.setText("Porque nós o usamos?");
        if(descricao != null){
            descricao.loadData("<p style=\"color:#616161; text-align: justify; font-size:18px\">" + textoDescricao + "</p>", "text/html", "UTF-8");
        } else{
            descricaoSmall.loadData("<p style=\"color:#616161; text-align: justify; font-size:14px\">" + textoDescricao + "</p>", "text/html", "UTF-8");
        }


        btAbreCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture(view);
            }
        });

        btAbreImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, OPEN_FILE);
            }
        });
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = FileProvider.getUriForFile(DiagnosticoActivity.this, getApplicationContext().getPackageName() + ".my.package.name.provider", getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, OPEN_CAMERA);
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "ImageCameraAppIFSC");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent;

        if (resultCode != RESULT_CANCELED) {
            if (requestCode == OPEN_CAMERA) {
                intent = new Intent(DiagnosticoActivity.this, ImageCameraActivity.class);
                intent.putExtra("image", file);

                startActivity(intent);
            } else if (requestCode == OPEN_FILE) {
                Uri uri = data.getData();

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = DiagnosticoActivity.this.getContentResolver().query(uri, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
//                Bitmap bitmapFile = BitmapFactory.decodeFile(picturePath);

                intent = new Intent(DiagnosticoActivity.this, ImageFileActivity.class);
                intent.putExtra("endereco_image", picturePath);

                startActivity(intent);
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            Intent intent = new Intent(DiagnosticoActivity.this, InformativoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria_imagens) {
            Intent intent = new Intent(DiagnosticoActivity.this, GaleriaImagensActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria_videos) {
            Intent intent = new Intent(DiagnosticoActivity.this, GaleriaVideosActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_RFID) {
            Intent intent = new Intent(DiagnosticoActivity.this, MonitoramentoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_sobre) {
            Intent intent = new Intent(DiagnosticoActivity.this, SobreActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_diagnostico) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
