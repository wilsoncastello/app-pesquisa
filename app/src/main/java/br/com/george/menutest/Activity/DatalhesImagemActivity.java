package br.com.george.menutest.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.george.menutest.R;

public class DatalhesImagemActivity extends AppCompatActivity {

    private String titulo;
    private int imageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_image);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            titulo = extra.getString("title");
            imageResource = extra.getInt("image");
        }

        this.getSupportActionBar().setTitle(titulo);


        TextView titleTextView = (TextView) findViewById(R.id.titleDetails);
        titleTextView.setText(titulo);

        ImageView imageView = (ImageView) findViewById(R.id.imageDetails);
        imageView.setImageResource(imageResource);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
