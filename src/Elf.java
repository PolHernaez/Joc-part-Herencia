package src;

public class Elf extends Character {
    
    public Elf(String name, int age, double strength, double dexterity, double constitution,
            double intelligence, double wisdom, double charisma) {
        super(name, age, strength, dexterity, constitution, intelligence, wisdom, charisma);
        double newDex = comprovarMax(getDexterity(), 2);
        setDexterity(newDex);
        double newIntll = comprovarMax(getIntelligence(), 2);

        setIntelligence(newIntll);

        recalcularAtributs();
    }

    @Override
    public void regenerarMana() {
        double max = getIntelligence() * 30;
        setMana(getMana() + (getIntelligence() * 3));
        if (getMana() > max) {
            setMana(max);
        }
    }

    @Override
    public String getRaca() {
        return "Elf";
    }

}
