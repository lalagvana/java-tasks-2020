package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.geometry.Insets;

import java.awt.Desktop;
import javax.imageio.ImageIO;
import javax.swing.text.html.ImageView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ExamTask2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Desktop desktop = Desktop.getDesktop();
    private Button chooseImage;
    private Label fileName;
    private ImageView showPNGImage;
    private ImageView showPGMImage;

    public void start(Stage stage) throws IOException {

        chooseImage = new Button("Select PNG image");
        final FileChooser fileChooser = new FileChooser();
        TextArea textArea = new TextArea();
        textArea.setMinHeight(70);
        chooseImage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    openFile(file);
                    List<File> files = Arrays.asList(file);
                    printLog(textArea, files);
                }
            }
        });

        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\lalag\\Desktop\\smolcatt.png"));
        ImageIO.write(bufferedImage, "pgm", new File("C:\\Users\\lalag\\Desktop\\smolcatt.png.pgm"));


        VBox root = new VBox(textArea, chooseImage);//(chooseImage, fileName, showPNGImage, showPGMImage);
        root.setPadding(new Insets(10));
        stage.setScene(new Scene(root, 700, 700));
        stage.setTitle("Save PNG image as PGM");
        stage.setResizable(false);
        stage.show();
    }
        private void printLog(TextArea textArea, List<File> files) {
            if (files == null || files.isEmpty()) {
                return;
            }
            for (File file : files) {
                textArea.appendText(file.getAbsolutePath() + "\n");
            }
        }

        private void openFile(File file) {
            try {
                this.desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }