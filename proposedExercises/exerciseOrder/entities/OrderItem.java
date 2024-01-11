package proposedExercises.exerciseOrder.entities;

public class OrderItem {

    private Product product;
    private Integer quantity;
    private Double price;
    
    public OrderItem() {
    }

    public OrderItem(Integer quantity, Double price, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    //getters and setters
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProduct() {
        return this.product;
    }

    public Double subTotal() {
        return price * quantity;
    }
}
