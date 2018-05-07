package br.com.george.menutest.Model;

import java.util.ArrayList;

public class Card {

    private String tituloCard;
    private String descricaoCard;
    private int imgCardResource;
    private String texto;
    private ArrayList<String> paragrafos;
    private ArrayList<String> paragrafos2;
    private ArrayList<Image> imagens;

    public Card(String tituloCard, String descricaoCard, int imgCardResource, ArrayList<String> paragrafo, ArrayList<String> paragrafo2, ArrayList<Image> imagens) {
        this.tituloCard = tituloCard;
        this.descricaoCard = descricaoCard;
        this.imgCardResource = imgCardResource;
        this.texto = texto;
        this.paragrafos = paragrafo;
        this.paragrafos2 = paragrafo2;
        this.imagens = imagens;

    }

    public String getTituloCard() {
        return tituloCard;
    }

    public void setTituloCard(String tituloCard) {
        this.tituloCard = tituloCard;
    }

    public String getDescricaoCard() {
        return descricaoCard;
    }

    public void setDescricaoCard(String descricaoCard) {
        this.descricaoCard = descricaoCard;
    }

    public int getImgCardResource() {
        return imgCardResource;
    }

    public void setImgCardResource(int imgCard) {
        this.imgCardResource = imgCard;
    }

    public ArrayList<String> getParagrafos() {
        return paragrafos;
    }

    public void setParagrafos(ArrayList<String> paragrafos) {
        this.paragrafos = paragrafos;
    }

    public ArrayList<String> getParagrafos2() {
        return paragrafos2;
    }

    public void setParagrafos2(ArrayList<String> paragrafos2) {
        this.paragrafos2 = paragrafos2;
    }

    public ArrayList<Image> getImagens() {
        return imagens;
    }

    public void setImagens(ArrayList<Image> imagens) {
        this.imagens = imagens;
    }
}
