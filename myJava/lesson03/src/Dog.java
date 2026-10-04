public class Dog {
    /*
     * 復習('o')
     * Class
     * -- a blueprint that defines what kind of data and behavior an object will have.
     * -- fields, methodsを持つ
     *
     * Object
     * -- an instance created from that class.
     * -- different objects created from the same class can store different values
     *
     * Constructor
     * -- Dog dog1 = new Dog();のDog()の部分。
     * -- a special part of a class that runs when a new object is created.
     * -- "this" means the current object
     *
     * */

    // my very first class
        // Fields
        String dogName;
        int dogAge;

        // Methods
        public void bark(){
            System.out.println("Woof Woof");
        }

        // Constructors
        public Dog(String name, int age){
            this.dogName = name;
            this.dogAge = age;
        }

}
