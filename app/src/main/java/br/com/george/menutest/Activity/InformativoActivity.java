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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.george.menutest.Adapter.CardAdapter;
import br.com.george.menutest.Model.Card;
import br.com.george.menutest.R;

public class InformativoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Card> cards;
    private ListView listCards;
    private ArrayAdapter<Card> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Informações");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String p1c1 = "Existem muitas variações disponíveis de passagens de Lorem Ipsum, mas a maioria sofreu algum tipo de " +
                        "alteração, seja por inserção de passagens com humor, ou palavras aleatórias que não parecem nem um" +
                        " pouco convincentes. Se você pretende usar uma passagem de Lorem Ipsum, precisa ter certeza de que não " +
                        "há algo embaraçoso escrito escondido no meio do texto. Todos os geradores de Lorem Ipsum na internet " +
                        "tendem a repetir pedaços predefinidos conforme necessário, fazendo deste o primeiro gerador de Lorem " +
                        "Ipsum autêntico da internet. Ele usa um dicionário com mais de 200 palavras em Latim combinado com um " +
                        "punhado de modelos de estrutura de frases para gerar um Lorem Ipsum com aparência razoável, livre de " +
                        "repetições, inserções de humor, palavras não características, etc.";
        String p2c1 = "Existem muitas variações disponíveis" +
                        " de passagens de Lorem Ipsum, mas a maioria sofreu algum tipo de alteração, seja por inserção de passagens" +
                        " com humor, ou palavras aleatórias que não parecem nem um pouco convincentes. Se você pretende usar uma" +
                        " passagem de Lorem Ipsum, precisa ter certeza de que não há algo embaraçoso escrito escondido no meio do" +
                        " texto. Todos os geradores de Lorem Ipsum na internet tendem a repetir pedaços predefinidos conforme " +
                        "necessário, fazendo deste o primeiro gerador de Lorem Ipsum autêntico da internet. Ele usa um dicionário" +
                        " com mais de 200 palavras em Latim combinado com um punhado de modelos de estrutura de frases para gerar " +
                        "um Lorem Ipsum com aparência razoável, livre de repetições, inserções de humor, palavras não características," +
                        " etc.";
        ArrayList<String> paragrafosCard1 = new ArrayList<>();
        ArrayList<String> paragrafos2Card1 = new ArrayList<>();
        paragrafosCard1.add(p1c1);
        paragrafos2Card1.add(p2c1);

        String descricaoCard1 = p1c1.substring(0,200);

        Card card1 = new Card(
                "Conhecendo o Cancro Europeu",
                descricaoCard1,
                 R.drawable.img_card_2, paragrafosCard1, paragrafos2Card1);

        String p1c2 = "Existem muitas variações disponíveis de passagens de Lorem Ipsum, mas a maioria sofreu algum tipo de " +
                "alteração, seja por inserção de passagens com humor, ou palavras aleatórias que não parecem nem um" +
                " pouco convincentes. Se você pretende usar uma passagem de Lorem Ipsum, precisa ter certeza de que não " +
                "há algo embaraçoso escrito escondido no meio do texto. Todos os geradores de Lorem Ipsum na internet " +
                "tendem a repetir pedaços predefinidos conforme necessário, fazendo deste o primeiro gerador de Lorem " +
                "Ipsum autêntico da internet. Ele usa um dicionário com mais de 200 palavras em Latim combinado com um " +
                "punhado de modelos de estrutura de frases para gerar um Lorem Ipsum com aparência razoável, livre de " +
                "repetições, inserções de humor, palavras não características, etc.";
        String p2c2 = "Existem muitas variações disponíveis" +
                " de passagens de Lorem Ipsum, mas a maioria sofreu algum tipo de alteração, seja por inserção de passagens" +
                " com humor, ou palavras aleatórias que não parecem nem um pouco convincentes. Se você pretende usar uma" +
                " passagem de Lorem Ipsum, precisa ter certeza de que não há algo embaraçoso escrito escondido no meio do" +
                " texto. Todos os geradores de Lorem Ipsum na internet tendem a repetir pedaços predefinidos conforme " +
                "necessário, fazendo deste o primeiro gerador de Lorem Ipsum autêntico da internet. Ele usa um dicionário" +
                " com mais de 200 palavras em Latim combinado com um punhado de modelos de estrutura de frases para gerar " +
                "um Lorem Ipsum com aparência razoável, livre de repetições, inserções de humor, palavras não características," +
                " etc.";
        ArrayList<String> paragrafosCard2 = new ArrayList<>();
        ArrayList<String> paragrafos2Card2 = new ArrayList<>();
        paragrafosCard2.add(p1c2);
        paragrafos2Card2.add(p2c2);

        String descricaoCard2 = p1c2.substring(0,200);

        Card card2 = new Card(
                "Prevenindo o Cancro Europeu",
                 descricaoCard2,
                 R.drawable.img_card_1, paragrafosCard2,paragrafos2Card2);

        String p1c3 = "Existem muitas variações disponíveis de passagens de Lorem Ipsum, mas a maioria sofreu algum tipo de " +
                "alteração, seja por inserção de passagens com humor, ou palavras aleatórias que não parecem nem um" +
                " pouco convincentes. Se você pretende usar uma passagem de Lorem Ipsum, precisa ter certeza de que não " +
                "há algo embaraçoso escrito escondido no meio do texto. Todos os geradores de Lorem Ipsum na internet " +
                "tendem a repetir pedaços predefinidos conforme necessário, fazendo deste o primeiro gerador de Lorem " +
                "Ipsum autêntico da internet. Ele usa um dicionário com mais de 200 palavras em Latim combinado com um " +
                "punhado de modelos de estrutura de frases para gerar um Lorem Ipsum com aparência razoável, livre de " +
                "repetições, inserções de humor, palavras não características, etc.";
        String p2c3 = "Existem muitas variações disponíveis" +
                " de passagens de Lorem Ipsum, mas a maioria sofreu algum tipo de alteração, seja por inserção de passagens" +
                " com humor, ou palavras aleatórias que não parecem nem um pouco convincentes. Se você pretende usar uma" +
                " passagem de Lorem Ipsum, precisa ter certeza de que não há algo embaraçoso escrito escondido no meio do" +
                " texto. Todos os geradores de Lorem Ipsum na internet tendem a repetir pedaços predefinidos conforme " +
                "necessário, fazendo deste o primeiro gerador de Lorem Ipsum autêntico da internet. Ele usa um dicionário" +
                " com mais de 200 palavras em Latim combinado com um punhado de modelos de estrutura de frases para gerar " +
                "um Lorem Ipsum com aparência razoável, livre de repetições, inserções de humor, palavras não características," +
                " etc.";
        ArrayList<String> paragrafosCard3 = new ArrayList<>();
        ArrayList<String> paragrafos2Card3 = new ArrayList<>();
        paragrafosCard3.add(p1c3);
        paragrafos2Card3.add(p2c3);

        String descricaoCard3 = p1c3.substring(0,200);

        Card card3 = new Card(
                "Cancro no Brasil e no Mundo",
                 descricaoCard3,
                 R.drawable.img_card_2, paragrafosCard3, paragrafos2Card3);

        String p1c4 = "Existem muitas variações disponíveis de passagens de Lorem Ipsum, mas a maioria sofreu algum tipo de " +
                "alteração, seja por inserção de passagens com humor, ou palavras aleatórias que não parecem nem um" +
                " pouco convincentes. Se você pretende usar uma passagem de Lorem Ipsum, precisa ter certeza de que não " +
                "há algo embaraçoso escrito escondido no meio do texto. Todos os geradores de Lorem Ipsum na internet " +
                "tendem a repetir pedaços predefinidos conforme necessário, fazendo deste o primeiro gerador de Lorem " +
                "Ipsum autêntico da internet. Ele usa um dicionário com mais de 200 palavras em Latim combinado com um " +
                "punhado de modelos de estrutura de frases para gerar um Lorem Ipsum com aparência razoável, livre de " +
                "repetições, inserções de humor, palavras não características, etc.";
        String p2c4 = "Existem muitas variações disponíveis" +
                " de passagens de Lorem Ipsum, mas a maioria sofreu algum tipo de alteração, seja por inserção de passagens" +
                " com humor, ou palavras aleatórias que não parecem nem um pouco convincentes. Se você pretende usar uma" +
                " passagem de Lorem Ipsum, precisa ter certeza de que não há algo embaraçoso escrito escondido no meio do" +
                " texto. Todos os geradores de Lorem Ipsum na internet tendem a repetir pedaços predefinidos conforme " +
                "necessário, fazendo deste o primeiro gerador de Lorem Ipsum autêntico da internet. Ele usa um dicionário" +
                " com mais de 200 palavras em Latim combinado com um punhado de modelos de estrutura de frases para gerar " +
                "um Lorem Ipsum com aparência razoável, livre de repetições, inserções de humor, palavras não características," +
                " etc.";
        ArrayList<String> paragrafosCard4 = new ArrayList<>();
        ArrayList<String> paragrafos2Card4 = new ArrayList<>();
        paragrafosCard4.add(p1c4);
        paragrafos2Card4.add(p2c4);

        String descricaoCard4 = p1c4.substring(0,200);

        Card card4 = new Card(
                "Normas",
                 descricaoCard4,
                 R.drawable.img_card_1, paragrafosCard4, paragrafos2Card4);

        cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        listCards = (ListView) findViewById(R.id.list_cards);
        adapter = new CardAdapter(InformativoActivity.this, cards);
        listCards.setAdapter(adapter);

        listCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                Card auxCard;

                switch (i){
                    case 0:
                        auxCard = cards.get(0);
                        intent = new Intent(InformativoActivity.this, ContentInformativoActivity.class);
                        intent.putExtra("paragrafos", auxCard.getParagrafos());
                        intent.putExtra("paragrafos2", auxCard.getParagrafos2());
                        intent.putExtra("titulo", auxCard.getTituloCard());
                        intent.putExtra("imagem", auxCard.getImgCardResource());

                        startActivity(intent);
                        break;

                    case 1:
                        auxCard = cards.get(1);
                        intent = new Intent(InformativoActivity.this, ContentInformativoActivity.class);
                        intent.putExtra("paragrafos", auxCard.getParagrafos());
                        intent.putExtra("paragrafos2", auxCard.getParagrafos2());
                        intent.putExtra("titulo", auxCard.getTituloCard());
                        intent.putExtra("imagem", auxCard.getImgCardResource());

                        startActivity(intent);
                        break;

                    case 2:
                        auxCard = cards.get(2);
                        intent = new Intent(InformativoActivity.this, ContentInformativoActivity.class);
                        intent.putExtra("paragrafos", auxCard.getParagrafos());
                        intent.putExtra("paragrafos2", auxCard.getParagrafos2());
                        intent.putExtra("titulo", auxCard.getTituloCard());
                        intent.putExtra("imagem", auxCard.getImgCardResource());

                        startActivity(intent);
                        break;

                    case 3:
                        auxCard = cards.get(3);
                        intent = new Intent(InformativoActivity.this, ContentInformativoActivity.class);
                        intent.putExtra("paragrafos", auxCard.getParagrafos());
                        intent.putExtra("paragrafos2", auxCard.getParagrafos2());
                        intent.putExtra("titulo", auxCard.getTituloCard());
                        intent.putExtra("imagem", auxCard.getImgCardResource());

                        startActivity(intent);
                        break;
                }
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
        }  else if (id == R.id.nav_galeria_imagens) {
            Intent intent = new Intent(InformativoActivity.this, GaleriaImagensActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria_videos) {
            Intent intent = new Intent(InformativoActivity.this, GaleriaVideosActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_RFID) {
            Intent intent = new Intent(InformativoActivity.this, MonitoramentoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_sobre) {
            Intent intent = new Intent(InformativoActivity.this, SobreActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_diagnostico) {
            Intent intent = new Intent(InformativoActivity.this, DiagnosticoActivity.class);
            startActivity(intent);
            finish();
       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
