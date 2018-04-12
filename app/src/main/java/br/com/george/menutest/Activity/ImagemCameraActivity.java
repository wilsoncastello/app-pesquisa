package br.com.george.menutest.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import br.com.george.menutest.R;

public class ImagemCameraActivity extends AppCompatActivity {

    private ImageView imageCamera;
    private Button btCancelar;
    private Button btSalvar;
    private Uri endFoto;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_camera);
        setTitle("Preview");

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            endFoto = (Uri)extra.get("image");
        }

        try {
            InputStream is = getContentResolver().openInputStream(endFoto);
            bitmap = BitmapFactory.decodeStream(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageCamera = (ImageView) findViewById(R.id.image_camera);
        btCancelar = (Button) findViewById(R.id.bt_cancelar);
        btSalvar = (Button) findViewById(R.id.bt_processar);

        imageCamera.setImageBitmap(bitmap);
        imageCamera.setRotation(90);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ImagemCameraActivity.this, "Salvando >> " + endFoto.getPath(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
