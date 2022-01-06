/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeyourmind;

/*
 * @author Nik
 */

import java.util.Scanner;

public class JumpingTraining {

    public static final double theDistance = 100.0;
    public static final double giveOrTake = 5.0;

    public static boolean run() {
        System.out.println("== Welcome to the Jump Program");
        double minDistance = theDistance - giveOrTake;
        double maxDistance = theDistance + giveOrTake;
        System.out.printf("== Your distance should be between %.1f and %.1f\n", minDistance, maxDistance);
        System.out.println("== Tell me your velocity and angle:");
        Scanner input = new Scanner(System.in);
        int velocity = input.nextInt();
        int angle = input.nextInt();

        double distance = JumpingCalculator.distance(velocity, angle);
        double time = JumpingCalculator.timeOfFlight(velocity, angle);
        double height = JumpingCalculator.height(velocity, angle);

        System.out.printf("== Your Jumping distance is: %.2f\n", distance);
        System.out.printf("== Your Jumping time is: %.2f\n", time);
        System.out.printf("== Your Jumping height is: %.2f\n", height);
        if (distance <= maxDistance && distance >= minDistance) {
            System.out.println("== Pass :) ==");
            return true;
        } else {
            System.out.println("== Fail :( ==");
            System.out.println("Try again (0 to Exit / 1 to Continue)?");
            int choice = input.nextInt();
            if (choice == 1) {
                return run();
            } else {
                return false;
            }
        }
    }
}
