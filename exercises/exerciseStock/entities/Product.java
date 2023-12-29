package exercises.exerciseStock.entities;

import java.util.List;

public class Product {

    private Integer code;
    private String description;
    private Double price;
    private Integer quantity;

    public Product(int code, String description, double price, int quantity) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    //getters and setters
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    //Método que adiciona a quantidade de produtos
    public void enterQuantity(int quantity) {
        this.quantity += quantity;
    }

    //Método que remove a quantidade de produtos
    public void removeQuantity(int quantity) {
        this.quantity -= quantity;
    }

    //Método que calcula o valor total do produto
    public double valueTotalProduct() {
        return price * quantity;
    }

    //Método que verifica se o código existe
    public static Product hasCode(List<Product> list, int code) {
        return list.stream().filter(x -> x.getCode() == code).findFirst().orElse(null);
        //retorna o objeto se o código existir, se não retorna null
    }

    public String toString() {
        return "\n\tCódigo: " + code + "\n\tDescrição: " + description + "\n\tPreço: " + price + "\n\tQuantidade: " + quantity;
    }
   
}
