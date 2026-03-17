package src;

import java.util.ArrayList;

enum Race {
    ORC, ELF, DUORF, HUMAN
}

public class Character {
    private String name;
    private int age;
    private double health;
    private double mana;
    private Weapon weapon;
    private Race race;
    private double strength;
    private double dexterity;
    private double constitution;
    private double intelligence;
    private double wisdom;
    private double charisma;
    private int powerLevel;
    private ArrayList<Weapon> armes;
    private Weapon armaEquipada;

    public Character(String name, int age, Race race, double strength, double dexterity,
            double constitution, double intelligence, double wisdom, double charisma) {
        this.name = name;
        this.age = age;
        this.race = race;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.health = constitution * 50;
        this.mana = intelligence * 30;
        this.powerLevel = 0;
        this.armes = new ArrayList<>();
        this.armaEquipada = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Race getRace() {
        return this.race;
    }

    public double getHealth() {
        return this.health;
    }

    public double getMana() {
        return this.mana;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public double getStrength() {
        return this.strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getDexterity() {
        return this.dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public double getConstitution() {
        return this.constitution;
    }

    public void setConstitution(double constitution) {
        this.constitution = constitution;
    }

    public double getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public double getWisdom() {
        return this.wisdom;
    }

    public void setWisdom(double wisdom) {
        this.wisdom = wisdom;
    }

    public double getCharisma() {
        return this.charisma;
    }

    public void setCharisma(double charisma) {
        this.charisma = charisma;
    }

    public int getPowerLevel() {
        return this.powerLevel;
    }

    public ArrayList<Weapon> getArmes() {
        return this.armes;
    }

    public String toString() {
        String info = "Nom: " + this.name + " Edat: " + this.age + " Raça: " + this.race +
                " Salut: " + this.health + " Manà: " + this.mana +
                " Arma: " + this.weapon + " Força: " + this.strength +
                " Destresa: " + this.dexterity + " Constitució: " + this.constitution +
                " Intel·ligència: " + this.intelligence + " Saviesa: " + this.wisdom +
                " Carisma: " + this.charisma + " Power Level: " + this.powerLevel;
        return info;
    }

    public double atacar() {
        if (armaEquipada == null || !armaEquipada.getMagicPhysical()) {
            double danyArma;
            if (armaEquipada == null) {
                danyArma = 0;
            } else {
                danyArma = armaEquipada.getDamage();
            }
            return strength * (1 + danyArma / 100);
        } else {
            return armaEquipada.getDamage() * intelligence / 100;
        }
    }

    public double defensar(double dany) {
        return dany / 2;
    }

    public void regenerarVida() {
        double max = this.constitution * 50;
        this.health = this.health + this.constitution * 3;
        if (this.health > max) {
            this.health = max;
        }
    }

    public void regenerarMana() {
        double max = this.intelligence * 30;
        this.mana = this.mana + this.intelligence * 2;
        if (this.mana > max) {
            this.mana = max;
        }
    }

    public boolean esquivar() {
        double probability = (this.dexterity - 5) * 3.33;
        double random = (int) (Math.random() * 100) + 1;
        if (random <= probability) {
            return true;
        } else {
            return false;
        }
    }

    public void rebreDanyAtac(double dany) {
        this.health = this.health - dany;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void afegirArma(Weapon arma) {
        this.armes.add(arma);
    }

    public void cambiarArma(Weapon arma) {
        if (arma.getMagicPhysical() && this.intelligence < 10) {
            System.out.println("No pots equipar una arma màgica, necessites intel·ligència igual o superior a 10");
        } else {
            this.armaEquipada = arma;
            System.out.println("Arma equipada: " + arma.getType());
        }
    }

    public void powerUp() {
        if (this.powerLevel >= 3) {
            System.out.println("Ja estàs al nivell màxim!");
        } else if (this.mana < 50) {
            System.out.println("No tens prou maná per evolucionar!");
        } else {
            this.mana = this.mana - 50;
            this.powerLevel++;
            this.strength = this.strength * 1.5;
            this.dexterity = this.dexterity * 1.5;
            this.constitution = this.constitution * 1.5;
            this.intelligence = this.intelligence * 1.5;
            System.out.println("Has evolucionat al nivell " + this.powerLevel + "!");
        }
    }


}