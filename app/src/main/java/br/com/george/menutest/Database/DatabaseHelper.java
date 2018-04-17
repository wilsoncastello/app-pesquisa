package br.com.george.menutest.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //NOME DA BASE DE DADOS
    private static final String NOME_BANCO = "rfid.db";
    //VERSÃO DO BANCO DE DADOS
    private static final int VERSAO_BANCO = 5;

    public DatabaseHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder stringBuilderCreateTableTags = new StringBuilder();
        StringBuilder stringBuilderCreateTableImagem = new StringBuilder();

        stringBuilderCreateTableTags.append(" CREATE TABLE Tags (");
        stringBuilderCreateTableTags.append("        id             INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTableTags.append("        descricao      TEXT,                              ");
        stringBuilderCreateTableTags.append("        identificacao  TEXT    NOT NULL                  )");

        stringBuilderCreateTableImagem.append(" CREATE TABLE Imagem (");
        stringBuilderCreateTableImagem.append("        id             INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTableImagem.append("        idTag          INTEGER,                           ");
        stringBuilderCreateTableImagem.append("        data           TEXT,                              ");
        stringBuilderCreateTableImagem.append("        imagem         TEXT,                              ");
        stringBuilderCreateTableImagem.append("        FOREIGN KEY    (idTag)    REFERENCES  Tag(id)    )");

        db.execSQL(stringBuilderCreateTableTags.toString());
        db.execSQL(stringBuilderCreateTableImagem.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Imagem");
        db.execSQL("DROP TABLE IF EXISTS Tags");
        onCreate(db);
    }

    public SQLiteDatabase GetConexaoDataBase() {
        return this.getWritableDatabase();
    }
}
