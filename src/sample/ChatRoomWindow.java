package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;

import java.io.IOException;
import java.util.Optional;
public class ChatRoomWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private TextField messageInput;
    private Button sendButton;
    private Button quitButton;
    private TextArea transcript;

    public void start( Stage stage ) {

        transcript = new TextArea();
        transcript.setPrefRowCount(30);
        transcript.setPrefColumnCount(60);
        transcript.setWrapText(true);
        transcript.setEditable(false);

        sendButton = new Button("send");
        messageInput = new TextField();
        messageInput.setPrefColumnCount(40);
        sendButton.setOnAction( e -> doSend() );
        sendButton.setDefaultButton(true);
        sendButton.setDisable(false);
        messageInput.setEditable(true);
        messageInput.setDisable(false);
        Button writeToTom = new Button("write smth to");
        HBox Tom = new HBox(3,writeToTom,new Label("Tom"));
        Tom.setMinHeight(50);
        Tom.setMinWidth(150);
        Tom.setStyle("-fx-background-color: #f2bcf8");
        Button writeToYen = new Button("write smth to");
        HBox Yen = new HBox(3,writeToYen,new Label("Yen"));
        Yen.setMinHeight(50);
        Yen.setMinWidth(150);
        Yen.setStyle("-fx-background-color: #f2bcf8");
        Button writeToGerald = new Button("write smth to");
        HBox Gerald = new HBox(3,writeToGerald,new Label("Gerald"));
        Gerald.setMinHeight(50);
        Gerald.setMinWidth(150);
        Gerald.setStyle("-fx-background-color: #f2bcf8");
        Button writeToJane = new Button("write smth to");
        HBox Jane = new HBox(3,writeToJane,new Label("Jane"));
        Jane.setMinHeight(50);
        Jane.setMinWidth(150);
        Jane.setStyle("-fx-background-color: #f2bcf8");
        VBox right = new VBox(8, new Label("Contacts"), Tom, Yen, Gerald, Jane);
        right.setPadding(new Insets(8));
        right.setStyle("-fx-border-color: black; -fx-border-width:2px");

        writeToTom.setOnAction( e -> chooseFriend() );
        writeToGerald.setOnAction( e -> chooseFriend() );
        writeToYen.setOnAction( e -> chooseFriend() );
        writeToJane.setOnAction( e -> chooseFriend() );

        HBox bottom = new HBox(8, new Label("YOU SAY:"), messageInput, sendButton);
        HBox.setHgrow(messageInput, Priority.ALWAYS);
        bottom.setPadding(new Insets(8));
        bottom.setStyle("-fx-border-color: black; -fx-border-width:2px");
        BorderPane root = new BorderPane(transcript);
        root.setBottom(bottom);
        root.setRight(right);

        stage.setScene( new Scene(root) );
        stage.setTitle("Chat");
        stage.setResizable(false);
        stage.show();
    }

    private void chooseFriend(){
        transcript.clear();
        messageInput.clear();
    }
    private void addToTranscript(String message) {
        Platform.runLater( () ->    transcript.appendText(message + "\n\n") );
    }
    private void doSend() {
        String message = messageInput.getText();
        if (message.trim().length() == 0)
            return;
        else addToTranscript(message);
        messageInput.selectAll();
        messageInput.requestFocus();
        messageInput.clear();
    }
}