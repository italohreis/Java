package basicExercises.exerciseBank.entities;

public class Account {
    
    public static final double TAX = 5.0;

    private int code;
    private String name;
    private double balance;

    //constructor default
    public Account() {
    }

    //constructor
    public Account(int code, String name, double initalDeposit) {
        this.code = code;
        this.name = name;
        deposit(initalDeposit);
    }

    //consctructor
    public Account(int code, String name) {
        this.code = code;
        this.name = name;
    }

    //methods
    public void deposit(double amount) {
        balance += amount;
    }

    //methods
    public void withdraw(double amount) {
        balance -= amount + TAX;
    }

    //getters and setters
    public int getCode() {
        return code;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Account " + code + ", Holder: " + name + ", Balance: $ " + balance; 
    }

}
