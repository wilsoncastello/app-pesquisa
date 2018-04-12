package br.com.george.menutest.Activity;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import br.com.george.menutest.R;

public class ImagemEtiquetaActivity extends AppCompatActivity {

    private ImageView imageCamera;
    private Button btCancelar;
    private Button btSalvar;
    ArrayList<String> imagens;
    private Uri uriImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_etiqueta);
        setTitle("Preview");

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            imagens = extra.getStringArrayList("image_etiqueta");
        }

        imageCamera = (ImageView) findViewById(R.id.image_camera_etiqueta);
        btCancelar = (Button) findViewById(R.id.bt_cancelar_etiqueta);
        btSalvar = (Button) findViewById(R.id.bt_salvar_etiqueta);

//        uriImagem = Uri.parse(imagem.getImagem());
//
//        imageCamera.setImageURI(uriImagem);
//        imageCamera.setRotation(90);

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ImagemEtiquetaActivity.this, "Salvando >> " + imagem.getImagem(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}