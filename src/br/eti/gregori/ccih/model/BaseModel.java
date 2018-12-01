package br.eti.gregori.ccih.model;

public abstract class BaseModel {

    private int id;

    public BaseModel() { }

    public BaseModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
