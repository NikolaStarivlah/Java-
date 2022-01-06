package freeyourmind;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Nik
 */
public class Trainee {
    private int id;
    private String name;
    private boolean jumpTrain;
    private static int numOfTrainee;

    public Trainee(int id, String name) {
        this.id = id;
        this.name = name;
        numOfTrainee++;
    }

    public String getName() {
        return name;
    }

    public static int getNumOfTrainee() {
        return numOfTrainee;
    }

    public void setJumpTrain(boolean jumpTrain) {
        this.jumpTrain = jumpTrain;
    }

    public void printInfo() {
        System.out.println("==================");
        System.out.printf("ID: %d | Name: %s\n", id, name);
        System.out.printf("Jump Train: %s\n", jumpTrain ? "Pass" : "Fail");
        System.out.println("==================");
    }
}
