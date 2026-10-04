//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    // creating an array
    ArrayList<Car> Cars = new ArrayList<Car>();
    cars.add(new Car ("Carolla", 45.00));
    cars.add(new LuxuryCar("MBW X5", 90.00, "insurance", 20.00));
    cars.add(new Car ("Civic", 40.00));


    // printing with a loop
    System.out.println("=== Cars ===");
    for(Car c : Cars){
        System.out.println(c);
    }

    // count the number of cars
    System.out.println("Cars rented: " + aaa);

    // count the number of luxury cars
    System.out.println("Luxury Cars: " + bbb);

    // calculate the total
    System.out.println("Total: $" + ccc);
}
