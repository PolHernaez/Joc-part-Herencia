package src;

public class Orc extends Character {

    public Orc(String name, int age, double strength, double dexterity, double constitution, double intelligence,
            double wisdom, double charisma) {
        super(name, age, strength, dexterity, constitution, intelligence, wisdom, charisma);
        double newStre = comprovarMax(getStrength(), 3);
        double newConst = comprovarMax(getConstitution(), 1);
        setConstitution(newConst);
        setStrength(newStre);
        recalcularAtributs();
    }

    @Override
    public double atacar() {

        return super.atacar() * 1.10;
    }

    @Override
    public void cambiarArma(Weapon arma) {
        if (arma.getMagicPhysical()) {
            System.out.println("Els orcs no poden equipar armes màgiques");
        } else {
            super.cambiarArma(arma);
        }
    }

    @Override
    public String getRaca() {
        return "ORC";
    }

}
