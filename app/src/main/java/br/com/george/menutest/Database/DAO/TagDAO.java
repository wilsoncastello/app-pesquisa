package br.com.george.menutest.Database.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.george.menutest.Model.ImagemBD;
import br.com.george.menutest.Model.Tag;

public class TagDAO {

    private SQLiteDatabase database;

    public TagDAO(SQLiteDatabase db) {
        database = db;
    }

    public void salvar(Tag tag) {
        ContentValues contentValues = new ContentValues();

        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("descricao", tag.getDescricao());
        contentValues.put("identificacao", tag.getIdentificacao());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        database.insert("Tags", null, contentValues);
    }

    public void atualizar(Tag tag) {
        ContentValues contentValues = new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/
        contentValues.put("descricao", tag.getDescricao());
        contentValues.put("identificacao", tag.getIdentificacao());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        database.update("Tags", contentValues, "id = ?", new String[]{Integer.toString(tag.getCod())});
    }

    public void excluir(int codigo) {
        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
         database.delete("Tags", "id = ?", new String[]{Integer.toString(codigo)});
    }

    public Tag buscarTagId(int codigo) {
        Tag tag = new Tag();
        String[] colunas = new String[]{"id", "descricao", "identificacao"};

        Cursor cursor = database.query("Tag",colunas,"id ="+codigo, null,null,null,null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();

            tag.setCod(cursor.getInt(0));
            tag.setDescricao(cursor.getString(1));
            tag.setIdentificacao(cursor.getString(3));
        }
        else {
            return null;
        }

        return tag;
    }

    public List<Tag> buscarTodos() {

        List<Tag> tags = new ArrayList<Tag>();

        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id,            ");
        stringBuilderQuery.append("        descricao,     ");
        stringBuilderQuery.append("        identificacao  ");
        stringBuilderQuery.append("  FROM  Tags           ");
        stringBuilderQuery.append(" ORDER BY id           ");

        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = database.rawQuery(stringBuilderQuery.toString(), null);

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

    public void deletarTodos() {
        database.delete("Tags", "", null);
    }

    private ImagemBD montarImagem(int codigo){
        ImagemBD imagem = new ImagemBD();
        ImagemDAO imagemDAO = new ImagemDAO(database);

        imagem = imagemDAO.buscarImagemId(codigo);

        return imagem;
    }
}
