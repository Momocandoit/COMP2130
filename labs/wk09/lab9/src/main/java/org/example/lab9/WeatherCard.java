package org.example.lab9;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/* ---- image ----
 * background: blue sky
 * center: yellow sun
 * top: "Toronto"
 * left: cloud
 * bottom: weather forecast for 3 days
 */

/* --- classes ---
 * Application: 　JavaFXの基本クラス
 * Stage
 * Scene
 * Pane:        　部品を自由に配置するレイアウト
 * BorderPane: 　 上下左右中央に部品を配置するレイアウト
 * HBox
 * Label
 * Text
 * Image
 * ImageView
 * Rectangle
 * Circle
 * Color
 * Insets
 * Font
 *
 */

public class WeatherCard extends Application {
    @Override
    public void start(Stage stage){
        // ----- 画面上に部品を自由に配置できるレイアウト -----
        Pane sky = new Pane();

        // ----- 背景の四角形を作る -----
        Rectangle background = new Rectangle();
        background.widthProperty().bind(sky.widthProperty());      // 背景の四角形の幅と高さをskyの幅と高さに連動させる
        background.heightProperty().bind(sky.heightProperty());
        background.setFill(Color.LIGHTSKYBLUE);                    // 背景に色

        // ----- 太陽を作る -----
        Circle sun = new Circle(40, Color.GOLD);
        sun.centerXProperty().bind(sky.widthProperty().divide(2));       // 太陽の中心位置をskyの中央に設定する
        sun.centerYProperty().bind(sky.heightProperty().divide(2));

        // ----- 背景と太陽をPaneに追加する(追加した順番に奥から表示される) -----
        sky.getChildren().addAll(background, sun);

        // ----- 都市名を表示する -----
        Text city = new Text("Toronto");
        city.setFont(Font.font("Arial", FontWeight.BOLD, 28));  // font
        city.setFill(Color.rgb(25, 25,112));               // color

        // ----- 雲の画像を読み込む -----
        Image image = new Image(getClass().getResource("cloud.png").toExternalForm());
        ImageView cloud = new ImageView(image);
        cloud.setFitWidth(80);
        cloud.setPreserveRatio(true);  // <------- 画像の縦横比を維持する

        // ----- 3日分の天気予報を作る -----
        HBox days = new HBox(20, new Label("Mon 18 °C"), new Label("Tue 15 °C"),  new Label("Wed 12 °C"));
        days.setPadding(new Insets(10));  // HBoxの内側に10pxの余白。Insetsは上下左右に設定する

        // ----- 画面全体を配置する -----
        // BorderPaneは画面を5つの領域(top, bottom, right, left and center)に分けて配置できる
        BorderPane root = new BorderPane(sky);  // borderPaneを作成
        root.setTop(city);
        root.setLeft(cloud);
        root.setBottom(days);

        // ----- ウィンドウを表示する -----
        stage.setTitle("Weather");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
