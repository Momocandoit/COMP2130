public class wk5_activity1{
    public static void main(String[] args){
        System.out.println("A");
        try{
            System.out.println("B");
            int[] list = new int[3];
            list[3] = 7;
            System.out.println("C");
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("D: " + ex.getMessage());
        }
        System.out.println("E");
    }
}