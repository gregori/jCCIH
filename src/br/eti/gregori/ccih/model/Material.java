package br.eti.gregori.ccih.model;

public class Material extends BaseModel {

    private String name;

    public Material() { super(); }

    public Material(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

