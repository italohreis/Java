package proposedExercises.exerciseOrder.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import proposedExercises.exerciseOrder.entities.enums.OrderStatus;

public class Order {
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Date moment;
    private OrderStatus status;
    private Client client;

    List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(Date moment, OrderStatus status, Client client) {
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return this.items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public Double total() {
        Double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.subTotal();
        }
        return sum;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order moment: ");
        sb.append(moment);
        sb.append("\nOrder status: ");
        sb.append(status);
        sb.append("\nClient: ");
        sb.append(client.getName());
        sb.append(" (");
        sb.append(sdf.format(client.getBirthDate()));
        sb.append(") - ");
        sb.append(client.getEmail());
        sb.append("\nOrder items: ");
        for (OrderItem item : items) {
            sb.append("\n");
            sb.append(item.getProduct().getName());
            sb.append(", $");
            sb.append(item.getProduct().getPrice());
            sb.append(", Quantity: ");
            sb.append(item.getQuantity());
            sb.append(", Subtotal: $");
            sb.append(item.subTotal());
        }
        sb.append("\nTotal price: $");
        sb.append(total());
        return sb.toString();
    }
    
}
