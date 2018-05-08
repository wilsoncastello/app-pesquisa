package br.com.george.menutest.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.george.menutest.Adapter.DatabaseAdapter;
import br.com.george.menutest.Database.Database;
import br.com.george.menutest.Model.ImagemBD;
import br.com.george.menutest.Model.Tag;
import br.com.george.menutest.R;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listDatabase;
    private List<Tag> tags;
    private List<ImagemBD> imagens;
    private ArrayAdapter adapter;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        setTitle("Etiquetas no Banco");

        database = new Database(DatabaseActivity.this);

        tags = new ArrayList<>();
        tags = database.buscarTodasTags();

        imagens = new ArrayList<>();
        imagens = database.buscarTodasImagens();

        adapter = new DatabaseAdapter(DatabaseActivity.this, tags);
        listDatabase = (ListView) findViewById(R.id.listTagsDatabase);

        listDatabase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final Dialog dialog = new Dialog(DatabaseActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menu_database);

                Button btnExcluir = (Button) dialog.findViewById(R.id.btnExcluirDatabase);
                Button btnImagem = (Button) dialog.findViewById(R.id.btnImagemDatabase);
                Button btnAdcImagem = (Button) dialog.findViewById(R.id.btnAdicionarImagemDatabase);
                Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancelarDatabase);

                btnExcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(ImagemBD img: imagens){
                            if(tags.get(position).getCod() == img.getCodTag()){
                                String srcFileDelete = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOCUMENTS + "/ImagensBancoIFSC/" + img.getImagem().substring(100);
                                File fdelete = new File(srcFileDelete);
                                if (fdelete.exists()) {
                                    if (fdelete.delete()) {
                                        Log.i("DELETE", "deletou!!!");
                                    } else {
                                        Log.i("DELETE", "não deletou!!!");
                                    }
                                }

                                database.excluirImagem(img.getCod());
                            }
                        }

                        database.excluirTag(tags.get(position).getCod());
                        finish();
                    }
                });

                btnImagem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<Tag> listTags = database.buscarTodasTags();

                        for (Tag tagAtual : listTags) {
                            if (listTags.indexOf(tagAtual) == position) {

                                List<ImagemBD> imagensBanco = new ArrayList<>();
                                ArrayList<String> imagensBancoEnd = new ArrayList<>();

                                ArrayList<String> datasBanco = new ArrayList<>();
                                ArrayList<String> descricaoBanco = new ArrayList<>();

                                imagensBanco = database.buscarTodasImagens();

                                for(ImagemBD imagemBD: imagensBanco){
                                    if(imagemBD.getCodTag() == tagAtual.getCod()){
                                        imagensBancoEnd.add(imagemBD.getImagem());
                                        datasBanco.add(imagemBD.getData());
                                        descricaoBanco.add(imagemBD.getDescricao());
                                    }
                                }

                                Intent intent = new Intent(DatabaseActivity.this, ImagemEtiquetaActivity.class);
                                intent.putExtra("image_etiqueta", imagensBancoEnd);
                                intent.putExtra("data_etiqueta", datasBanco);
                                intent.putExtra("descricao_etiqueta", descricaoBanco);

                                startActivity(intent);
                            }
                        }
                        dialog.dismiss();
                    }
                });

                btnAdcImagem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<Tag> listTags = database.buscarTodasTags();

                        for (Tag tagAtual : listTags) {
                            if (listTags.indexOf(tagAtual) == position) {
                                Intent intent = new Intent(DatabaseActivity.this, AdicionarFotoActivity.class);
                                intent.putExtra("tagAtual", tagAtual.getIdentificacao());
                                startActivity(intent);
                            }
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

        listDatabase.setAdapter(adapter);

    }
}
