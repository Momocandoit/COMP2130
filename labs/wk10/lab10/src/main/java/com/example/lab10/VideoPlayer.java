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
public class VideoPlayer extends Application {
    @Override
    public void start(Stage stage){
        Media media = new Media(this.getClass().getResource("clip.mp4").toExternalForm());
        MediaPlayer mpClip = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mpClip);
        // stage2
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
        Button btRewind = new Button("Rewind");
        btRewind.setOnAction(e -> mpClip.seek(Duration.ZERO));
        HBox controls = new HBox(10, btPlay, btRewind);
        controls.setPadding(new Insets(10));

        // stage3
        Slider sldVolume = new Slider(0, 100, 50);
        sldVolume.setShowTickMarks(true);
        sldVolume.setShowTickLabels(true);
        mpClip.volumeProperty().bind(sldVolume.valueProperty().divide(100));
        CheckBox chMute = new CheckBox("Mute");
        chMute.setOnAction(e -> mpClip.setMute(chMute.isSelected()));
        controls.getChildren().addAll(new Label("Volume"), sldVolume, chMute);

        // stage4
        TextField tfNote = new TextField();
        TextArea taNote = new TextArea();
        taNote.setEditable(false);
        taNote.setPrefColumnCount(15);
        tfNote.setOnAction(e -> {
            double seconds = mpClip.getCurrentTime().toSeconds();
            taNote.appendText(String.format("%.1f 2: %s%n", seconds, tfNote.getText()));
            tfNote.clear();
        });
        VBox notes = new VBox(10, new Label("Note: (press Enter)"), tfNote, taNote);
        notes.setPadding(new Insets(10));

        BorderPane root = new BorderPane(mediaView);
        root.setBottom(controls); //stage2
        root.setRight(notes);     //stage4
        stage.setTitle("Video Player");
        stage.setScene(new Scene(root, 560, 300));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
