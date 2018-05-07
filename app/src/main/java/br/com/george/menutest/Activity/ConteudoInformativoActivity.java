package br.com.george.menutest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.george.menutest.R;

public class ConteudoInformativoActivity extends AppCompatActivity {

    private TextView paragrafoUm;
    private TextView paragrafoDois;
    private ImageView imageHeader;
    private ImageView imageUm;
    private ImageView imageDois;
    private ImageView imageTres;
    private TextView tituloHeader;
    private ArrayList<String> paragrafos;
    private ArrayList<String> paragrafos2;
    private String titulo;
    private int imagem;
    private ArrayList<Integer> imagensEnd;
    private Integer[] imagemAtual = new Integer[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_informativo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageHeader = (ImageView) findViewById(R.id.img_header_info);
        tituloHeader = (TextView) findViewById(R.id.titulo_info);
        paragrafoDois = (TextView) findViewById(R.id.paragrafoDois);
        paragrafoUm = (TextView) findViewById(R.id.paragrafoUm);
        imageUm = (ImageView) findViewById(R.id.image_info_um);
        imageDois = (ImageView) findViewById(R.id.image_info_dois);
        imageTres = (ImageView) findViewById(R.id.image_info_tres);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            paragrafos = extra.getStringArrayList("paragrafos");
            paragrafos2 = extra.getStringArrayList("paragrafos2");
            titulo = extra.getString("titulo");
            imagem = extra.getInt("imagem");
            imagensEnd = extra.getIntegerArrayList("imagens");
        }

        setTitle(titulo);
        tituloHeader.setText(titulo);

        imageHeader.setImageResource(imagem);

        int cont = 0;
        for (int endImagemAtual: imagensEnd){
            imagemAtual[cont] = endImagemAtual;
            cont++;
        }

        imageUm.setImageResource(imagemAtual[0]);
        imageDois.setImageResource(imagemAtual[1]);
        imageTres.setImageResource(imagemAtual[2]);

        for(String p: paragrafos){
            paragrafoUm.setText(p);
        }
        for(String p: paragrafos2){
            paragrafoDois.setText(p);
        }


        imageUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConteudoInformativoActivity.this, ImagemInformativoActivity.class);
                intent.putExtra("endImages", imagensEnd);
                intent.putExtra("position", 0);
                startActivity(intent);
            }
        });

        imageDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConteudoInformativoActivity.this, ImagemInformativoActivity.class);
                intent.putExtra("endImages", imagensEnd);
                intent.putExtra("position", 1);
                startActivity(intent);
            }
        });

        imageTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConteudoInformativoActivity.this, ImagemInformativoActivity.class);
                intent.putExtra("endImages", imagensEnd);
                intent.putExtra("position", 2);
                startActivity(intent);
            }
        });

    }

}
