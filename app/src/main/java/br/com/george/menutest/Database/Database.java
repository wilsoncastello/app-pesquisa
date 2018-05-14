package br.com.george.menutest.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.george.menutest.Database.DAO.ImagemDAO;
import br.com.george.menutest.Database.DAO.TagDAO;
import br.com.george.menutest.Model.ImagemBD;
import br.com.george.menutest.Model.Tag;

public class Database {

    private SQLiteDatabase db;

    public Database(Context ctx) {
        DatabaseHelper helper = new DatabaseHelper(ctx);
        db = helper.GetConexaoDataBase();
    }

    // region Tag
    public void salvarTag(Tag tag){
        new TagDAO(db).salvar(tag);
    }

    public void atualizarTag(Tag tag){
        new TagDAO(db).atualizar(tag);
    }

    public void excluirTag(int cod){
        new TagDAO(db).excluir(cod);
    }

    public Tag buscarTagId(int cod){
        return new TagDAO(db).buscarTagId(cod);
    }

    public List<Tag> buscarTodasTags(){
        return new TagDAO(db).buscarTodos();
    }

    public void deletarTodasTags(){
        new TagDAO(db).deletarTodos();
    }
    // end region Tag

    // region Imagem
    public void salvarImagem(ImagemBD imagemBD){
        new ImagemDAO(db).salvar(imagemBD);
    }

    public void atualizarImagem(ImagemBD imagemBD){
        new ImagemDAO(db).atualizar(imagemBD);
    }

    public void excluirImagem(int cod){
        new ImagemDAO(db).excluir(cod);
    }

    public void excluirImagemTag(int cod){
        new ImagemDAO(db).excluirCodTag(cod);
    }

    public ImagemBD buscarIamgemId(int cod){
        return new ImagemDAO(db).buscarImagemId(cod);
    }

    public List<ImagemBD> buscarTodasImagens(){
        return new ImagemDAO(db).buscarTodos();
    }

    public void deletarTodasImagens(){
        new ImagemDAO(db).deletarTodos();
    }
    // end region Imagem

}
