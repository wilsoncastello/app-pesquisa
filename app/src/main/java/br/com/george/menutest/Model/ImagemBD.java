package br.com.george.menutest.Model;

import java.io.Serializable;

public class ImagemBD implements Serializable {

    //IDENTITY DA TABELA
    private int cod;
    private int codTag;
    private String imagem;
    private String data;

    public int getCod() {
        return cod;
    }
    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCodTag() {
        return codTag;
    }
    public void setCodTag(int codTag) {
        this.codTag = codTag;
    }

    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
