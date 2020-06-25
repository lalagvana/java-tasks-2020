package sample;

import javafx.application.Application;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

import java.io.File;
import java.io.IOException;

public class ExamTask2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Button chooseImage;
    private ImageView showPNGImage;
    private ImageView showPGMImage;

    public void start(Stage stage) throws IOException {

        chooseImage = new Button("Select PNG image");
        final FileChooser fileChooser = new FileChooser();
        Label imgPath = new Label();

        VBox root = new VBox(imgPath, chooseImage);
        root.setPadding(new Insets(10));
        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle("Save PNG image as PGM");
        stage.setResizable(false);
        stage.show();

        chooseImage.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                imgPath.setText(file.getAbsolutePath());
                Image pic = new Image("file:" + file.getAbsolutePath());
                showPNGImage = new ImageView(pic);
                root.getChildren().addAll(showPNGImage);
                try {
                    convertPNGtoPGM(file.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void convertPNGtoPGM(String filename) throws IOException {
        Image img = new Image("file:" + filename);
        PixelReader pr = img.getPixelReader();
        PGMImage imgPGM = new PGMImage((int) img.getWidth(),(int) img.getHeight());
        for (int x = 0; x < (int) img.getWidth(); x++)
            for (int y = 0; y < (int) img.getHeight(); y++) {
                Color color = pr.getColor(x, y);
                double brightness = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
                imgPGM.setPixel(y, x, (int) Math.round(brightness*255));
            }
        imgPGM.saveTo(filename + ".pgm");
    }
}