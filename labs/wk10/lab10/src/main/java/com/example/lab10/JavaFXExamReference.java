/*
 * COMP2130 試験用コードリファレンス / Exam Code Reference
 *
 * GitHub 上で ⌘F を押し、下のメソッド名かキーワードを検索してください。
 * JavaFX の例は、該当メソッドの中身を start(Stage stage) に貼り、必要な import をファイルの上部に追加します。
 * 同じ名前の変数がある場合は、宣言を重ねず、必要な処理だけを追加します。
 * Use Ctrl+F or Cmd+F on GitHub to find a method name or keyword below.
 * For a JavaFX example, paste the method body into start(Stage stage) and add the relevant imports at the top.
 * If a variable with the same name already exists, reuse it and paste only the lines you need.
 *
 *
 * 次に何を書くか迷ったとき / WHEN YOU'RE UNSURE WHAT TO WRITE NEXT
 * 問題文を「最初に画面にあるもの → 操作 → 操作後に変わるもの」に分けます。
 * まず表示だけを作って実行し、その後で操作を１つずつ追加します。
 * Break the prompt into what is visible at launch, what the user does, and what changes.
 * Get the initial screen working, then add and test one interaction at a time.
 * 各メソッドの直前に、目的別に書く順番と確認方法があります。
 * The comment above each example shows the order to write things and what to test.
 *
 * Week 9
 *   layoutExample: Stage, Scene, BorderPane, HBox, VBox, StackPane
 *   weatherExample: Pane, Rectangle, Circle, bind, ImageView, Font
 * Week 10
 *   controlsExample: Button, TextField, TextArea, CheckBox, Enter, toggle
 *   mediaExample: MediaPlayer, MediaView, Play, Pause, Rewind, Slider, Mute, timestamp
 * Week 11
 *   eventsExample: lambda, setOnAction, setOnKeyPressed, setOnMouseClicked, addListener
 *   StartHandler: interface, implements, EventHandler<ActionEvent>, handle, @Override
 * Earlier Java
 *   orderExample, Item, DeliveryItem: constructor, static, super, instanceof, Comparable
 *   sumArray, printInteger, printFile: array, for-each, try/catch, readAllLines
 *
 * 授業の対応例 / Related course examples
 * Week 9 WeatherCard: https://github.com/sojoudian/COMP2130_Fall_2026/tree/master/Week09
 * Week 10 VideoPlayer: https://github.com/sojoudian/COMP2130_Fall_2026/tree/master/Week10
 * Week 11 LambdaHandlerDemo: https://github.com/sojoudian/COMP2130_Fall_2026/blob/master/Week11/Lecture/src/main/java/com/example/week11/LambdaHandlerDemo.java
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

public class JavaFXExamReference extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // ファイル全体で試すなら、ここで呼ぶメソッドを１つ選びます。
        // To try the full file, choose one example to call here.
        layoutExample(stage);
    }

    /*
     * 画面を出したい / BUILD A WINDOW
     * 1. 表示する Label、Button、図形などを new で作ります。
     *    Create the labels, buttons, or shapes you need.
     * 2. 置き方でレイアウトを選び、部品を入れます。
     *    Choose a layout based on placement, then add the nodes.
     *    上下中央 / Top, bottom, center → BorderPane + setTop / setBottom / setCenter
     *    横一列 / One row → HBox   縦一列 / One column → VBox
     *    重ねる / Overlap → StackPane   自分で座標を決める / Manual positions → Pane
     *    同じ位置にボタンを２つ置くなら、HBox にまとめてから setBottom します。
     *    For two buttons at the bottom, put both in an HBox and pass it to setBottom.
     * 3. new Scene(root, width, height) → stage.setScene(scene) → stage.show()。
     *    Build the Scene from root, attach it to the Stage, then show the Stage.
     * 確認 / Check: 部品が見えないなら、その部品を root 側のレイアウトに入れたか確認します。
     * If a node is missing, check that it was added to a layout attached to the scene.
     */
    // Week 9 | Stage, Scene, BorderPane, HBox, VBox, StackPane
    private void layoutExample(Stage stage) {
        Label status = new Label("Ready");
        Button start = new Button("Start");
        Button reset = new Button("Reset");

        BorderPane root = new BorderPane();

        // VBox は縦、HBox は横に並べます。10 は部品同士の間隔です。
        // VBox arranges children vertically and HBox horizontally. The gap is 10.
        VBox heading = new VBox(10, new Label("Status Card"), new Label("Example"));
        HBox buttons = new HBox(10, start, reset);
        buttons.setPadding(new Insets(10));

        // StackPane は後に書いた部品を手前に重ねます。
        // StackPane places the later child in front of the earlier one.
        Circle backdrop = new Circle(45, Color.LIGHTSKYBLUE);
        StackPane badge = new StackPane(backdrop, status);

        root.setTop(heading);
        root.setCenter(badge);
        root.setBottom(buttons);

        // Stage はウィンドウです。Scene は root から画面の中身を作ります。
        // Stage is the window. Scene holds the content rooted at root.
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Layout example");
        stage.setScene(scene);
        stage.show();
    }

    /*
     * リサイズに追従させたい / FOLLOW A RESIZE
     * 1. Pane と図形を作り、図形の何を変えたいか決めます。
     *    Create a Pane and a shape, then decide which shape property should change.
     * 2. 背景を広げるなら widthProperty と heightProperty を Pane の幅と高さに bind します。
     *    To stretch a background, bind its width and height to the Pane's width and height.
     *    円を中央に置き続けるなら centerX と centerY を幅と高さの半分に bind します。
     *    To keep a circle centered, bind centerX and centerY to half the width and height.
     * 3. 背景、円の順に Pane に追加し、その Pane を root に入れます。
     *    Add the background first and the circle second, then put the Pane in root.
     * 確認 / Check: ウィンドウを広げて、背景が伸びても円が中央にあるか見ます。
     * Enlarge the window and check that the background grows and the circle stays centered.
     *
     * 画像を置きたい / SHOW AN IMAGE
     * ファイルを resources に置く → getResource → new Image → new ImageView → root に配置。
     * Put the file in resources, find its URL with getResource, create an Image and
     * an ImageView, then add the ImageView to your layout.
     * getResource が null なら、ファイル名と resources からのパスを確認します。
     * If getResource returns null, check the filename and its path from the resource root.
     */
    // Week 9 | Rectangle, Circle, property binding, ImageView, Font
    private void weatherExample(Stage stage) {
        Pane sky = new Pane();
        Rectangle background = new Rectangle();
        background.setFill(Color.LIGHTSKYBLUE);

        // bind の左側が、右側の値に合わせて自動更新されます。
        // The property on the left of bind follows the value on the right.
        background.widthProperty().bind(sky.widthProperty());
        background.heightProperty().bind(sky.heightProperty());

        Circle sun = new Circle(40, Color.GOLD);
        sun.centerXProperty().bind(sky.widthProperty().divide(2));
        sun.centerYProperty().bind(sky.heightProperty().divide(2));
        sky.getChildren().addAll(background, sun);

        Label city = new Label("Toronto");
        city.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        HBox forecast = new HBox(10, new Label("Mon 10°C"), new Label("Tue 12°C"));
        forecast.setPadding(new Insets(10));

        // "/cloud.png" は src/main/resources/cloud.png に対応します。
        // "/cloud.png" resolves from the resource root, src/main/resources.
        URL cloudUrl = Objects.requireNonNull(
                getClass().getResource("/cloud.png"), "Missing cloud.png");
        ImageView cloud = new ImageView(new Image(cloudUrl.toExternalForm()));
        cloud.setFitWidth(80);
        cloud.setPreserveRatio(true);

        BorderPane root = new BorderPane();
        root.setTop(city);
        root.setLeft(cloud);
        root.setCenter(sky);
        root.setBottom(forecast);

        stage.setTitle("Weather example");
        stage.setScene(new Scene(root, 480, 300));
        stage.show();
    }

    /*
     * 入力を反映したい / USE INPUT
     * 1. 入力元と出力先を先に作ります。例では TextField と Label / TextArea です。
     *    Create the input and output controls first. Here they are a TextField and a Label / TextArea.
     * 2. きっかけを選びます。Enter なら input.setOnAction、ボタンなら button.setOnAction です。
     *    Choose the trigger. For Enter in the field, use input.setOnAction. For a click, use button.setOnAction.
     * 3. 処理の中で getText() → setText() または appendText() → 必要なら clear() の順に書きます。
     *    Inside the handler, read with getText(), update with setText() or appendText(),
     *    then call clear() if the task asks you to empty the field.
     * 確認 / Check: 文字を入れて Enter を押し、表示と履歴が更新されるか見ます。
     * Type something and press Enter to check the display and history.
     *
     * ON/OFF を切り替えたい / TOGGLE A STATE
     * チェックの有無なら isSelected() を読みます。この例の Start/Stop では、
     * クリック処理の中で現在のボタン文字を調べ、if と else で次の表示に変えます。
     * For a checkbox, read isSelected(). In this Start/Stop example, check the current
     * button text inside the handler and use if/else to choose what to display next.
     * 確認 / Check: ２回押して最初の表示に戻るか見ます。
     * Click twice and check that the initial display returns.
     */
    // Week 10 | TextField, TextArea, CheckBox, Enter, toggle
    private void controlsExample(Stage stage) {
        Label status = new Label("Ready");
        TextField input = new TextField();
        input.setPromptText("Type a note and press Enter");

        TextArea history = new TextArea();
        history.setEditable(false);

        // 入力欄で Enter を押したとき、文字を読んで履歴に追加します。
        // Pressing Enter in the input field reads its text and appends it to the history.
        input.setOnAction(event -> {
            String text = input.getText();
            status.setText(text);
            history.appendText(text + System.lineSeparator());
            input.clear();
        });

        CheckBox enabled = new CheckBox("Enabled");
        enabled.setOnAction(event -> {
            if (enabled.isSelected()) {
                status.setText("Enabled");
            } else {
                status.setText("Disabled");
            }
        });

        Button toggle = new Button("Start");
        toggle.setOnAction(event -> {
            if (toggle.getText().equals("Start")) {
                toggle.setText("Stop");
                status.setText("Running");
            } else {
                toggle.setText("Start");
                status.setText("Ready");
            }
        });

        VBox root = new VBox(10, status, input, history, enabled, toggle);
        root.setPadding(new Insets(10));
        stage.setTitle("Controls example");
        stage.setScene(new Scene(root, 420, 330));
        stage.show();
    }

    /*
     * 動画を操作したい / CONTROL VIDEO PLAYBACK
     * 1. 動画の URL → Media → MediaPlayer → MediaView の順に作り、View をレイアウトに入れます。
     *    Create the resource URL, Media, MediaPlayer, and MediaView in that order, then place the view.
     * 2. 再生・一時停止なら、ボタンに setOnAction を登録し、player.play() / pause() を呼びます。
     *    For play/pause, register a button action that calls player.play() or player.pause().
     *    頭出しなら seek(Duration.ZERO)、現在の秒数なら getCurrentTime().toSeconds() を使います。
     *    Use seek(Duration.ZERO) to rewind and getCurrentTime().toSeconds() to read the timestamp.
     * 3. 音量なら、Slider を作って player.volumeProperty() を slider の値 ÷ 100 に bind します。
     *    For volume, create a Slider and bind player.volumeProperty() to its value divided by 100.
     *    ミュートなら、player.muteProperty() を CheckBox の selectedProperty() に bind します。
     *    For mute, bind player.muteProperty() to the CheckBox's selectedProperty().
     * 確認 / Check: Play → Pause → Rewind を試し、スライダーの 50 が音量 0.5 に対応するか確認します。
     * Try Play, Pause, and Rewind, and check that 50 on the slider maps to 0.5 in the player.
     */
    // Week 10 | Media, MediaPlayer, MediaView, Slider, Mute, Rewind
    private void mediaExample(Stage stage) {
        // 動画は src/main/resources/clip.mp4 に置きます。
        // Put clip.mp4 in src/main/resources before running this example.
        URL clipUrl = Objects.requireNonNull(
                getClass().getResource("/clip.mp4"), "Missing clip.mp4");
        Media media = new Media(clipUrl.toExternalForm());
        MediaPlayer player = new MediaPlayer(media);
        MediaView view = new MediaView(player);
        view.setFitWidth(450);
        view.setPreserveRatio(true);

        Button playPause = new Button("Play");
        playPause.setOnAction(event -> {
            if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                player.pause();
            } else {
                player.play();
            }
        });
        player.setOnPlaying(() -> playPause.setText("Pause"));
        player.setOnPaused(() -> playPause.setText("Play"));
        player.setOnStopped(() -> playPause.setText("Play"));
        player.setOnEndOfMedia(player::stop);

        Button rewind = new Button("Rewind");
        rewind.setOnAction(event -> player.seek(Duration.ZERO));

        // Slider の最小値、最大値、初期値です。音量は 0.0〜1.0 に変換します。
        // These are the slider's minimum, maximum, and initial values. Convert volume to 0.0–1.0.
        Slider volume = new Slider(0, 100, 50);
        player.volumeProperty().bind(volume.valueProperty().divide(100));
        CheckBox mute = new CheckBox("Mute");
        player.muteProperty().bind(mute.selectedProperty());

        TextField note = new TextField();
        note.setPromptText("Note at current time");
        TextArea notes = new TextArea();
        notes.setEditable(false);
        notes.setPrefColumnCount(16);
        note.setOnAction(event -> {
            double seconds = player.getCurrentTime().toSeconds();
            notes.appendText(String.format("%.1f s: %s%n", seconds, note.getText()));
            note.clear();
        });

        HBox buttons = new HBox(10, playPause, rewind, new Label("Volume"), volume, mute);
        BorderPane root = new BorderPane();
        root.setCenter(view);
        root.setRight(new VBox(10, note, notes));
        root.setBottom(buttons);

        stage.setOnHidden(event -> player.dispose());
        stage.setTitle("Media example");
        stage.setScene(new Scene(root, 720, 420));
        stage.show();
    }

    /*
     * 何かが起きたら動かしたい / CHOOSE AN EVENT
     * 1. 問題文から、きっかけと変更する部品を見つけます。例では「R キーで円の色を戻す」です。
     *    Identify the trigger and what changes. In this example, R resets the circle's color.
     * 2. きっかけに合うメソッドを選び、変更する部品を作った後に登録します。
     *    Choose the method for the trigger and register it after creating the nodes it updates.
     *    ボタン / Button action → button.setOnAction(...)
     *    キー / Key press → scene.setOnKeyPressed(...) and event.getCode()
     *    図形のクリック / Click a shape → shape.setOnMouseClicked(...)
     *    スライダーの値が変わる / Slider value changes → slider.valueProperty().addListener(...)
     * 3. ラムダ式の中に、文字・色・サイズなどを変更する行を書きます。
     *    Put the lines that update text, color, or size inside the lambda.
     * 確認 / Check: 操作を１つずつ試します。キー入力ではアプリのウィンドウを選択します。
     * Test one interaction at a time. For key input, focus the app window.
     */
    // Week 11 | Button actions, Keyboard, Mouse, ChangeListener
    private void eventsExample(Stage stage) {
        Label status = new Label("Ready");
        Button start = new Button("Start");
        Circle dot = new Circle(35, Color.GOLD);
        Slider size = new Slider(10, 55, 35);

        // setOnAction にクリック後の処理を渡します。
        // Pass the code to run on a button action to setOnAction.
        start.setOnAction(event -> status.setText("Running"));

        dot.setOnMouseClicked(event -> dot.setFill(Color.ORANGE));

        size.valueProperty().addListener((observable, oldValue, newValue) -> {
            dot.setRadius(newValue.doubleValue());
        });

        VBox root = new VBox(12, status, start, dot, size);
        root.setPadding(new Insets(12));
        Scene scene = new Scene(root, 400, 300);

        // Scene を作成した後にキーイベントを登録します。
        // Register the key handler after creating the Scene.
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R) {
                dot.setFill(Color.GOLD);
                status.setText("Ready");
            }
        });

        stage.setTitle("Events example");
        stage.setScene(scene);
        stage.show();
    }

    /*
     * Week 11 | interface, implements, EventHandler<ActionEvent>, @Override
     * interface を使うよう指定されたとき / WHEN THE PROMPT REQUIRES AN INTERFACE
     * 1. start() の外に、EventHandler<ActionEvent> を implements するクラスを書きます。
     *    Outside start(), write a class that implements EventHandler<ActionEvent>.
     * 2. @Override の付いた handle(ActionEvent event) に、押した後の処理を書きます。
     *    Write the action in handle(ActionEvent event), with @Override above the method.
     * 3. ボタンを作った後、setOnAction に new StartHandler(status) を渡して登録します。
     *    After creating the button, pass new StartHandler(status) to setOnAction.
     *
     * eventsExample のラムダ式の行を次の行に置き換えると、同じ動作になります。
     * Replace the lambda registration in eventsExample with this line for the same behavior.
     *
     * start.setOnAction(new StartHandler(status));
     *
     * StartHandler は start() の外側、アプリのクラスの内側に貼ります。
     * Paste StartHandler inside the app class but outside start().
     */
    private static class StartHandler implements EventHandler<ActionEvent> {
        private final Label status;

        StartHandler(Label status) {
            this.status = status;
        }

        // ボタンのアクションが発生したとき、JavaFX がこのメソッドを呼びます。
        // JavaFX calls this method when the button fires an action.
        @Override
        public void handle(ActionEvent event) {
            status.setText("Running");
        }
    }

    /*
     * 複数のオブジェクトを作って集計したい / CREATE OBJECTS AND CALCULATE TOTALS
     * 1. 問題文にある共通データを親クラスのフィールドにして、コンストラクタで初期化します。
     *    Put shared data in the base class and initialize its fields in the constructor.
     * 2. 子クラスで super(...) を呼んで、子クラス固有の計算を @Override します。
     *    Call super(...) from the subclass constructor and override the calculation that differs.
     * 3. ArrayList に追加 → for-each で取り出す → 合計や件数を更新 → ループの後で表示します。
     *    Add items to an ArrayList, loop to update totals and counts, then print after the loop.
     * 確認 / Check: 親と子を１つずつ入れ、子の追加料金が合計に入るか手計算と比べます。
     * Use one base item and one subclass item and compare the total with a hand calculation.
     */
    // Earlier Java | constructor, static, final, super, toString, instanceof, Comparable
    // orderExample() を main() から呼びます。Item と DeliveryItem は main() の外に置きます。
    // Call orderExample() from main(). Keep Item and DeliveryItem outside main().
    private static void orderExample() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Notebook", 5.00));
        items.add(new DeliveryItem("Pen", 2.00, 1.00));

        double sum = 0;
        int deliveries = 0;
        for (Item item : items) {
            System.out.println(item);
            // 実際のオブジェクトに合った total() が呼ばれます。
            // Java calls the total() implementation for the actual object.
            sum += item.total();
            if (item instanceof DeliveryItem) {
                deliveries++;
            }
        }
        System.out.printf("Items: %d, deliveries: %d, total: $%.2f%n",
                Item.getCount(), deliveries, sum);

        // compareTo() の順序でリストを並べ替えます。
        // Sort the list using the order defined by compareTo().
        items.sort(null);
    }

    private static class Item implements Comparable<Item> {
        private static int count;
        private final int id;
        private final String name;
        private final double price;

        Item(String name, double price) {
            id = ++count;
            this.name = name;
            this.price = price;
        }

        static int getCount() {
            return count;
        }

        double total() {
            return price;
        }

        @Override
        public String toString() {
            return String.format("#%d %s - $%.2f", id, name, price);
        }

        @Override
        public int compareTo(Item other) {
            return Double.compare(price, other.price);
        }
    }

    private static class DeliveryItem extends Item {
        private final double fee;

        DeliveryItem(String name, double price, double fee) {
            super(name, price);
            this.fee = fee;
        }

        @Override
        double total() {
            return super.total() + fee;
        }
    }

    // Earlier Java | abstract class and abstract method
    // サブクラスが area() の処理を書きます。
    // Each concrete subclass supplies the implementation of area().
    private abstract static class ShapeExample {
        abstract double area();
    }

    private static class SquareExample extends ShapeExample {
        private final double side;

        SquareExample(double side) {
            this.side = side;
        }

        @Override
        double area() {
            return side * side;
        }
    }

    // Earlier Java | array, for-each, parseInt, try/catch, Files
    private static int sumArray(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    private static void printInteger(String text) {
        try {
            int value = Integer.parseInt(text.trim());
            System.out.println(value);
        } catch (NumberFormatException exception) {
            System.out.println("Enter a whole number.");
        }
    }

    private static void printFile(Path path) {
        try {
            for (String line : Files.readAllLines(path)) {
                System.out.println(line);
            }
        } catch (IOException exception) {
            System.out.println("Could not read " + path);
        }
    }
}

/*
 * IntelliJ / Maven
 *
 * 既存プロジェクトにファイル全体を入れる場合は、最上部に対象の package 宣言を追加します。
 * ファイル全体をコンパイルするには javafx-controls と javafx-media が必要です。
 * JavaFX の各依存関係は同じバージョンにそろえ、pom.xml を編集したら Maven を再読み込みします。
 * モジュールを使う場合は module-info.java に requires javafx.controls; と requires javafx.media;
 * を入れ、アプリのパッケージを javafx.graphics にエクスポートしてください。
 *
 * When adding the full file to an existing project, add its package declaration above the imports.
 * Compiling the full file requires javafx-controls and javafx-media.
 * Use the same version for both JavaFX dependencies and reload Maven after editing pom.xml.
 * For a modular project, declare requires javafx.controls; and requires javafx.media;
 * in module-info.java and export the application package to javafx.graphics.
 */
