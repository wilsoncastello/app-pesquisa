package br.com.george.menutest.Model;

public class Image {

    private int id; // Composto por dois números, o primeiro numero se refere a categoria e o segundo ao numero da imagem.
    private int endImage;
    private String titulo;

    public Image(int id, int endImage, String titulo) {
        this.id = id;
        this.endImage = endImage;
        this.titulo = titulo;
    }

    public Image() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
