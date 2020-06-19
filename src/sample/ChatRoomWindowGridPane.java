package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.geometry.Insets;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.IOException;
import java.util.Optional;

public class ChatRoomWindowGridPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private TextField messageInput;
    private Button sendButton;
    private TextArea transcript;

    public void start(Stage stage) {

        transcript = new TextArea();
        transcript.setPrefRowCount(30);
        transcript.setPrefColumnCount(60);
        transcript.setWrapText(true);
        transcript.setEditable(false);

        sendButton = new Button("Send");
        messageInput = new TextField();
        messageInput.setMaxWidth(400);
        messageInput.setMinHeight(40);
        messageInput.setPrefColumnCount(40);
        sendButton.setOnAction(e -> doSend());
        sendButton.setDefaultButton(true);
        sendButton.setDisable(false);
        sendButton.setMinWidth(70);
        messageInput.setEditable(true);
        messageInput.setDisable(false);
        javafx.scene.text.Text contactsTitle = new javafx.scene.text.Text();
        contactsTitle.setText("Contacts");
        contactsTitle.setStyle("-fx-font-size:22px");

        GridPane root = new GridPane();
        root.getColumnConstraints().add(new ColumnConstraints(500));
        ColumnConstraints column2 = new ColumnConstraints(250, 250, Double.MAX_VALUE);
        column2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(column2);

        RowConstraints row1 = new RowConstraints(70, 70, Double.MAX_VALUE);
        RowConstraints row2 = new RowConstraints(80, 300, Double.MAX_VALUE);
        row2.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().add(row1);
        root.getRowConstraints().add(row2);

        root.setGridLinesVisible(true);

        root.add(sendButton, 0, 0);
        root.add(messageInput, 0, 0);
        root.add(transcript, 0, 1);
        root.add(contactsTitle, 1, 0);

        root.setHalignment(messageInput, HPos.RIGHT);
        root.setMargin(messageInput, new Insets(15));
        root.setMargin(sendButton, new Insets(8));
        root.setMargin(contactsTitle, new Insets(60));

        GridPane contacts = new GridPane();
        ColumnConstraints column01 = new ColumnConstraints(250);
        column01.setHgrow(Priority.ALWAYS);
        contacts.getColumnConstraints().add(column01);

        RowConstraints row01 = new RowConstraints(75, 75, Double.MAX_VALUE);
        RowConstraints row05 = new RowConstraints(75, 75, Double.MAX_VALUE);
        row05.setVgrow(Priority.ALWAYS);
        for (int i = 0; i < 4; i++) {
            contacts.getRowConstraints().add(row01);
        }
        contacts.getRowConstraints().add(row05);
        contacts.setGridLinesVisible(true);

        addContact(contacts, "Tom", 0, 0);
        addContact(contacts, "Yen", 0, 1);
        addContact(contacts, "Jane", 0, 2);
        addContact(contacts, "Gerald", 0, 3);
        addContact(contacts, "Fred", 0, 4);

        root.add(contacts, 1, 1);
        root.setGridLinesVisible(true);

        stage.setScene(new Scene(root, 700, 450));
        stage.setTitle("Chat");
        stage.setResizable(false);
        stage.show();
    }

    public void addContact(GridPane grid, String name, int x, int y) {
        javafx.scene.text.Text text = new javafx.scene.text.Text();
        text.setText(name);
        text.setStyle("-fx-font-size:20px");
        Button button = new Button("Write");
        button.setOnAction(e -> chooseFriend());
        grid.add(text, x, y);
        grid.add(button, x, y);
        grid.setMargin(text, new Insets(65));
        grid.setMargin(button, new Insets(5));
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

    private void chooseFriend() {
        transcript.clear();
        messageInput.clear();
    }
}