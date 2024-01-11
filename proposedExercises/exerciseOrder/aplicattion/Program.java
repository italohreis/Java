package proposedExercises.exerciseOrder.aplicattion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import proposedExercises.exerciseOrder.entities.Product;
import proposedExercises.exerciseOrder.entities.Client;
import proposedExercises.exerciseOrder.entities.Order;
import proposedExercises.exerciseOrder.entities.OrderItem;
import proposedExercises.exerciseOrder.entities.enums.OrderStatus;


public class Program {
    public static void main(String[] args) throws ParseException {

        Scanner input = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter client data:");
        System.out.print("Name --> ");
        String name = input.nextLine();

        System.out.print("Email --> ");
        String email = input.nextLine();

        System.out.print("Birth date (DD/MM/YYYY) --> ");
        Date birthDate = sdf.parse(input.next());

        System.out.println("Enter order data:");
        System.out.print("Status --> ");
        OrderStatus status = OrderStatus.valueOf(input.next());
        
        Order order = new Order(new Date(), status, new Client(name, email, birthDate));

        System.out.print("How many items to this order? --> ");
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter #" + (i+1) + " item data:");
            System.out.print("Product name --> ");
            input.nextLine();
            String productName = input.nextLine();

            System.out.print("Product price --> ");
            double productPrice = input.nextDouble();

            System.out.print("Quantity --> ");
            int quantity = input.nextInt();
         
            OrderItem orderItem = new OrderItem(quantity, productPrice, new Product(productName, productPrice));

            order.addItem(orderItem); 
        }

        System.out.println("\nORDER SUMMARY:\n");

        System.out.println(order);

        input.close();
    }
    
}
