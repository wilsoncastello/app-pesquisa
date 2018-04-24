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

public class ConteudoInformativoActivity extends AppCompatActivity {

    private WebView webViewParagrafo;
    private WebView webViewParagrafoSmall;
    private WebView webViewParagrafo2;
    private WebView webViewParagrafo2Small;
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
        setContentView(R.layout.activity_content_informativo);

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
        String codeWebSmall = "";

        for (int i = 0; i <= (qtdParagrafos - 1); i++) {
            codeWeb += "<p style=\"color:#616161; text-align: justify; margin:0px; font-size:18px\">" + paragrafos.get(i) + "</p>";
        }
        for (int i = 0; i <= (qtdParagrafos - 1); i++) {
            codeWebSmall += "<p style=\"color:#616161; text-align: justify; margin:0px; font-size:14px\">" + paragrafos.get(i) + "</p>";
        }

        webViewParagrafo = (WebView) findViewById(R.id.web_view_paragrafo);
        webViewParagrafoSmall = (WebView) findViewById(R.id.web_view_paragrafo_small);

        if (webViewParagrafo != null){
            webViewParagrafo.loadData(
                    codeWeb,
                    "text/html",
                    "UTF-8"
            );
        } else {
            webViewParagrafoSmall.loadData(
                    codeWebSmall,
                    "text/html",
                    "UTF-8"
            );
        }

        linkInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(ConteudoInformativoActivity.this, GaleriaImagensActivity.class);
                startActivity(intent);
            }
        });

        codeWeb = "";
        codeWebSmall = "";

        for (int i = 0; i <= (qtdParagrafos2 - 1); i++) {
            codeWeb += "<p style=\"color:#616161; text-align: justify; margin:0px; font-size:18px\">" + paragrafos2.get(i) + "</p>";
        }
        for (int i = 0; i <= (qtdParagrafos2 - 1); i++) {
            codeWebSmall += "<p style=\"color:#616161; text-align: justify; margin:0px; font-size:14px\">" + paragrafos2.get(i) + "</p>";
        }
        webViewParagrafo2 = (WebView) findViewById(R.id.web_view_paragrafo_2);
        webViewParagrafo2Small = (WebView) findViewById(R.id.web_view_paragrafo_2_small);

        if (webViewParagrafo2 != null){
            webViewParagrafo2.loadData(
                    codeWeb,
                    "text/html",
                    "UTF-8"
            );
        } else {
            webViewParagrafo2Small.loadData(
                    codeWebSmall,
                    "text/html",
                    "UTF-8"
            );
        }
    }

}
