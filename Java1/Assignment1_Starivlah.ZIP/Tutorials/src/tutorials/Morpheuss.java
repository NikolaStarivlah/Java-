/*
 Nikola Starivlah
991570332
2020-01-30
The purpose of the program is to get morpheus to tell neo
that there are two choices and ten they ask him to choose
 */
package tutorials;

/**
 *
 * @author Nik
 */
import java.util.Scanner;//imports keybaord scanner from java library

public class Morpheuss {//morpheuss class

    //Use setter methods to set size and status for each of the 
    //two pills to be 1.414 and false
    public static void redPill(String efficacy) {//method for efficacy in the
        //pill desciption 
        System.out.println("Tracing for unplugging this human");
    }

    public static void bluePill(String efficacy) {//method for efficacy in the
        //pill description
        System.out.println("Sending back to the matrix to sleep");
    }

    
    public static void main(String[] args) {//allows for logcal operators and
        //scripting

        PILL redPill = new PILL("red", 0);//uses constructor to create two pill
        //objects 
        PILL bluePill = new PILL("blue", 0);

        //setter methodssets blue pill and red 
        //pill sizes to 1.414 and status to false
        redPill.setsize(1.414);
        bluePill.setsize(1.414);

        redPill.setstatus(false);
        bluePill.setstatus(false);

        int choice;//choice variable to store keyboard input
        Scanner input = new Scanner(System.in);//scanner for keyboard input

        System.out.println("Morpheus: Unfortunetly, no one can be...told"
                + " what the matrix is. You have to see it for yourself. This "
                + " is  your last chance. After this, there is no turning back"
                + " I have two pills here.");

        //Prints info about info about pill one. it uses the pill constructor and
        //pritns creates a efficacy for the method when called
        System.out.println("==Pill one==");
        redPill.printInfo();
        redPill.setefficacy("Tracing for unplugging this human\n");
        System.out.printf("Efficacy: " + redPill.getefficacy());

        //Prints info about info about pill one. it uses the pill constructor and
        //pritns creates a efficacy for the method when called
        System.out.println("==Pill two==");
        bluePill.printInfo();
        bluePill.setefficacy("Sending back to the matrix to sleep");
        System.out.printf("Efficacy: " + bluePill.getefficacy());

        //prompt message for opening 
        System.out.println("");
        System.out.println("You take the blue pill (press 1), the story ends\t");
        System.out.println("you wake up in your bed and belive whatever you want to belive.\t");
        System.out.println("You take the red pill (press 2, you stay in Wonderland, and I\t");
        System.out.println("show you how deep the rabbit hole goes\t");
        //print info for red pill
        System.out.println("Now tell me Neo, (1) blue or (2) red");
        do {//do while loop

            //System.out.println("Now tell me Neo, (1) blue or (2) red");
            choice = input.nextInt();//prompt for user input

            //////////////////////////////////////////////////////////////////
            //Logic for choice one, following that the choices could be greater 
            //than 2 or equal to 2 
            //after that if the choice is one again then it will continue a 
            //another set of else ifs untill a final conclusion is reached 
            if (choice == 1) {
                System.out.println("No Neo, you gotta try harder");
                System.out.println("Now tell me Neo, (1) blue or (2) red");
                choice = 0;//sets choice to zero for do while loop

            } else if (choice > 2) {
                System.out.println("Come on Neo, I only have two pills");
                System.out.println("Now tell me Neo, (1) blue or (2) red");
                choice = 0;
            } //get an else
            else if (choice == 2) {

                System.out.println("Morpheus: Are you sure, (1) Yes or (2) No?");
                System.out.println("Remember, all I'm offering is the truth. Nothing more.");
                System.out.println("");
                choice = 0;
                int choice2 = input.nextInt();
                
                

                if (choice2 == 1) {
                    System.out.println("Follow me");
                    redPill.setstatus(true);
                    return;
                } else if (choice2 == 2) {
                    System.out.println("Ok think about it and then decide");
                    System.out.println("Now tell me Neo, (1) blue or (2) red");

                } else if (choice2 > 2){
                    System.out.println("Come on Neo, I only have two pills");
                System.out.println("Now tell me Neo, (1) blue or (2) red");
                choice = 0;
                }

            } else if (choice == 2) {
                System.out.println("Ok, think about it then decide");
                System.out.println("Now tell me Neo, (1) blue or (2) red");
                System.out.println("");

            } 

        } while (redPill.isTaken() == false);//if the status of the red pill
        //is not taken then continue running all if and else if statements

    }
}
