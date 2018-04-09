package br.com.george.menutest.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.george.menutest.Database.TagDAO;
import br.com.george.menutest.Model.Tag;
import br.com.george.menutest.R;

public class CadastroActivity extends AppCompatActivity {
    private EditText edtDescricao;
    private TextView txtIdentificacao;
    private Tag tag;
    private Button btnGravar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        setTitle("Cadastro de Etiquetas");

        // Pegando os componentes da tela de cadastro (activity_cadastro.xml
        edtDescricao = (EditText) findViewById(R.id.editDescricaoEtiqueta);
        txtIdentificacao = (TextView) findViewById(R.id.txtIdTag);

        if (getIntent().hasExtra("tag")) {
            txtIdentificacao.setText(getIntent().getExtras().getString("tag").toString());
        }

        btnGravar = (Button) findViewById(R.id.btnSalvarBD);
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tag = new Tag();

                tag.setIdentificacao(txtIdentificacao.getText().toString());
                tag.setDescricao(edtDescricao.getText().toString());

                new TagDAO(CadastroActivity.this).Salvar(tag);

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
                finish();
            }
        });
    }

    public void verificaEtiquetasSalvas(String tag){

        List<Tag> tagsVerificacao = new TagDAO(CadastroActivity.this).SelecionarTodos();

        for(Tag tagAux: tagsVerificacao) {
            if (tagAux.equals(tag)) {
                Toast.makeText(CadastroActivity.this, "Etiqueta já salva!", Toast.LENGTH_LONG).show();
                finish();
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

