package br.eti.gregori.ccih.model;

public class AntibioticTest extends BaseModel {
    private Antibiogram antibiogram;
    private Antibiotic antibiotic;
    private boolean resistant;

    public AntibioticTest() {
    }

    public AntibioticTest(int id, Antibiogram antibiogram, Antibiotic antibiotic, boolean resistant) {
        super(id);
        this.antibiogram = antibiogram;
        this.antibiotic = antibiotic;
        this.resistant = resistant;
    }

    public Antibiogram getAntibiogram() {
        return antibiogram;
    }

    public void setAntibiogram(Antibiogram antiBiogram) {
        this.antibiogram = antiBiogram;
    }

    public Antibiotic getAntibiotic() {
        return antibiotic;
    }

    public void setAntibiotic(Antibiotic antibiotic) {
        this.antibiotic = antibiotic;
    }

    public boolean isResistant() {
        return resistant;
    }

    public void setResistant(boolean resistant) {
        this.resistant = resistant;
    }
}
