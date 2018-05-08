package br.com.george.menutest.Database.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.george.menutest.Model.ImagemBD;

public class ImagemDAO {

    private SQLiteDatabase database;

    public ImagemDAO(SQLiteDatabase db) {
        database = db;
    }

    public void salvar(ImagemBD imagem) {
        ContentValues contentValues = new ContentValues();

        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("idTag", imagem.getCodTag());
        contentValues.put("data", imagem.getData());
        contentValues.put("imagem", imagem.getImagem());
        contentValues.put("descricao", imagem.getDescricao());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        database.insert("Imagem", null, contentValues);
    }

    public void atualizar(ImagemBD imagem) {
        ContentValues contentValues = new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/
        contentValues.put("idTag", imagem.getData());
        contentValues.put("data", imagem.getData());
        contentValues.put("imagem", imagem.getImagem());
        contentValues.put("descricao", imagem.getDescricao());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        database.update("Imagem", contentValues, "id = ?", new String[]{Integer.toString(imagem.getCod())});
    }

    public void excluir(int codigo) {
        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        database.delete("Imagem", "id = ?", new String[]{Integer.toString(codigo)});
    }

    public ImagemBD buscarImagemId(int codigo) {
        ImagemBD imagemBD = new ImagemBD();
        String[] colunas = new String[]{"id", "idTag", "data","imagem", "descricao"};

        Cursor cursor = database.query("Imagem",colunas,"id ="+codigo, null,null,null,null);

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();

            imagemBD.setCod(cursor.getInt(0));
            imagemBD.setCodTag(cursor.getInt(1));
            imagemBD.setData(cursor.getString(2));
            imagemBD.setImagem(cursor.getString(3));
            imagemBD.setDescricao(cursor.getString(4));
        }
        else {
            return null;
        }

        return imagemBD;
    }

    public List<ImagemBD> buscarTodos() {

        List<ImagemBD> imagens = new ArrayList<ImagemBD>();

        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id,            ");
        stringBuilderQuery.append("        idTag,         ");
        stringBuilderQuery.append("        data,          ");
        stringBuilderQuery.append("        imagem,         ");
        stringBuilderQuery.append("        descricao      ");
        stringBuilderQuery.append("  FROM  Imagem         ");
        stringBuilderQuery.append(" ORDER BY id           ");

        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = database.rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();

        ImagemBD imagem;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()) {

            /* CRIANDO UMA NOVA PESSOAS */
            imagem = new ImagemBD();

            //ADICIONANDO OS DADOS DA PESSOA
            imagem.setCod(cursor.getInt(cursor.getColumnIndex("id")));
            imagem.setCodTag(cursor.getInt(cursor.getColumnIndex("idTag")));
            imagem.setData(cursor.getString(cursor.getColumnIndex("data")));
            imagem.setImagem(cursor.getString(cursor.getColumnIndex("imagem")));
            imagem.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));

            //ADICIONANDO UMA PESSOA NA LISTA
            imagens.add(imagem);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }

        //RETORNANDO A LISTA DE PESSOAS
        return imagens;
    }

    public void deletarTodos() {
        database.delete("Imagem", "", null);
    }
}