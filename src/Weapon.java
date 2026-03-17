package src;

public class Weapon {
    private String type; //tipus
    private int damage; //dany
    private boolean magicPhysical; //magic o fisic

    public Weapon(String type, int damage, boolean magicPhysical) {
        this.type = type;
        this.damage = damage;
        this.magicPhysical = magicPhysical;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setMagicPhysical(boolean magicPhysical) {
        this.magicPhysical = magicPhysical;
    }

    public boolean getMagicPhysical() {
        return this.magicPhysical;
    }

    
    public String toString() {
        String info;
             String tipusAtac;
        
        if (this.magicPhysical) {
            tipusAtac = "Màgica";
        } else {
            tipusAtac = "Física";
        }
        info =  "Tipus: " + this.type + " Dany: " + this.damage + " Màgica/Física: " + tipusAtac ;
        return info;
    }
}
