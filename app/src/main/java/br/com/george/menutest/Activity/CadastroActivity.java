package br.com.george.menutest.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.george.menutest.Adapter.EndImagensAdapter;
import br.com.george.menutest.Database.Database;
import br.com.george.menutest.Model.DescricaoImagem;
import br.com.george.menutest.Model.ImagemBD;
import br.com.george.menutest.Model.Tag;
import br.com.george.menutest.R;

public class CadastroActivity extends AppCompatActivity {
    private EditText edtDescricao;
    private TextView txtIdentificacao;
    private ListView listEndImagens;
    private ImageView btnCamera;
    private Tag tag;
    private Uri file;
    private Button btnSalvar;
    private Button btnCancelar;
    private Database database;
    private ImagemBD imagemBD;
    private String dataImagem;
    private List<String> endImagemArray;
    private int codTagImagem;
    private ArrayAdapter adapter;
    private List<ImagemBD> imagens;
    private List<DescricaoImagem> descImagens;

    static final int OPEN_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        setTitle("Cadastro de Etiquetas");

        endImagemArray = new ArrayList<>();
        descImagens = new ArrayList<>();

        // Iniciando o banco
        database = new Database(CadastroActivity.this);

        // Pegando os componentes da tela de cadastro (activity_cadastro.xml
        edtDescricao = (EditText) findViewById(R.id.editDescricaoEtiqueta);
        txtIdentificacao = (TextView) findViewById(R.id.txtIdTag);
        listEndImagens = (ListView) findViewById(R.id.listEndImagens);

        imagens = new ArrayList<>();
        imagens = database.buscarTodasImagens();

        if (getIntent().hasExtra("tag")) {
            txtIdentificacao.setText(getIntent().getExtras().getString("tag").toString());
        }

        btnSalvar = (Button) findViewById(R.id.btnSalvarBD);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tag = new Tag();
                tag.setIdentificacao(txtIdentificacao.getText().toString());
                tag.setDescricao(edtDescricao.getText().toString());
                database.salvarTag(tag);

                List<Tag> tagsAux = database.buscarTodasTags();
                for (Tag t : tagsAux) {
                    if (tag.getIdentificacao().equals(t.getIdentificacao())) {
                        codTagImagem = t.getCod();
                    }
                }

                dataImagem = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

                int i = 0;
                String[] vetorDescricao = new String[descImagens.size()];
                String[] vetorEnd = new String[descImagens.size()];
                for(DescricaoImagem d: descImagens){
                    vetorDescricao[i] = d.getDescricao();
                    vetorEnd[i] = d.getEndImagem();
                    i++;
                }

                int j = 0;
                for (String endImagem : endImagemArray) {
                    imagemBD = new ImagemBD();
                    imagemBD.setCodTag(codTagImagem);
                    imagemBD.setData(dataImagem);
                    imagemBD.setImagem(endImagem);
                    if (endImagem.equals(vetorEnd[j])) {
                        imagemBD.setDescricao(vetorDescricao[j]);
                    }

                    database.salvarImagem(imagemBD);
                    j++;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(CadastroActivity.this);
                builder.setMessage("Registro salvo com sucesso!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnCancelar = (Button) findViewById(R.id.btnCancelarCadastro);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (String endImage : endImagemArray) {
                    String srcFileDelete = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOCUMENTS + "/ImagensBancoIFSC/" + endImage.substring(100);
                    File fdelete = new File(srcFileDelete);
                    if (fdelete.exists()) {
                        if (fdelete.delete()) {
                            Log.i("DELETE", "deletou!!!");
                        } else {
                            Log.i("DELETE", "não deletou!!!");
                        }
                    }
                }

                finish();
            }
        });

        btnCamera = (ImageView) findViewById(R.id.imageCadastro);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture(view);
            }
        });
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = FileProvider.getUriForFile(CadastroActivity.this, getApplicationContext().getPackageName() + ".my.package.name.provider", getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, OPEN_CAMERA);
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "ImagensBancoIFSC");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent;

        if (resultCode != RESULT_CANCELED) {
            if (requestCode == OPEN_CAMERA) {
                endImagemArray.add(file.toString());

                adapter = new EndImagensAdapter(CadastroActivity.this, endImagemArray);
                listEndImagens.setAdapter(adapter);

                listEndImagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long l) {

                        final Dialog dialog = new Dialog(CadastroActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.menu_cadastro);

                        Button btnDescricao = (Button) dialog.findViewById(R.id.btnDescricaoCadastro);
                        Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancelarCadastro);
                        final EditText editDescricao = (EditText) dialog.findViewById(R.id.editDescricao);

                        btnDescricao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (!editDescricao.equals("")) {
                                    String endereco = endImagemArray.get(position);
                                    String descricao = editDescricao.getText().toString();
                                    DescricaoImagem descricaoImagem = new DescricaoImagem(endereco, descricao);

                                    descImagens.add(descricaoImagem);
                                    Toast.makeText(CadastroActivity.this, "Descrição inserida", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(CadastroActivity.this, "Preencha os campos", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        btnCancelar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    }
                });
            } else {
                Toast.makeText(CadastroActivity.this, "Ocorreu algum erro!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //region MÉTODOS NÃO UTILIZADOS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_cad_patrimonio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    //endregion
}

