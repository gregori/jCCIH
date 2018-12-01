package br.eti.gregori.ccih.model;

public class Bacteria extends BaseModel {
    private String name;
    private Gram gram;

    public Bacteria() {}

    public Bacteria(String name, Gram gram) {
        this.name = name;
        this.gram = gram;
    }
    public Bacteria(int id, String name, Gram gram) {
        super(id);
        this.name = name;
        this.gram = gram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gram getGram() {
        return gram;
    }

    public void setGram(Gram gram) {
        this.gram = gram;
    }
}
