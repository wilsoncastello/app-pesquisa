package br.com.george.menutest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.george.menutest.Model.Tag;


public class TagDAO {

    Database database;

    public TagDAO(Context context) {
        database = new Database(context);
    }

    public void Salvar(Tag tag) {
        ContentValues contentValues = new ContentValues();

        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("descricao", tag.getDescricao());
        contentValues.put("identificacao", tag.getIdentificacao());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        database.GetConexaoDataBase().insert("tags", null, contentValues);
    }

    public void Atualizar(Tag tag) {
        ContentValues contentValues = new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/
        contentValues.put("descricao", tag.getDescricao());
        contentValues.put("identificacao", tag.getIdentificacao());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        database.GetConexaoDataBase().update("tags", contentValues, "id = ?", new String[]{Integer.toString(tag.getCod())});
    }

    public int Excluir(int codigo) {
        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        return database.GetConexaoDataBase().delete("tags", "id = ?", new String[]{Integer.toString(codigo)});
    }

    public Tag GetTag(int codigo) {
        Cursor cursor = database.GetConexaoDataBase().rawQuery("SELECT * FROM tags WHERE id= " + codigo, null);

        cursor.moveToFirst();

        ///CRIANDO UMA NOVA TAG
        Tag tag = new Tag();

        //ADICIONANDO OS DADOS DA TAG
        tag.setCod(cursor.getInt(cursor.getColumnIndex("id")));
        tag.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        tag.setIdentificacao(cursor.getString(cursor.getColumnIndex("identificacao")));

        //RETORNANDO A TAG
        return tag;
    }

    public List<Tag> SelecionarTodos() {

        List<Tag> tags = new ArrayList<Tag>();

        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id,            ");
        stringBuilderQuery.append("        nome,          ");
        stringBuilderQuery.append("        descricao,     ");
        stringBuilderQuery.append("        identificacao ");
        stringBuilderQuery.append("  FROM  tags           ");
        stringBuilderQuery.append(" ORDER BY id           ");

        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = database.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();

        Tag tag;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()) {

            /* CRIANDO UMA NOVA PESSOAS */
            tag = new Tag();

            //ADICIONANDO OS DADOS DA PESSOA
            tag.setCod(cursor.getInt(cursor.getColumnIndex("id")));
            tag.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            tag.setIdentificacao(cursor.getString(cursor.getColumnIndex("identificacao")));

            //ADICIONANDO UMA PESSOA NA LISTA
            tags.add(tag);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }

        //RETORNANDO A LISTA DE PESSOAS
        return tags;
    }
}
