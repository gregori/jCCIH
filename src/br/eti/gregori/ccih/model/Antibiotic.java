package br.eti.gregori.ccih.model;

public class Antibiotic extends BaseModel {
    private String name;
    private String acronym;
    private Gram gram;

    public Antibiotic() { }

    public Antibiotic(int id, String name, String acronym, Gram gram) {
        super(id);
        this.name = name;
        this.acronym = acronym;
        this.gram = gram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Gram getGram() {
        return gram;
    }

    public void setGram(Gram gram) {
        this.gram = gram;
    }
}
