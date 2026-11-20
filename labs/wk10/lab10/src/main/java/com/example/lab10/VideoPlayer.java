package com.example.lab10;

import javafx.application.Application;
import javafx.geometry.Insets;  // stage2
import javafx.scene.Scene;
import javafx.scene.control.Button;  // stage2
import javafx.scene.control.CheckBox;         //stage3
import javafx.scene.control.Label;            //stage3
import javafx.scene.control.Slider;           //stage3

import javafx.scene.control.TextArea;                 // stage4
import javafx.scene.control.TextField;                // stage4

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;  // stage2

import javafx.scene.layout.VBox;                     // stage4

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration; // stage2

// 最初の方のイントロが書ければ20％もらえる
/* ----- classes -----
 * Stage
 * Scene
 * Button
 * CheckBox
 * Label
 * Slider
 * TextField
 * TextArea
 * Media         メディアファイルを読み込む
 * MediaPlayer   動画を再生・停止する
 * MediaView　　　動画を画面に表示
 * Duration
 */
public class VideoPlayer extends Application {
    @Override
    public void start(Stage stage){
        // ----- 動画ファイルを読み込む -----
        Media media = new Media(this.getClass().getResource("clip.mp4").toExternalForm());
        MediaPlayer mpClip = new MediaPlayer(media);  // 読み込んだ動画を操作するためのプレイヤーを作成  mpClip.play();で再生　pauseで一時停止
        MediaView mediaView = new MediaView(mpClip);  // 画面に表示

        // ----- Play/Pause ボタンを作る -----
        Button btPlay = new Button("Play");
        btPlay.setOnAction(e -> {
            if(btPlay.getText().equals("Play")){
                mpClip.play();
                btPlay.setText("Pause");
            } else {
                mpClip.pause();
                btPlay.setText("Play");
            }
        });

        // ----- Rewind ボタンを作る -----
        Button btRewind = new Button("Rewind");
        btRewind.setOnAction(e -> mpClip.seek(Duration.ZERO));

        // ----- HBoxにボタンを配置する -----
        HBox controls = new HBox(10, btPlay, btRewind);
        controls.setPadding(new Insets(10));    //周囲に10pxの余白

        // ----- 音量調整とミュート機能を作る -----
        Slider sldVolume = new Slider(0, 100, 50);
        sldVolume.setShowTickMarks(true);   // 目盛りの線を表示
        sldVolume.setShowTickLabels(true);  // 目盛りの数値を表示

        // ----- 音量とスライダーを連動させる -----
        mpClip.volumeProperty().bind(sldVolume.valueProperty().divide(100));  // JavaFXのMediaPlayerの音量は基本0.0 ~ 1.0だから100で割る

        // ----- muteチェックボックス -----
        CheckBox chMute = new CheckBox("Mute");
        chMute.setOnAction(e -> mpClip.setMute(chMute.isSelected()));

        // ----- 音量関連の部品をHBoxに追加 -----
        controls.getChildren().addAll(new Label("Volume"), sldVolume, chMute);

        // ----- メモ機能 -----
        TextField tfNote = new TextField();
        TextArea taNote = new TextArea();     // TextFieldから入力、TextAreaに記録される
        taNote.setEditable(false);            // userが編集できないようにする
        taNote.setPrefColumnCount(15);

        // ----- メモでenterキーを押した時の処理 -----
        tfNote.setOnAction(e -> {
            double seconds = mpClip.getCurrentTime().toSeconds();
            taNote.appendText(String.format("%.1f 2: %s%n", seconds, tfNote.getText()));
            tfNote.clear();
        });

        // ----- メモ関連の部品をVBoxに配置する -----
        VBox notes = new VBox(10, new Label("Note: (press Enter)"), tfNote, taNote);
        notes.setPadding(new Insets(10));

        // ----- BorderPaneで全体を配置する -----
        BorderPane root = new BorderPane(mediaView);
        root.setBottom(controls);
        root.setRight(notes);

        // ----- ウィンドウを表示 -----
        stage.setTitle("Video Player");
        stage.setScene(new Scene(root, 560, 300));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
