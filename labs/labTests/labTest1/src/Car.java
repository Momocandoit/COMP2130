public class Car {
    // Fields
    private static int count;
    private final int id;
    private String model;
    private double price;

    // Constructor
    public Car(String model, double price){
        count++;
        this.id = count;
        setModel(model);
        setPrice(price);
    }

    // Methods
    public void setModel(model){
        this.model = model;
    }

    public void setPrice(price){
        this.price = price;
    }

    public void getModel(){
        return;
    }

    public void getPrice(){
        return;
    }

    public void getCount(){
        return;
    }

    public double getFinalPrice(){
        // some code to calculate
    }

    // Override
    @Override
    public String toString(int count, String model, double price) {
        System.out.println("#" + count + model + "-" + "$" + price);
    }
}
