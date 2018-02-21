package br.com.george.menutest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.george.menutest.R;

public class InformativoActivity extends AppCompatActivity {

    private WebView webViewParagrafo;
    private WebView webViewParagrafo2;
    private ImageView imageHeader;
    private TextView tituloHeader;
    private TextView linkInfo;
    private ArrayList<String> paragrafos;
    private ArrayList<String> paragrafos2;
    private String titulo;
    private int imagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informativo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageHeader = (ImageView) findViewById(R.id.img_header_info);
        linkInfo = (TextView) findViewById(R.id.link_informativo);
        tituloHeader = (TextView) findViewById(R.id.titulo_info);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            paragrafos = extra.getStringArrayList("paragrafos");
            paragrafos2 = extra.getStringArrayList("paragrafos2");
            titulo = extra.getString("titulo");
            imagem = extra.getInt("imagem");
        }

        tituloHeader.setText(titulo);

        imageHeader.setImageResource(imagem);

        int qtdParagrafos = paragrafos.size();
        int qtdParagrafos2 = paragrafos.size();

        String codeWeb = "";
        for (int i = 0; i <= (qtdParagrafos - 1); i++) {
            codeWeb += "<p style=\"color:#616161; text-align: justify; margin:0px; font-size:18px\">" + paragrafos.get(i) + "</p>";
        }
        webViewParagrafo = (WebView) findViewById(R.id.web_view_paragrafo);
        webViewParagrafo.loadData(
                 codeWeb,
                "text/html",
                "UTF-8"
        );

        linkInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(InformativoActivity.this, GaleriaImagensActivity.class);
                startActivity(intent);
            }
        });

        codeWeb = "";
        for (int i = 0; i <= (qtdParagrafos2 - 1); i++) {
            codeWeb += "<p style=\"color:#616161; text-align: justify; margin:0px; font-size:18px\">" + paragrafos2.get(i) + "</p>";
        }
        webViewParagrafo2 = (WebView) findViewById(R.id.web_view_paragrafo_2);
        webViewParagrafo2.loadData(
                codeWeb,
                "text/html",
                "UTF-8"
        );
    }

}
