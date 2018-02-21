package br.com.george.menutest.Model;

public class Video {

    private String descricao;
    private int resourceImg;
    private String linkVideo;

    public Video(String descricao, int resourceImg, String linkVideo) {
        this.descricao = descricao;
        this.resourceImg = resourceImg;
        this.linkVideo = linkVideo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getResourceImg() {
        return resourceImg;
    }

    public void setResoruceImg(int resourceImg) {
        this.resourceImg = resourceImg;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }
}
