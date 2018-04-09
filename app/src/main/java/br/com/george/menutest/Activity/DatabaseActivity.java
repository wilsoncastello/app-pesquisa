package br.com.george.menutest.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.george.menutest.Adapter.DatabaseAdapter;
import br.com.george.menutest.Database.TagDAO;
import br.com.george.menutest.Model.Tag;
import br.com.george.menutest.R;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listDatabase;
    private List<Tag> tags;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        setTitle("Etiquetas no Banco");

        tags = new ArrayList<>();
        tags = new TagDAO(DatabaseActivity.this).SelecionarTodos();

        adapter = new DatabaseAdapter(DatabaseActivity.this, tags);
        listDatabase = (ListView) findViewById(R.id.listTagsDatabase);

        listDatabase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final Dialog dialog = new Dialog(DatabaseActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menu_database);

                Button btnExcluir = (Button) dialog.findViewById(R.id.btnExcluirDatabase);
                Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancelarDatabase);

                btnExcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new TagDAO(DatabaseActivity.this).Excluir(tags.get(position).getCod());
                        finish();
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
