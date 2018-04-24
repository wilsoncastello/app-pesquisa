package br.com.george.menutest.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import br.com.george.menutest.Adapter.SlideGaleriaAdapter;
import br.com.george.menutest.R;
import me.relex.circleindicator.CircleIndicator;

public class ImagemGaleriaActivity extends AppCompatActivity {

    private String titulo;
    private int imageResource;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private ImageView btnVoltarGaleria;
    private ArrayList<Integer> imagensEndArray = new ArrayList<>();
    private ArrayList<Integer> imagensIdsArray = new ArrayList<>();
    private int idImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_galeria);

        btnVoltarGaleria = (ImageView) findViewById(R.id.btnVoltarGaleria);
        btnVoltarGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            titulo = extra.getString("title");
            imageResource = extra.getInt("image");
            idImage = extra.getInt("id");
            imagensEndArray = extra.getIntegerArrayList("endImages");
            imagensIdsArray = extra.getIntegerArrayList("ids");
        }

        mPager = (ViewPager) findViewById(R.id.pagerGaleria);
        mPager.setAdapter(new SlideGaleriaAdapter(ImagemGaleriaActivity.this, imagensEndArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicatorGaleria);
        indicator.setViewPager(mPager);
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == imagensEndArray.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
