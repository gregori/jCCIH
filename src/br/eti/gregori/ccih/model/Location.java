package br.eti.gregori.ccih.model;

public class Location extends BaseModel {

    private String name;

    public Location() { super(); }

    public Location(int id, String name) {
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
