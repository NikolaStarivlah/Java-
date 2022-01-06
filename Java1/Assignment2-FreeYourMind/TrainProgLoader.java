/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeyourmind;

import java.util.Scanner;

/**
 * @author Nik
 */
public class TrainProgLoader {
    public static final int SIZE = 3;

    public static void main(String[] args) {
        Trainee[] arr = new Trainee[SIZE];
        arr[0] = new Trainee(1, "Neo");
        arr[1] = new Trainee(2, "Morpheus");
        arr[2] = new Trainee(3, "Trinity");

        int currentTrainee = currentTrainee(arr);
        if (currentTrainee != 0) {
            Trainee trainee = arr[currentTrainee - 1];
            System.out.printf(">> Hello %s\n", trainee.getName());
            trainee.setJumpTrain(JumpingTraining.run());
            trainee.printInfo();
        }
        System.out.println(">> Take Care <<");
    }

    private static int currentTrainee(Trainee[] arr) {
        int numOfTrainee = Trainee.getNumOfTrainee();
        System.out.printf(">> Currently we have: %d trainees. They are:\n", numOfTrainee);
        for (int i = 0; i < numOfTrainee; i++) {
            arr[i].printInfo();
        }

        Scanner input = new Scanner(System.in);
        boolean done = false;
        int choice = 0;

        while (!done) {
            System.out.println(">> Let me know who you are (1, 2, or 3, 0 to Exit):");
            choice = input.nextInt();
            if (choice >= 0 && choice <= 3) {
                done = true;
            } else {
                System.out.println(">> Doesn't exist! <<");
            }
        }
        return choice;
    }
}
