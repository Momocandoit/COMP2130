# COMP2130 JavaFX 試験用コードリファレンス / Exam Code Reference

GitHub でこの README を開き、Mac では **⌘F**、Windows では **Ctrl+F** で問題文のキーワードを探します。各例の変数名、表示文字、配置を問題に合わせて変えてください。

Open this README on GitHub and use **⌘F** on Mac or **Ctrl+F** on Windows. Search for a keyword from the question, then adapt the variable names, display text, and layout.

| やりたいこと / Task | 探す場所 / Where to look |
|---|---|
| ウィンドウを開く / Open a window | [Week 9 · Stage and Scene](#week-9-stage-and-scene) |
| 上・中央・下に置く、横に並べる、重ねる / Place, align in a row, or stack nodes | [Week 9 · Layout](#week-9-layout) |
| 図形を描く、リサイズに追従させる、画像を表示する / Draw, resize, or load an image | [Week 9 · Shapes and images](#week-9-shapes-and-images) |
| 入力を読む、チェック状態を取る / Read text or checkbox state | [Week 10 · Controls](#week-10-controls) |
| 動画を再生する、頭出しする、音量を変える / Play, rewind, or adjust volume | [Week 10 · Media](#week-10-media) |
| ボタン、キー、マウスに反応する / Respond to a button, key, or mouse | [Week 11 · Events](#week-11-events) |
| interface、implements、ラムダ式 / Interfaces, implements, and lambdas | [Week 11 · Interface](#week-11-interface) |
| クラス、継承、合計、例外、ファイル / Classes, inheritance, totals, exceptions, and files | [Earlier Java](#earlier-java) |
| import、Maven、画像や動画が見つからない / Imports, Maven, or missing resources | [Troubleshooting](#troubleshooting) |

## Week 9 Stage and Scene

`Stage` はウィンドウ、`Scene` はその中身、`root` は部品をまとめる一番外側のレイアウトです。既存プロジェクトではファイルの先頭に、そのプロジェクトに合う `package` 宣言を付けます。

The `Stage` is the window, the `Scene` is its content, and `root` is the outermost layout. In an existing project, put the matching `package` declaration at the top.

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StatusCard extends Application {
    @Override
    public void start(Stage stage) {
        Label status = new Label("Ready");
        Button startButton = new Button("Start");

        BorderPane root = new BorderPane();
        root.setTop(new Label("Status"));
        root.setCenter(status);
        root.setBottom(startButton);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Status Card");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

以下の JavaFX の短い例は、特記がなければ `start()` に追加するか、対応する箇所と入れ替えます。たとえば `scene` を使う行は、`Scene scene = ...` の後に置きます。図形と動画の例は中央の部品を入れ替えます。`status` の表示を更新する例と組み合わせる場合は、そのラベルを見える位置に移してください。

Unless stated otherwise, add the shorter JavaFX examples inside `start()` or use them to replace the corresponding lines. Place any line that uses `scene` after its declaration. The shapes and video examples replace the center. To combine either with an example that updates `status`, place that label in another visible region.

## Week 9 Layout

| 指示 / Requirement | 書き方 / Code |
|---|---|
| 上・下・左・右・中央に置く / Place in a region | `root.setTop(node)`, `setBottom`, `setLeft`, `setRight`, `setCenter` |
| 横に並べる / Arrange in a row | `new HBox(10, first, second)` |
| 縦に並べる / Arrange in a column | `new VBox(10, first, second)` |
| 重ねて中央に置く / Layer nodes at the center | `new StackPane(background, foreground)` |
| 自分で座標を決める / Set positions yourself | `pane.getChildren().add(node)` と `node.relocate(x, y)` / and `node.relocate(x, y)` |

`HBox` と `VBox` の最初の数値は部品同士の間隔です。`setPadding` はレイアウトの内側に余白を作ります。下に２つボタンを置く場合は、まず `HBox` にまとめます。

The first number in an `HBox` or `VBox` is the gap between its children. `setPadding` adds space inside the layout’s edges. To place two buttons at the bottom, put both in a single `HBox`.

```java
// javafx.scene.layout.HBox, javafx.geometry.Insets
HBox controls = new HBox(10, startButton, new Button("Reset"));
controls.setPadding(new Insets(10));
root.setBottom(controls);
```

`root.setBottom(controls)` を呼ぶと、それまで下に置いていた内容は `controls` に置き換わります。`StackPane` は後から渡した部品を手前に描きます。

Calling `root.setBottom(controls)` replaces the previous bottom content with `controls`. A `StackPane` draws later children in front of earlier ones.

## Week 9 Shapes and Images

`bind()` の左側が自動更新される値です。下の例では長方形の幅と高さを `sky` に合わせ、円の中心を `sky` の幅と高さの半分にします。背景を先に追加するので、円が背景の手前に見えます。

The property on the left of `bind()` updates automatically. The rectangle follows the size of `sky`, and the circle’s center stays at half the width and height. Adding the background first keeps the circle visible in front.

```java
// javafx.scene.layout.Pane, javafx.scene.paint.Color
// javafx.scene.shape.Rectangle, javafx.scene.shape.Circle
Pane sky = new Pane();
Rectangle background = new Rectangle();
background.widthProperty().bind(sky.widthProperty());
background.heightProperty().bind(sky.heightProperty());
background.setFill(Color.LIGHTSKYBLUE);

Circle sun = new Circle(40, Color.GOLD);
sun.centerXProperty().bind(sky.widthProperty().divide(2));
sun.centerYProperty().bind(sky.heightProperty().divide(2));

sky.getChildren().addAll(background, sun);
root.setCenter(sky);
```

画像は `Image` に読み込み、画面に置くために `ImageView` を作ります。先頭が `/` のパスはリソースのルートから探すので、下の `"/cloud.png"` なら `src/main/resources/cloud.png` に置きます。

Load an image into an `Image`, then make an `ImageView` to display it. A path beginning with `/` starts at the resource root, so put `cloud.png` in `src/main/resources` for this example.

```java
// javafx.scene.image.Image, javafx.scene.image.ImageView
// java.net.URL, java.util.Objects
URL cloudUrl = Objects.requireNonNull(
        getClass().getResource("/cloud.png"), "Missing cloud.png");
ImageView cloud = new ImageView(new Image(cloudUrl.toExternalForm()));
cloud.setFitWidth(80);
cloud.setPreserveRatio(true);
root.setLeft(cloud);
```

## Week 10 Controls

`TextField` の `setOnAction` は、入力欄にフォーカスがあるときの Enter で使えます。この例では入力をラベルに表示し、履歴に１行追加してから入力欄を空にします。

A `TextField` action can run when you press Enter while the field has focus. This example updates the label, adds a line to the history, and clears the field.

```java
// javafx.scene.control.TextField, javafx.scene.control.TextArea
// javafx.scene.control.CheckBox
// javafx.scene.layout.VBox
TextField input = new TextField();
TextArea history = new TextArea();
history.setEditable(false);

input.setOnAction(event -> {
    String text = input.getText();
    status.setText(text);
    history.appendText(text + System.lineSeparator());
    input.clear();
});

CheckBox enabled = new CheckBox("Enabled");
enabled.setOnAction(event ->
        status.setText(enabled.isSelected() ? "On" : "Off"));

root.setRight(new VBox(10, input, history, enabled));
```

## Week 10 Media

`Media` は動画の読み込み元、`MediaPlayer` は再生の操作、`MediaView` は画面に置く部品です。下の例では `clip.mp4` を `src/main/resources` に置きます。Maven の動画用設定は [Troubleshooting](#troubleshooting) を参照してください。

`Media` identifies the video source, `MediaPlayer` controls playback, and `MediaView` displays it. Put `clip.mp4` in `src/main/resources` for this example. See [Troubleshooting](#troubleshooting) for Maven setup.

```java
// javafx.scene.media.Media, javafx.scene.media.MediaPlayer
// javafx.scene.media.MediaView
// javafx.scene.control.Slider, javafx.scene.control.CheckBox
// javafx.scene.layout.HBox, javafx.util.Duration
// java.net.URL, java.util.Objects
URL clipUrl = Objects.requireNonNull(
        getClass().getResource("/clip.mp4"), "Missing clip.mp4");
MediaPlayer player = new MediaPlayer(new Media(clipUrl.toExternalForm()));
root.setCenter(new MediaView(player));

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

Slider volume = new Slider(0, 100, 50);
player.volumeProperty().bind(volume.valueProperty().divide(100));

CheckBox mute = new CheckBox("Mute");
player.muteProperty().bind(mute.selectedProperty());

root.setBottom(new HBox(10, playPause, rewind, volume, mute));
```

`new Slider(0, 100, 50)` の順番は最小値、最大値、初期値です。音量には `0.0`〜`1.0` を使うので、スライダーの `50` は `divide(100)` で `0.5` になります。現在の再生位置を秒で取得するには `player.getCurrentTime().toSeconds()` を使います。

The slider arguments are minimum, maximum, and initial value. Volume uses a `0.0`–`1.0` scale, so `divide(100)` turns a slider value of `50` into `0.5`. Use `player.getCurrentTime().toSeconds()` to read the playback position in seconds.

## Week 11 Events

`setOnAction` や `setOnKeyPressed` は、あとで実行する処理を登録します。`new Scene(...)` で画面を作ったあと、キー入力時に実行したい処理を `scene.setOnKeyPressed(...)` に渡します。マウスの例は、上の図形の例で作った `sun` を使います。

`setOnAction` and `setOnKeyPressed` register code to run later. After creating the scene, use `scene.setOnKeyPressed(...)` to register your key handler. The mouse example uses `sun` from the shapes example above.

```java
// javafx.scene.input.KeyCode
startButton.setOnAction(event -> status.setText("Running"));

scene.setOnKeyPressed(event -> {
    if (event.getCode() == KeyCode.SPACE) {
        status.setText("Space pressed");
    }
});

sun.setOnMouseClicked(event -> sun.setFill(Color.ORANGE));
```

値が変わるたびに処理をする場合は `addListener` を使えます。たとえば音量スライダーの値が変わるたびに、パーセント表示を更新できます。`oldValue` が変更前、`newValue` が変更後です。

Use `addListener` to run code whenever a value changes. For example, update a percentage label whenever the volume slider moves. `oldValue` is the previous value and `newValue` is the updated value.

```java
volume.valueProperty().addListener((observable, oldValue, newValue) ->
        status.setText(String.format("%.0f%%", newValue.doubleValue())));
```

## Week 11 Interface

`EventHandler<ActionEvent>` は、`ActionEvent` を受け取る `handle(...)` メソッドを持つ型です。`implements` と書いたクラスは、この例では `public void handle(ActionEvent event)` を実装します。JavaFX はボタンのアクションが起きたときにイベントを渡して、そのメソッドを呼びます。

`EventHandler<ActionEvent>` is a type with a `handle(...)` method that accepts an `ActionEvent`. A class that `implements` it supplies `public void handle(ActionEvent event)`. When the button fires an action, JavaFX passes the event to that method.

```java
// javafx.event.ActionEvent, javafx.event.EventHandler
// Inside StatusCard, outside start()
private static class StartHandler implements EventHandler<ActionEvent> {
    private final Label status;

    StartHandler(Label status) {
        this.status = status;
    }

    @Override
    public void handle(ActionEvent event) {
        status.setText("Running");
    }
}
```

`start()` で次のどちらか１行を使います。ラムダ式の左側が引数で、右側が `handle` の中身に当たります。`@Override` は、実装しようとしたメソッドの形が合っているかをコンパイラに確認させます。

Use either line below in `start()`. The left side of the lambda is the parameter. The right side supplies the body of `handle`. `@Override` lets the compiler check that the method matches the one you intend to implement.

```java
startButton.setOnAction(new StartHandler(status));
// Or the same behavior with a lambda
startButton.setOnAction(event -> status.setText("Running"));
```

## Earlier Java

### Constructor, static, inheritance, and ArrayList

`static count` はクラスで共有し、`id` は各オブジェクトが持ちます。`super(...)` は親のコンストラクタを呼びます。`item.total()` は、実際に入っているオブジェクトに合った実装を呼び出します。

`static count` is shared by the class, and each object has its own `id`. `super(...)` calls the parent constructor. `item.total()` calls the implementation for the actual object in the list.

```java
import java.util.ArrayList;

public class OrderApp {
    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Notebook", 5.00));
        items.add(new DeliveryItem("Pen", 2.00, 1.00));

        double sum = 0;
        int deliveries = 0;
        for (Item item : items) {
            System.out.println(item);
            sum += item.total();
            if (item instanceof DeliveryItem) {
                deliveries++;
            }
        }
        System.out.printf("Items: %d, deliveries: %d, total: $%.2f%n",
                Item.getCount(), deliveries, sum);
    }
}

class Item {
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
}

class DeliveryItem extends Item {
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
```

### Arrays, Comparable, and files

| 指示 / Requirement | 書き方 / Code |
|---|---|
| 配列の各要素を読む / Read every array item | `for (int value : numbers) { ... }` |
| 配列とリストの要素数 / Get array or list size | 配列は `numbers.length`、リストは `items.size()`。 / Use `numbers.length` for an array and `items.size()` for a list. |
| 自作クラスの並べ替え順を決める / Define natural order | クラス宣言に `implements Comparable<Item>` を加え、`public int compareTo(Item other)` を実装します。数値の昇順なら `Double.compare(price, other.price)` を返します。 / Add `implements Comparable<Item>` and implement `public int compareTo(Item other)`. Return `Double.compare(price, other.price)` for ascending price. |
| サブクラスにメソッド実装を求める / Require subclasses to implement a method | 親クラスを `abstract class` にし、`abstract double total();` を宣言します。 / Declare the parent as an `abstract class` with `abstract double total();`. |
| 文字列が整数か確認する / Parse an integer | `Integer.parseInt(text.trim())` を `try` に入れ、`NumberFormatException` を `catch` します。 / Put the parse call in a `try` and catch `NumberFormatException`. |
| テキストを行ごとに読む / Read a text file line by line | `Files.readAllLines(Path.of("notes.txt"))` を使い、`IOException` を処理します。 / Use `Files.readAllLines(Path.of("notes.txt"))` and handle `IOException`. |

## Troubleshooting

| 症状 / Symptom | 確認すること / What to check |
|---|---|
| `Button` や `Label` がエラーになる / Button or Label has an error | import は `javafx.scene.control.Button` と `javafx.scene.control.Label` です。 / Use the imports `javafx.scene.control.Button` and `javafx.scene.control.Label`. |
| `getResource(...)` が `null` を返す / getResource returns null | ファイル名の大文字・小文字と場所を確認します。`"/cloud.png"` はリソースのルート、`"cloud.png"` はクラスのパッケージに対応する場所を探します。 / Check the exact filename and path. `"/cloud.png"` starts at the resource root, while `"cloud.png"` starts relative to the class’s package. |
| `javafx.scene.media` が解決できない / javafx.scene.media cannot be resolved | `pom.xml` に `org.openjfx:javafx-media` を追加し、他の JavaFX 依存関係と同じ version にそろえて Maven を再読み込みします。`module-info.java` がある場合は `requires javafx.media;` も追加します。 / Add `org.openjfx:javafx-media` with the same version as your other JavaFX dependencies and reload Maven. If your project has a `module-info.java`, also add `requires javafx.media;`. |
| 実行してもサンプルの Hello 画面が出る / The sample Hello window still opens | 実行設定のメインクラスを確認します。Maven の `javafx:run` なら、プラグインの `mainClass` を確認します。 / Check the run configuration’s main class. For Maven’s `javafx:run`, check the plugin’s `mainClass`. |
| 赤線は消えたが画面に部品が出ない / Code compiles but a control is missing | 部品をレイアウトに追加し、そのレイアウトを `Scene` につないだか確認します。 / Check that you added the control to a layout and attached that layout to the scene. |

## Course Examples

- Week 9 の `WeatherCard` では `root.setBottom(days)` と `background.widthProperty().bind(...)` を探します。 / In Week 9’s `WeatherCard`, look for `root.setBottom(days)` and `background.widthProperty().bind(...)`. [Week 9](https://github.com/sojoudian/COMP2130_Fall_2026/tree/master/Week09)
- Week 10 の `VideoPlayer` では `mpClip.volumeProperty()` と `tfNote.setOnAction(...)` を探します。 / In Week 10’s `VideoPlayer`, look for `mpClip.volumeProperty()` and `tfNote.setOnAction(...)`. [Week 10](https://github.com/sojoudian/COMP2130_Fall_2026/tree/master/Week10)
- Week 11 の `LambdaHandlerDemo.java` では `btSave.setOnAction(...)` を探します。 / In Week 11’s `LambdaHandlerDemo.java`, look for `btSave.setOnAction(...)`. [LambdaHandlerDemo.java](https://github.com/sojoudian/COMP2130_Fall_2026/blob/master/Week11/Lecture/src/main/java/com/example/week11/LambdaHandlerDemo.java)
