package src;

public class Duorf extends Character {

    public Duorf(String name, int age, double strength, double dexterity, double constitution, double intelligence,
            double wisdom, double charisma) {
        super(name, age, strength, dexterity, constitution, intelligence, wisdom, charisma);
        setConstitution(comprovarMax(getConstitution(), 4));
        double newDex = getDexterity() - 1;
        if (newDex < 5) {
            newDex = 5;
        }
        setDexterity(newDex);
        recalcularAtributs();
    }

    @Override
    public double defensar(double dany) {
    return super.defensar(dany) * 0.75;
    }

    @Override
    public void regenerarVida() {
        double max = getConstitution() * 50;

        setHealth(getHealth() + getConstitution() * 4);
        if (getHealth() > max) {
            setHealth(max);
        }
    }

    @Override
    public String getRaca() {
        return "DUORF";
    }

}
