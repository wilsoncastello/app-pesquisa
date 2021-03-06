package br.com.george.menutest.Activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import br.com.george.menutest.Adapter.SlideEtiquetaAdapter;
import br.com.george.menutest.Model.ImagemBD;
import br.com.george.menutest.R;
import me.relex.circleindicator.CircleIndicator;

public class ImagemEtiquetaActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private ImageView btnVoltarEtiqueta;
    private ArrayList<String> imagensArray = new ArrayList<>();
    private ArrayList<String> datasArray = new ArrayList<>();
    private ArrayList<String> descricaoArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_etiqueta);

        setTitle("Imagens");

        btnVoltarEtiqueta = (ImageView) findViewById(R.id.btnVoltar);
        btnVoltarEtiqueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            imagensArray = extra.getStringArrayList("image_etiqueta");
            datasArray = extra.getStringArrayList("data_etiqueta");
            descricaoArray = extra.getStringArrayList("descricao_etiqueta");
        }

        ImagemBD imagemBD;
        final ArrayList<ImagemBD> imagens = new ArrayList<>();

        int i = 0;
        String[] vetorDatas = new String[datasArray.size()];
        for(String data: datasArray){
            vetorDatas[i] = data;
            i++;
        }

        int j = 0;
        String[] vetorDescricao = new String[descricaoArray.size()];
        for(String descricao: descricaoArray){
            vetorDescricao[j] = descricao;
            j++;
        }

        int k = 0;
        for(String img: imagensArray){
            imagemBD = new ImagemBD();
            imagemBD.setImagem(img);
            imagemBD.setData(vetorDatas[k]);
            imagemBD.setDescricao(vetorDescricao[k]);
            imagens.add(imagemBD);
            k++;
        }

        mPager = (ViewPager) findViewById(R.id.pagerEtiqueta);
        mPager.setAdapter(new SlideEtiquetaAdapter(ImagemEtiquetaActivity.this, imagens));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicatorEtiqueta);
        indicator.setViewPager(mPager);
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == imagens.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPager.setAdapter(null);
    }
}

