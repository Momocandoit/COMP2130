/*
* Lab 3: Drink.java: 飲み物のルール(どんな情報を持っているか、何ができるか)を書く
*                    飲み物には名前と価格があり、価格を変更するときは符の数を認めない、表示の方法はこう、みたいな
*        DrinkApp: その飲み物を使ってプログラムを動かす。そのルールに従って実際の飲み物を作る。
*                  mainがあって、実際に飲み物を何個か作ってメニューに入れ、表示、割引とかする
* */

// classは情報とできる操作を決める設計図
// classを元に作った一つのものをobjectという　(new Drink("Iced Latte", 12.00))
// class Drinkは飲み物の情報や操作を書く場所

public class Drink {
    // -------------- Fields ----------------
    // field: Variables inside a class that store an object's data/保存する情報
    /*
    * private: Drink内でだけアクセスできる
    * public: 別のクラスからもアクセスできる
    * static: オブジェクトごとではなく、クラス全体で共有する
    * final: prevents reassignment after initialization.
    * */
    private static int count = 0;
    private final int id;
    private String name;
    private double price;


    // -------------- 2 constructors ------------
    // constructor: 作る時の初期設定。classと同じ名前でreturnがないのが特徴。飲み物の名前、価格、IDを設定する。
    // 1) 名前を価格も指定されなかったら水 1ドルにする、という設定
    // このコンストラクターは2個目のコンストラクターを呼んでいる (thisがある)
    public Drink(){
        this("Water", 1.00);
    }
    // 2) 指定された名前を価格を受け取って、飲み物の情報を設定する処理
    public Drink(String name, double price){
        count++;
        this.id = count;    // 今の総数をその飲み物のIDにする
        this.name = name;
        setPrice(price);
    }


    // ------------- Methods --------------
    // method: performs an operation when called/必要な時にその名前で呼び出して行う処理。
    // 保存してある名前を取り出したり(drinks[i].getPrice())、価格を変更したりする。
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        if (price < 0) {
            System.out.println("Error: price cannot be negative. Price set to 0");
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public static int getCount(){
        return count;
    }

    // ------ Override ------------
    // Overriding: provides your own version of a method inherited from another class
    // すでに用意されているmethodに、自分の処理を与える
    // javaには元々toString()というobjectを文字列にする基本のmethodがある。
    // Drink classもそれを受け継いでいるが、基本の処理では飲み物の名前や価格を使った表示にはならない？？
    // meaning こちらが指定しないと、javaは飲み物のどんな情報をどんな形で表示したいのかわからない、ということ
    // そこでDrink.javaに自分たちの表示方法を書く。
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(id)
                .append(" ").append(name)
                .append(" - $").append(String.format("%.2f", price));
        return sb.toString();
    }
}
