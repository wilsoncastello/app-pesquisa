package br.com.george.menutest.Model;

public class Image {

    private int endImage;
    private String titulo;

    public Image(int endImage, String titulo) {
        this.endImage = endImage;
        this.titulo = titulo;
    }

    public int getEndImage() {
        return endImage;
    }

    public void setEndImage(int endImage) {
        this.endImage = endImage;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
