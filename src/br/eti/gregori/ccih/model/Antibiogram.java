package br.eti.gregori.ccih.model;

public class Antibiogram extends BaseModel{
    private Bacteria bacteria;
    private Location location;
    private Material material;

    public Antibiogram(int id, Bacteria bacteria, Location location, Material material) {
        super(id);
        this.bacteria = bacteria;
        this.location = location;
        this.material = material;
    }

    public Antibiogram() {

    }

    public Bacteria getBacteria() {
        return bacteria;
    }

    public void setBacteria(Bacteria bacteria) {
        this.bacteria = bacteria;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
