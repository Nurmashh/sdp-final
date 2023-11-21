
public class Service {
    protected String name;
    protected double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Service() {
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }
}