package models;

public class Parameter {

    private final int source;
    private final int dest;
    private final int cost;
    private int amount;


    public Parameter(int source, int dest, int cost, int amount) {
        this.source = source;
        this.dest = dest;
        this.cost = cost;
        this.amount = amount;
    }

    public Parameter(int source, int dest, int cost) {
        this.source = source;
        this.dest = dest;
        this.cost = cost;
    }

    public int getSource() {
        return source;
    }

    public int getDest() {
        return dest;
    }

    public int getCost() {
        return cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
