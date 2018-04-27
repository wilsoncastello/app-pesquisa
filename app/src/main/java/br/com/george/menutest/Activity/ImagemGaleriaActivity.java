package br.com.george.menutest.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import br.com.george.menutest.Adapter.SlideGaleriaAdapter;
import br.com.george.menutest.R;
import me.relex.circleindicator.CircleIndicator;

public class ImagemGaleriaActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private ImageView btnVoltarGaleria;
    private int positionAtual;
    private ArrayList<Integer> imagensArray = new ArrayList<>();

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
            imagensArray = extra.getIntegerArrayList("endImages");
            positionAtual = extra.getInt("position");
        }

        mPager = (ViewPager) findViewById(R.id.pagerGaleria);
        mPager.setAdapter(new SlideGaleriaAdapter(ImagemGaleriaActivity.this, imagensArray));
        mPager.setCurrentItem(positionAtual);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicatorGaleria);
        indicator.setViewPager(mPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
