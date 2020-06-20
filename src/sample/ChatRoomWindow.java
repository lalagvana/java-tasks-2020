package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
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

    public void start(Stage stage) {

        transcript = new TextArea();
        transcript.setPrefRowCount(30);
        transcript.setPrefColumnCount(60);
        transcript.setWrapText(true);
        transcript.setEditable(false);

        sendButton = new Button("send");
        messageInput = new TextField();
        messageInput.setPrefColumnCount(40);
        sendButton.setOnAction(e -> doSend());
        sendButton.setDefaultButton(true);
        sendButton.setDisable(false);
        messageInput.setEditable(true);
        messageInput.setDisable(false);

        VBox right = new VBox(8, new Label("Contacts"), contact("Tom"), contact("Yen"), contact("Gerald"), contact("Jane"));
        right.setPadding(new Insets(8));
        right.setStyle("-fx-border-color: black; -fx-border-width:2px");

        HBox bottom = new HBox(8, new Label("YOU SAY:"), messageInput, sendButton);
        HBox.setHgrow(messageInput, Priority.ALWAYS);
        bottom.setPadding(new Insets(8));
        bottom.setStyle("-fx-border-color: black; -fx-border-width:2px");
        BorderPane root = new BorderPane(transcript);
        root.setBottom(bottom);
        root.setRight(right);

        stage.setScene(new Scene(root));
        stage.setTitle("Chat");
        stage.setResizable(false);
        stage.show();
    }

    private void chooseFriend() {
        transcript.clear();
        messageInput.clear();
    }

    private void addToTranscript(String message) {
        Platform.runLater(() -> transcript.appendText(message + "\n\n"));
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

    public HBox contact(String name) {
        Button writeTo = new Button("Write smth to");
        writeTo.setOnAction(e -> chooseFriend());
        HBox contact = new HBox(3, writeTo, new Label(name));
        contact.setMinHeight(50);
        contact.setMinWidth(150);
        contact.setPadding(new Insets(10));
        contact.setStyle("-fx-background-color: #f2bcf8");
        return contact;
    }
}