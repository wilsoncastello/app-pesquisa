package br.com.george.menutest.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.george.menutest.R;

public class ImageCameraActivity extends AppCompatActivity {

    private ImageView imageCamera;
    private Button btCancelar;
    private Button btSalvar;
    private Uri endFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_camera);
        setTitle("Preview");

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            endFoto = (Uri)extra.get("image");
        }

        imageCamera = (ImageView) findViewById(R.id.image_camera);
        btCancelar = (Button) findViewById(R.id.bt_cancelar);
        btSalvar = (Button) findViewById(R.id.bt_processar);

        imageCamera.setImageURI(endFoto);
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
                Toast.makeText(ImageCameraActivity.this, "Processando >> " + endFoto.getPath(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
