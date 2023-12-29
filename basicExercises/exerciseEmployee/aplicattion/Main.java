package aplicattion;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import entities.Employee;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        List<Employee> employess = new ArrayList<>();

        System.out.print("How many employees will be registered ? --> ");
        int number = input.nextInt();
        int id;
        for (int i = 0; i < number; i++) {
            System.out.println("\nEmployee #" + (i+1));

            System.out.print("\nId --> ");
            id = input.nextInt();

            while(hasId(employess, id)) {
                System.out.print("\nId already taken! Try again: ");
                id = input.nextInt();
            }
               
            System.out.print("\nName --> ");
            input.nextLine();
            String name = input.nextLine();

            System.out.print("\nSalary --> ");
            Double salary = input.nextDouble();

            Employee employee = new Employee(id, name, salary);

            employess.add(employee);
        }
        
        System.out.print("\nEnter the employee id that will have salary increase --> ");
        int code = input.nextInt();
            
        final int codeFinal = code;

        Employee employes = employess.stream().filter(x -> x.getId() == codeFinal).findFirst().orElse(null);

        if (employes != null) {
            System.out.print("\nEnter the percentage: ");
            Double percentage = input.nextDouble();

            employes.increaseSalary(percentage);
        } else {
            System.out.println("This id does not exist.");
        }

        System.out.println("\nList of employees: ");
        for (Employee employee : employess) {
            System.out.println(employee.toString());
        }

        input.close();
    }

    public static boolean hasId(List<Employee> list, int id) {
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }
    
}
