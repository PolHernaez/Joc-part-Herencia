package src;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class game {
    public static void main(String[] args) {
        game p = new game();
        p.principal();
    }

    public void principal() {
        ArrayList<Character> character = new ArrayList<Character>();
        System.out.println("BENVINGUT");
        int menu = 0;
        do {
            menu = llegirInt("\n 1- Crear personatge\n 2- Jugar combat simple (1vs1)\n 3-Sortir");
            switch (menu) {
                case 1:
                    crearPJ(character);
                    break;
                case 2:
                    if (character.size() < 2) {
                        System.out.println("Necessites com a mínim 2 personatges per jugar!");
                    } else {
                        jugar1v1(character);
                    }
                    break;
                case 3:
                    System.out.println("Fins aviat!");
                    break;
                default:
                    System.out.println("Trie entre el 1, 2 i 3");
                    break;
            }

        } while (menu != 3);

    }

    public void jugar1v1(ArrayList<Character> character) {
        Character pj1 = escollirPersonatge(character, 1, -1);
        Character pj2 = escollirPersonatge(character, 2, character.indexOf(pj1));
        System.out.println("Jugador 1: " + pj1.getName() + " vs Jugador 2: " + pj2.getName());
        combat(pj1, pj2);
    }

    public void combat(Character pj1, Character pj2) {

        int player = 1;
        boolean p1Defensa = false;
        boolean p2Defensa = false;

        System.out.println("\nArmes de " + pj1.getName() + ":");
        ArrayList<Weapon> armespj1 = pj1.getArmes();
        for (int i = 0; i < armespj1.size(); i++) {
            System.out.println((i + 1) + "- " + armespj1.get(i).toString());
        }
        int opcio1;
        do {
            opcio1 = llegirInt("Jugador 1, tria l'arma inicial:");
            opcio1--;
            if (opcio1 < 0 || opcio1 >= armespj1.size()) {
                System.out.println("Tria una arma vàlida!");
            }
        } while (opcio1 < 0 || opcio1 >= armespj1.size());
        pj1.cambiarArma(armespj1.get(opcio1));

        System.out.println("\nArmes de " + pj2.getName() + ":");
        ArrayList<Weapon> armespj2 = pj2.getArmes();
        for (int i = 0; i < armespj2.size(); i++) {
            System.out.println((i + 1) + "- " + armespj2.get(i).toString());
        }
        int opcio2;
        do {
            opcio2 = llegirInt("Jugador 2, tria l'arma inicial:");
            opcio2--;
            if (opcio2 < 0 || opcio2 >= armespj2.size()) {
                System.out.println("Tria una arma vàlida!");
            }
        } while (opcio2 < 0 || opcio2 >= armespj2.size());
        pj2.cambiarArma(armespj2.get(opcio2));

        while (pj1.getHealth() > 0 && pj2.getHealth() > 0) {

            int resposta = 0;
            if (player > 2) {
                player = 1;
            }
            Character atacant;
            Character defensor;
            if (player == 1) {
                atacant = pj1;
                defensor = pj2;
            } else {
                atacant = pj2;
                defensor = pj1;
            }
            System.out.println("\nArmes de " + atacant.getName() + ": ");
            ArrayList<Weapon> armes = atacant.getArmes();
            for (int i = 0; i < armes.size(); i++) {
                System.out.println((i + 1) + "- " + armes.get(i).toString());

            }
            String canviar = llegirString("Vols canviar d'arma? (si/no)");
            if (canviar.equalsIgnoreCase("si")) {
                int opcioArma;
                do {
                    opcioArma = llegirInt("Tria una arma:") - 1;
                    if (opcioArma < 0 || opcioArma >= armes.size()) {
                        System.out.println("Tria una arma vàlida!");
                    }
                } while (opcioArma < 0 || opcioArma >= armes.size());
                atacant.cambiarArma(armes.get(opcioArma));
            }
            do {
                resposta = llegirInt("Jugador  " + player + ": \n1- Atacar \n 2- Defensar\n 3- Evolucionar");
                if (resposta < 1 || resposta > 3) {
                    System.out.println("Tria entre 1 i 3!");
                }

            } while (resposta < 1 || resposta > 3);
            switch (resposta) {
                case 1:
                    double dany = atacant.atacar();
                    if (defensor.esquivar()) {
                        System.out.println(defensor.getName() + " ha esquivat l'atac!");
                    } else {
                        if (player == 1 && p2Defensa) {
                            dany = defensor.defensar(dany);
                            System.out.println(defensor.getName() + " estava defensant, rep menys dany");
                        } else if (player == 2 && p1Defensa) {
                            dany = defensor.defensar(dany);
                            System.out.println(defensor.getName() + " estava defensant, rep menys dany");
                        }
                        defensor.rebreDanyAtac(dany);
                        System.out.println(atacant.getName() + " ha fet " + dany + " de dany a " + defensor.getName());
                        System.out.println(defensor.getName() + " té " + defensor.getHealth() + " de vida restant");
                    }
                    p1Defensa = false;
                    p2Defensa = false;
                    break;
                case 2:
                    if (player == 1) {
                        p1Defensa = true;
                    } else {
                        p2Defensa = true;
                    }
                    System.out.println(atacant.getName() + " es defensa!");
                    break;
                case 3:
                    atacant.powerUp();
                    break;

            }
            pj1.regenerarVida();
            pj1.regenerarMana();
            pj2.regenerarVida();
            pj2.regenerarMana();
            player++;

        }
        if (pj1.getHealth() <= 0) {
            System.out.println(pj2.getName() + " ha guanyat el combat!");
        } else {
            System.out.println(pj1.getName() + " ha guanyat el combat!");
        }
    }

    public void crearPJ(ArrayList<Character> character) {
        boolean error = false;
        String resposta = "";
        do {
            resposta = llegirString("Vols omplir les dades manualment? (si/no)");
            if (resposta.equalsIgnoreCase("si") || resposta.equalsIgnoreCase("no")) {
                error = false;
            } else {
                error = true;
                System.out.println("Respon només si o no");
            }
        } while (error);
        if (resposta.equalsIgnoreCase("si")) {
            omplirManualment(character);
        } else if (resposta.equalsIgnoreCase("no")) {
            omplirAutomatic(character);
        }
    }

    public void omplirAutomatic(ArrayList<Character> character) {

        String nom = llegirString("Escriu el nom del personatge:");
        int edat;
        do {
            edat = llegirInt("Escriu l'edat del personatge");
        } while (edat <= 0);
        int opcioRaca = (int) (Math.random() * 4) + 1;

        int[] stats = { 5, 5, 5, 5, 5, 5 };
        int queden = 30;
        while (queden > 0) {
            int i = (int) (Math.random() * 6);
            if (stats[i] < 20) {
                stats[i]++;
                queden--;
            }
        }

        Character nou;
        if (opcioRaca == 1) {
            nou = new Orc(nom, edat, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
        } else if (opcioRaca == 2) {
            nou = new Elf(nom, edat, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
        } else if (opcioRaca == 3) {
            nou = new Duorf(nom, edat, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
        } else {
            nou = new Human(nom, edat, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
        }
        String[] tipus = { "Espasa", "Destral", "Basto", "Arc" };
        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * 4);
            String t = tipus[random];
            int dany = (int) (Math.random() * 100) + 1;
            boolean magica;
            int opcioMagica = (int) (Math.random() * 2) + 1;
            if (opcioMagica == 1) {
                magica = true;
            } else {
                magica = false;
            }
            nou.afegirArma(new Weapon(t, dany, magica));
        }
        character.add(nou);
        System.out.println("Personatge creat automàticament!");
        System.out.println(nou.toString());
    }

    public void omplirManualment(ArrayList<Character> character) {
        String nom = "";

        nom = llegirString("Escriu el nom del personatge:");

        int edat;
        do {
            edat = llegirInt("Escriu l'edat del personatge:");
            if (edat <= 0) {
                System.out.println("L'edat ha de ser positiva!");
            }
        } while (edat <= 0);

        System.out.println("Tria la raça: 1-ORC, 2-ELF, 3-NAN, 4-HUMÀ");
        int opcioRaca = llegirInt("");

        double strength, dexterity, constitution, intelligence, wisdom, charisma;
        int total;

        int restants = 60;
        do {

            System.out.println("Punts a repartir restants: " + restants);
            strength = llegirCaracteristica("Força");
            restants -= strength;
            System.out.println("Punts a repartir restants: " + restants);
            dexterity = llegirCaracteristica("Destresa");
            restants -= dexterity;
            System.out.println("Punts a repartir restants: " + restants);
            constitution = llegirCaracteristica("Constitució");
            restants -= constitution;
            System.out.println("Punts a repartir restants: " + restants);
            intelligence = llegirCaracteristica("Intel·ligència");
            restants -= intelligence;
            System.out.println("Punts a repartir restants: " + restants);
            wisdom = llegirCaracteristica("Saviesa");
            restants -= wisdom;
            System.out.println("Punts a repartir restants: " + restants);
            charisma = llegirCaracteristica("Carisma");
            total = (int) (strength + dexterity + constitution + intelligence + wisdom + charisma);
            if (total != 60) {
                System.out.println("Les característiques han de sumar 60! Has posat " + total);
                restants = 60;
            }
        } while (total != 60);

        Character nou;
        if (opcioRaca == 1) {
            nou = new Orc(nom, edat, strength, dexterity, constitution, intelligence, wisdom, charisma);
        } else if (opcioRaca == 2) {
            nou = new Elf(nom, edat, strength, dexterity, constitution, intelligence, wisdom, charisma);
        } else if (opcioRaca == 3) {
            nou = new Duorf(nom, edat, strength, dexterity, constitution, intelligence, wisdom, charisma);
        } else {
            nou = new Human(nom, edat, strength, dexterity, constitution, intelligence, wisdom, charisma);
        }
        String seguir = "";
        do {
            Weapon arma = crearArmaManual();
            nou.afegirArma(arma);
            do {
                seguir = llegirString("Vols afegir una altra arma? (si/no)");
                if (!seguir.equalsIgnoreCase("si") && !seguir.equalsIgnoreCase("no")) {
                    System.out.println("Respon només si o no!");
                }
            } while (!seguir.equalsIgnoreCase("si") && !seguir.equalsIgnoreCase("no"));
        } while (seguir.equalsIgnoreCase("si"));
        character.add(nou);
        System.out.println("Personatge creat!");

    }

    public int llegirCaracteristica(String nom) {
        int valor;
        do {
            valor = llegirInt(nom + " (5-20):");
            if (valor < 5 || valor > 20) {
                System.out.println("El valor ha de ser entre 5 i 20!");
            }
        } while (valor < 5 || valor > 20);
        return valor;
    }

    Scanner sc = new Scanner(System.in);

    public int llegirInt(String m) {
        int text = 0;
        boolean error = false;
        do {
            try {
                System.out.println(m);
                text = sc.nextInt();
                sc.nextLine();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("Error, escriu numeros");
                error = true;
                sc.next();
            } catch (Exception e) {
                System.out.println("Error desconegut, torna");
                error = true;
                sc.next();
            }
        } while (error);
        return text;
    }

    public String llegirString(String m) {
        String text = "";
        boolean error = false;
        do {
            try {
                System.out.println(m);
                text = sc.nextLine();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("Error, escriu lletres o numeros");
                error = true;
                sc.next();
            } catch (Exception e) {
                System.out.println("Error desconegut, torna");
                error = true;
                sc.next();
            }
        } while (error);
        return text;
    }

    public Character escollirPersonatge(ArrayList<Character> character, int jugador, int exclou) {
        System.out.println("\nPersonatges disponibles:");
        for (int c = 0; c < character.size(); c++) {
            System.out.println((c + 1) + "- " + character.get(c).getName());
        }
        int opcio;
        do {
            opcio = llegirInt("Jugador " + jugador + ", tria un personatge:") - 1;
            if (opcio < 0 || opcio >= character.size()) {
                System.out.println("Tria un personatge vàlid!");
            } else if (opcio == exclou) {
                System.out.println("Aquest personatge ja l'ha triat l'altre jugador!");
            }
        } while (opcio < 0 || opcio >= character.size() || opcio == exclou);
        return character.get(opcio);
    }

    public Weapon crearArmaManual() {
        int opcio;
        String type = "";
        do {
            System.out.println("Tria el tipus d'arma: 1-Espasa, 2-Destral, 3-Basto, 4-Arc");
            opcio = llegirInt("");

            switch (opcio) {
                case 1:
                    type = "Espasa";
                    break;
                case 2:
                    type = "Destral";
                    break;
                case 3:

                    type = "Basto";
                    break;
                case 4:
                    type = "Arc";
                    break;
                default:
                    System.out.println("Escull entre 1 i 4");
                    break;
            }
        } while (opcio < 1 || opcio > 4);
        int dany = 0;
        do {
            dany = llegirInt("Dany de l'arma (1-100):");
            if (dany < 1 || dany > 100) {
                System.out.println("El dany ha de ser entre 1 i 100!");
            }
        } while (dany < 1 || dany > 100);
        int magica;
        do {
            System.out.println("És màgica? 1-Sí, 2-No");
            magica = llegirInt("");
        } while (magica < 1 || magica > 2);
        return new Weapon(type, dany, magica == 1);
    }
}
