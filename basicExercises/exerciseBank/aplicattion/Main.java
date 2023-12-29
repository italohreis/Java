package basicExercises.exerciseBank.aplicattion;

import java.util.Scanner;
import basicExercises.exerciseBank.entities.Account;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("\nEnter account number --> ");
        int code = input.nextInt();
        input.nextLine();   //buffer do teclado

        System.out.print("\nEnter account holder --> ");
        String name = input.nextLine();

        System.out.print("\nIs the an initial deposit ? (y / n) --> ");
        char answer = input.next().charAt(0);

        //declarando antes de instanciar
        Account accounts;

        if (answer == 'y' || answer == 'Y') {
            System.out.print("\nEnter initial deposit value --> ");
            double initalDeposit = input.nextDouble();

            accounts = new Account(code, name, initalDeposit);
        } else {
            accounts = new Account(code, name);
        }

        System.out.println("\nAccount data: ");
        System.err.println(accounts.toString());

        System.out.print("\nEnter a deposit value --> ");
        double value = input.nextDouble();

        accounts.deposit(value);

        System.out.println("\nUpdated account data: ");
        System.out.println(accounts.toString());

        System.out.print("\nEnter a withdraw value --> ");
        double amount = input.nextDouble();

        accounts.withdraw(amount);

        System.out.println("\nUpdated account data: ");
        System.out.println(accounts.toString());

        input.close();
    }   
}
