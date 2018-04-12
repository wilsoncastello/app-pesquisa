package br.com.george.menutest.Activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.george.menutest.Adapter.SlideEtiquetaAdapter;
import br.com.george.menutest.R;
import me.relex.circleindicator.CircleIndicator;

public class ImagemEtiquetaActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private ArrayList<String> imagensArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_etiqueta);

        setTitle("Preview");

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            imagensArray = extra.getStringArrayList("image_etiqueta");
        }

        mPager = (ViewPager) findViewById(R.id.pagerEtiqueta);
        mPager.setAdapter(new SlideEtiquetaAdapter(ImagemEtiquetaActivity.this, imagensArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicatorEtiqueta);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == imagensArray.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
    }
}