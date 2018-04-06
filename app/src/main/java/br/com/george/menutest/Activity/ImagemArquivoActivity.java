package br.com.george.menutest.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.george.menutest.R;

public class ImagemArquivoActivity extends AppCompatActivity {

    private ImageView imageFile;
    private Button btCancelar;
    private Button btSalvar;
    private Bitmap bitmap;
    private String endImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_file);
        setTitle("Preview");

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            endImage = extra.getString("endereco_image");
        }

        bitmap = BitmapFactory.decodeFile(endImage);

        imageFile = (ImageView) findViewById(R.id.image_file);
        btCancelar = (Button) findViewById(R.id.bt_cancelar_file);
        btSalvar = (Button) findViewById(R.id.bt_processar_file);

        imageFile.setImageBitmap(bitmap);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ImagemArquivoActivity.this, "Processando >> " + endImage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
