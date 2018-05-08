package br.com.george.menutest.Model;

public class DescricaoImagem {

    private String endImagem;
    private String descricao;

    public DescricaoImagem(String endImagem, String descricao) {
        this.endImagem = endImagem;
        this.descricao = descricao;
    }

    public String getEndImagem() {
        return endImagem;
    }

    public void setEndImagem(String endImagem) {
        this.endImagem = endImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
