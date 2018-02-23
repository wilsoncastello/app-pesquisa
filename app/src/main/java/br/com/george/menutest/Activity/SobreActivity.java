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
import android.webkit.WebView;

import br.com.george.menutest.R;

public class SobreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private WebView webView;
    private WebView webViewSmall;
    private String texto = "Existem muitas variações disponíveis de passagens de Lorem Ipsum, mas a maioria sofreu algum " +
            "tipo de alteração, seja por inserção de passagens com humor, ou palavras aleatórias que não parecem nem um pouco " +
            "convincentes. Se você pretende usar uma passagem de Lorem Ipsum, precisa ter certeza de que não há algo embaraçoso " +
            "escrito escondido no meio do texto. Todos os geradores de Lorem Ipsum na internet tendem a repetir pedaços " +
            "predefinidos conforme necessário, fazendo deste o primeiro gerador de Lorem Ipsum autêntico da internet. Ele usa um " +
            "dicionário com mais de 200 palavras em Latim combinado com um punhado de modelos de estrutura de frases para gerar " +
            "um Lorem Ipsum com aparência razoável, livre de repetições, inserções de humor, palavras não características, etc." + "<br>" +

            "Ao contrário do que se acredita, Lorem Ipsum não é simplesmente um texto randômico. Com mais de 2000 anos, " +
            "suas raízes podem ser encontradas em uma obra de literatura latina clássica datada de 45 AC. Richard McClintock, um" +
            " professor de latim do Hampden-Sydney College na Virginia, pesquisou uma das mais obscuras palavras em latim, " +
            "consectetur, oriunda de uma passagem de Lorem Ipsum, e, procurando por entre citações da palavra na literatura " +
            "clássica, descobriu a sua indubitável origem. Lorem Ipsum vem das seções 1.10.32 e 1.10.33 do \"de Finibus Bonorum " +
            "et Malorum\" (Os Extremos do Bem e do Mal), de Cícero, escrito em 45 AC. Este livro é um tratado de teoria da ética " +
            "muito popular na época da Renascença. A primeira linha de Lorem Ipsum, \"Lorem Ipsum dolor sit amet...\" vem de uma " +
            "linha na seção 1.10.32.";

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

        webView = (WebView) findViewById(R.id.web_view_sobre);
        webViewSmall = (WebView) findViewById(R.id.web_view_sobre_small);

        if(webView != null){
            webView.loadData("<p style=\"color:#616161; text-align: justify; font-size:18px\">" + texto + "</p>", "text/html", "utf-8");
        } else {
            webViewSmall.loadData("<p style=\"color:#616161; text-align: justify; font-size:14px\">" + texto + "</p>", "text/html", "utf-8");
        }

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
