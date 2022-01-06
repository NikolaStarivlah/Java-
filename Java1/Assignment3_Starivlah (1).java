/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 * @author Nik
 */
class Intelligence {
    String name;

    public Intelligence(String name) {
        this.name = name;
    }

    public String getName() {
        return String.format("%1$-15s", name);
    }

    public void sing() {
        System.out.println(name + "HAHA");
    }

    public void play() {
        System.out.println(name + "PAPA");
    }

    @Override
    public String toString() {
        return name;
    }
}

class Human extends Intelligence {
    int life;
    boolean unplugged;

    public Human(String name) {
        super(name);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isUnplugged() {
        return unplugged;
    }

    public void setUnplugged(boolean unplugged) {
        this.unplugged = unplugged;
    }

    @Override
    public void sing() {
        System.out.println(getName() + ": HAALLE");
    }

    @Override
    public void play() {
        System.out.println(getName() + ": ZOOWEE");
    }

    @Override
    public String toString() {
        return "[Human]   Name: " + getName() + " > Life:   " + getLife() + " > Unplugged: " + isUnplugged();
    }
}

class Machine extends Intelligence {
    int energy;
    boolean exiled;

    public Machine(String name) {
        super(name);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isExiled() {
        return exiled;
    }

    public void setExiled(boolean exiled) {
        this.exiled = exiled;
    }

    @Override
    public void sing() {
        System.out.println(getName() + ": LUUJAH");

    }

    @Override
    public void play() {
        System.out.println(getName() + ": MAAMAA");

    }

    @Override
    public String toString() {
        return "[Machine] Name: " + getName() + " > Energy: " + getEnergy() + " > Exiled:    " + isExiled();
    }
}

public class Assignment3_Starivlah {
    public void init(Intelligence[] entity) {
        for (Intelligence i : entity) {
            if (i instanceof Human) {
                ((Human) i).setLife(98);
                ((Human) i).setUnplugged(true);
            } else if (i instanceof Machine) {
                ((Machine) i).setEnergy(95);
                ((Machine) i).setExiled(false);
            }
        }
    }

    public void who(Intelligence[] entity) {
        for (Intelligence i : entity) {
            System.out.println(i);
        }
    }

    public void sing(Intelligence[] entity) {
        for (Intelligence i : entity) {
            i.sing();
        }
    }

    public void play(Intelligence[] entity) {
        for (Intelligence i : entity) {
            i.play();
        }
    }

    public char choice() {
        Scanner input = new Scanner(System.in);
        char choice;
        while (true) {
            System.out.println("=====================");
            System.out.println("  1 - Girls' info");
            System.out.println("  2 - Guys' info");
            System.out.println("  3 - Girls' show");
            System.out.println("  4 - Guys' show");
            System.out.println("  Q - Exit");
            System.out.println("=====================");
            System.out.print("Your Choice: ");
            String choiceStr = input.next();
            if (choiceStr.length() != 1) {
                System.out.println("The input is invalid");
                continue;
            }
            choice = choiceStr.charAt(0);
            if (choice != '1' && choice != '2' && choice != '3' && choice != '4' && choice != 'Q') {
                System.out.println("The input is invalid");
            } else {
                break;
            }
        }

        return choice;
    }

    public static void main(String[] args) {
        Assignment3_Starivlah assignment3 = new Assignment3_Starivlah();

        // Create array
        Intelligence[] girls = new Intelligence[4];
        Intelligence[] guys = new Intelligence[4];

        girls[0] = new Human("Trinity");
        girls[1] = new Machine("The Oracle");
        girls[2] = new Human("Niobe");
        girls[3] = new Machine("Persephone");

        guys[0] = new Human("Neo");
        guys[1] = new Machine("The Architect");
        guys[2] = new Human("Morpheus");
        guys[3] = new Machine("Seraph");

        assignment3.init(girls);
        assignment3.init(guys);

        // Run loop until quit
        boolean done = false;

        while (!done) {
            char choice = assignment3.choice();
            if (choice == 'Q') {
                done = true;
            } else if (choice == '1') {
                System.out.println("==== The Girls Group ====");
                assignment3.who(girls);
            } else if (choice == '2') {
                System.out.println("==== The Guys Group ====");
                assignment3.who(guys);
            } else if (choice == '3') {
                System.out.println("Girls, let's sing a song!");
                assignment3.sing(girls);
            } else if (choice == '4') {
                System.out.println("Guys, let's make some noise!");
                assignment3.play(guys);
            }
        }

        System.out.println("Bye-Bye!");
    }
}
