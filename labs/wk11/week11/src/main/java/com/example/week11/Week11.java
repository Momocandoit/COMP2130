package com.example.week11;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/* rを押したら赤、bを押したら青でマウスの位置に点が描かれる。
 * 下のバーには点の数が表示される。
 *
 *
 */

public class Week11 extends Application {
    // =========== Setting up the drawing state ===========
    // drawing area
    private final Pane canvas = new Pane();

    // 点を描いた順に保存する
    private final List<Circle> dots = new ArrayList<>();
    private final Label lblInfo = new Label("Dots: 0, red: 0");
    private Color color = Color.BLUE;


    // =========== start() ==============
    // 起動時はstartから始まる。
    @Override
    public void start(Stage stage) {
        // マウス操作に処理を登録する
        DotHandler handler = new DotHandler();
        canvas.setOnMousePressed(handler);
        canvas.setOnMouseDragged(handler);

        // Clearボタンを作る
        Button btClear = new Button("Clear");

        // anonymous classで、ボタンの操作に対する処理を書く
        btClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                canvas.getChildren().clear();
                dots.clear();
                update();
            }
        });

        // Undoボタンを作る
        Button btUndo = new Button("Undo");

        // Lamda to define this button's action
        btUndo.setOnAction(e -> {
            if (!dots.isEmpty()) {
                canvas.getChildren().remove(dots.remove(dots.size() - 1));
                update();
            }
        });

        // 画面全体を配置する arrange the layout
        HBox bar = new HBox(10, btClear, btUndo, lblInfo);
        bar.setPadding(new Insets(10));

        // 配置。Put the drawing area in the center of the BorderPane
        BorderPane root = new BorderPane(canvas);
        root.setBottom(bar);
        Scene scene = new Scene(root, 400, 300);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.R) color = Color.RED;
            else if (e.getCode() == KeyCode.B) color = Color.BLUE;
        });
        stage.setTitle("Dot Painter");
        stage.setScene(scene);
        stage.show();
    }


    // ========= update() ===============
    private void update() {
        long red = dots.stream().filter(d -> d.getFill().equals(Color.RED)).count();
        lblInfo.setText("Dots: " + dots.size() + ", red: " + red);
    }

    // ======== implementation ==========
    class DotHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            Circle dot = new Circle(e.getX(), e.getY(), 6, color);
            dots.add(dot);
            canvas.getChildren().add(dot);
            update();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}