package src;

public class Human extends Character {

    public Human(String name, int age, double strength, double dexterity, double constitution, double intelligence,
            double wisdom, double charisma) {
        super(name, age, strength, dexterity, constitution, intelligence, wisdom, charisma);
        setStrength(comprovarMax(getStrength(), 1));
        setDexterity(comprovarMax(getDexterity(), 1));
        setConstitution(comprovarMax(getConstitution(), 1));
        setIntelligence(comprovarMax(getIntelligence(), 1));
        setWisdom(comprovarMax(getWisdom(), 1));
        setCharisma(comprovarMax(getCharisma(), 1));
        recalcularAtributs();
    }
@Override
public String getRaca(){
    return "HUMAN";
}
}
