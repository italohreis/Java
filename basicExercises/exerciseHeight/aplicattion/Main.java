package basicExercises.exerciseHeight.aplicattion;

import java.util.Scanner;
import basicExercises.exerciseHeight.entities.People;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("\nhow many peoples will be entered --> ");
        int number = input.nextInt();

        People[] peoples = new People[number];
        double medium = 0;

        for(int i = 0; i < peoples.length; i++){
            System.out.printf("%n People %d %n", i + 1);

            System.out.print("\n\tName --> ");
            input.nextLine();   //clean buffer
            String name = input.nextLine();

            System.out.print("\n\tAge --> ");
            int age = input.nextInt();

            System.out.print("\n\tHeight --> ");
            double height = input.nextDouble();

            medium += height;

            peoples[i] = new People(name, age, height);
        }
    
        //calling function to report
        report(peoples, medium);

        input.close();
    }

    public static void report(People[] peoples, double medium) {

        System.out.printf("%nAverage height = %.2f %n", medium / peoples.length);

        System.out.println("\nName of people under 16 years old:\n");
        int cPeopleUnder16 = 0;
        for(int i = 0; i < peoples.length; i++) {
            if (peoples[i].getAge() < 16) {
                System.out.println(peoples[i].getName());
                cPeopleUnder16++;
            }
        }
        
        System.out.println("\n Percentage of people under 16 years old = " + cPeopleUnder16 / peoples.length * 100 + "%\n");
    }
    
}
