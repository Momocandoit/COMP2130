public class DrinkApp {
    public static void main(String[] args){
        Drink[] menu = new Drink[3];
        menu[0] = new Drink("Iced Latte", 12.00);
        menu[1] = new Drink("Strawberry Matcha", 7.00);
        menu[2] = new Drink();

        System.out.println("=== Menu ===");
        for(Drink d : menu){
            System.out.println(d);
        }
        System.out.println("Drinks created: " + Drink.getCount());

        Drink top = mostExpensive(menu);
        System.out.println("Most expensive drink: " + top.getName());

        applyDiscount(menu[0], 35);
        System.out.println("After 35% discount" + menu[0]);
        applyDiscount(menu[2]);
           System.out.println("After the default discount" + menu[2]);

        menu[1].setPrice(-2);

        System.out.println("========== Codes");
        for(Drink d : menu){
            System.out.println(d.getName() + "-->" + makeCode(d.getName()));
        }
    }

    public static Drink mostExpensive(Drink[] drinks){
        Drink max = drinks[0];
        for(int i=1; i<drinks.length; i++){
            if(drinks[i].getPrice() > max.getPrice()){
                max = drinks[i];
            }
        }
        return max;
    }

    public static void applyDiscount(Drink d, double percent){
        d.setPrice(d.getPrice() * ( 1 - percent/100));
    }

    // Overloading: the same method, different parameter list
    public static void applyDiscount(Drink d){
        applyDiscount(d, 10);
    }

    public static String makeCode(String name){
        StringBuilder code = new StringBuilder();
        for (int i=0; i < name.length() && code.length()<3; i++){
            char c=name.charAt(i);
            if(Character.isLetter(c)){
                code.append(Character.toUpperCase(c));
            }
        }
        return code.toString();
    }
}
